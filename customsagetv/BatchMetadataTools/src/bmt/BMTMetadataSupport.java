package bmt;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdna.configuration.ConfigurationManager;
import org.jdna.media.IMediaFile;
import org.jdna.media.MediaConfiguration;
import org.jdna.media.MovieResourceFilter;
import org.jdna.media.metadata.IMediaMetadata;
import org.jdna.media.metadata.IMediaMetadataPersistence;
import org.jdna.media.metadata.IMediaMetadataProvider;
import org.jdna.media.metadata.IMediaSearchResult;
import org.jdna.media.metadata.MediaMetadataFactory;
import org.jdna.media.metadata.MetadataAPI;
import org.jdna.media.metadata.MetadataConfiguration;
import org.jdna.media.metadata.PersistenceOptions;
import org.jdna.media.metadata.SearchQuery;
import org.jdna.media.metadata.SearchQueryFactory;
import org.jdna.media.metadata.impl.sage.CentralFanartPersistence;
import org.jdna.media.metadata.impl.sage.SageProperty;
import org.jdna.media.metadata.impl.sage.SageTVPropertiesPersistence;
import org.jdna.media.util.AutomaticUpdateMetadataVisitor;
import org.jdna.media.util.FilteredResourceVisitor;
import org.jdna.metadataupdater.MetadataUpdaterConfiguration;
import org.jdna.sage.MetadataPluginOptions;
import org.jdna.sage.media.SageMediaFile;
import org.jdna.sage.media.SageMediaFolder;
import org.jdna.sage.media.SageShowPeristence;
import org.jdna.util.MediaScanner;
import org.jdna.util.ProgressTracker;
import org.jdna.util.ProgressTrackerManager;

import sagex.api.AiringAPI;
import sagex.api.Global;
import sagex.api.MediaFileAPI;
import sagex.phoenix.configuration.proxy.GroupProxy;
import sagex.phoenix.fanart.IMetadataProviderInfo;
import sagex.phoenix.fanart.IMetadataSearchResult;
import sagex.phoenix.fanart.IMetadataSupport;

/**
 * Allows BMT to provide metadata support to the Phoenix Metadata apis.
 * 
 * @author seans
 * 
 */
public class BMTMetadataSupport implements IMetadataSupport {
    private static final Logger log = Logger.getLogger(BMTMetadataSupport.class);
    private MetadataUpdaterConfiguration updaterConfig = GroupProxy.get(MetadataUpdaterConfiguration.class);
    private MetadataConfiguration metadataConfig = GroupProxy.get(MetadataConfiguration.class);
    
    private String currentTrackerId = null;
    
    public BMTMetadataSupport() {
        log.info("Using BMTMetadataSupport: " + bmt.api.GetVersion());
    }

    public void addActiveProvider(IMetadataProviderInfo pi) {
        bmt.api.AddDefaultMetadataProvider(pi);
    }

    public void decreaseProviderPriority(IMetadataProviderInfo pi) {
        bmt.api.DecreaseMetadataProviderPriority(pi);
    }

    public IMetadataProviderInfo[] getInstalledProviders() {
        return bmt.api.getMetadataProviders(true);
    }

    public void increaseProviderPriority(IMetadataProviderInfo pi) {
        bmt.api.IncreaseMetadataProviderPriority(pi);
    }

    public boolean isMetadataSupportEnabled() {
        return true;
    }

    public void removeActiveProvider(IMetadataProviderInfo pi) {
        bmt.api.RemoveDefaultMetadataProvider(pi);
    }

    public IMetadataProviderInfo[] getActiveProviders() {
        return bmt.api.getMetadataProviders(false);
    }

    public String[] getMetadataKeys(KeyType type) {
        List<String> l = new LinkedList<String>();
        
        for (SageProperty p : SageProperty.values()) {
            if (p.sageKey!=null) {
                l.add(p.sageKey);
            }
        }
        
        return l.toArray(new String[l.size()]);
    }

    public boolean updateMetadataForResult(Object media, IMetadataSearchResult result) {
        try {
            // first we need to update the central fanart properties based on
            // the ui settings
            updaterConfig.setCentralFanartFolder(phoenix.api.GetFanartCentralFolder());
            updaterConfig.setFanartEnabled(phoenix.api.IsFanartEnabled());

            IMediaMetadataProvider prov = MediaMetadataFactory.getInstance().getProvider(result.getProviderId());
            IMediaMetadata md = prov.getMetaData((IMediaSearchResult) result);
            IMediaMetadataPersistence persistence = null; 

            if (MediaFileAPI.IsMediaFileObject(media)) {
                persistence = MetadataPluginOptions.getOnDemandUpdaterPersistence();
            } else {
                persistence = MetadataPluginOptions.getFanartOnlyPersistence();
            }

            PersistenceOptions options = new PersistenceOptions();
            options.setImportAsTV(metadataConfig.isImportTVAsRecordedShows());
            options.setOverwriteFanart(false);
            options.setOverwriteMetadata(true);
            options.setUseTitleMasks(true);
            
            IMediaFile smf = new SageMediaFile(media);
            MetadataAPI.normalizeMetadata(smf, md, options);
            
            // Save the sage properties, the update sage's wiz.bin, then download the fanart.
            persistence.storeMetaData(md, smf, options);
            
            try {
                if (result instanceof IMediaSearchResult) {
                    ConfigurationManager.getInstance().setMetadataIdForTitle(smf.getTitle(), ((IMediaSearchResult)result).getMetadataId());
                    ConfigurationManager.getInstance().saveTitleMappings();
                }
            } catch (Exception ex) {
                log.error("Failed to update title mappings!", ex);
            }
        } catch (Exception e) {
            log.error("Failed to update metadata!", e);
        }
        
        return false;
    }

    public IMetadataSearchResult[] getMetadataSearchResults(String providerId, Object media) {
        log.debug("Searching for metadata; Provider: " + providerId + "; media: " + media);
        
        if (media == null) {
            log.debug("getMetadataSearchResults() was passed a null media item");
            return null;
        }
        
        if (providerId==null) {
            providerId = metadataConfig.getDefaultProviderId(); 
        }

        try {
            SageMediaFile smf = new SageMediaFile(media);

            SearchQuery q = SearchQueryFactory.getInstance().createQuery(smf);
            if (smf.getContentType() == IMediaFile.ContentType.TV) {
                q.setType(SearchQuery.Type.TV);
            }

            log.debug("Metadata Search for: " + q + "; media: " + media);
            IMediaMetadataProvider prov = MediaMetadataFactory.getInstance().getProvider(providerId);
            List<IMediaSearchResult> l = prov.search(q);
            if (l == null || l.size() == 0) {
                log.debug("No matches for: " + q);
            } else {
                return l.toArray(new IMetadataSearchResult[l.size()]);
            }
        } catch (Exception e) {
            log.error("Failed to do a metadata lookup", e);
        }
        return null;
    }

    public float getMetadataScanComplete() {
        return getMetadataScanComplete(currentTrackerId);
    }

    public boolean isMetadataScanRunning() {
        return isMetadataScanRunning(currentTrackerId);
    }

    public float getMetadataScanComplete(Object tracker) {
        ProgressTracker<IMediaFile> mt = getTracker(tracker);
        if (mt!=null) return (float) mt.internalWorked();
        return 0;
    }

    public boolean isMetadataScanRunning(Object tracker) {
        ProgressTracker<IMediaFile> mt = getTracker(tracker);
        if (mt!=null) return mt.isDone() || mt.isCancelled();
        return false;
    }

    private ProgressTracker<IMediaFile> getTracker(Object tracker) {
        if (tracker!=null) {
            return (ProgressTracker<IMediaFile>) ProgressTrackerManager.getInstance().getProgress((String) tracker);
        }
        return null;
    }
    
    public synchronized Object startMetadataScan(String provider, Object[] sageMediaFiles) {
        try {
            if (isMetadataScanRunning()) {
                log.error("Can't start another tracker until the current tracker is done!");
                return null;
            }
            
            if (sageMediaFiles==null) {
                log.error("Can't scan, since media files are null!");
                return null;
            }

            log.info("BMT Media Scan about to start with " + sageMediaFiles.length + " items");
            
            MetadataConfiguration metadataConfig = GroupProxy.get(MetadataConfiguration.class);
            ProgressTracker<IMediaFile> tracker = new ProgressTracker<IMediaFile>();
            PersistenceOptions options = new PersistenceOptions();
            options.setImportAsTV(false);
            options.setUseTitleMasks(true);
            options.setOverwriteFanart(true);
            options.setOverwriteMetadata(true);
            AutomaticUpdateMetadataVisitor autoUpdater = new AutomaticUpdateMetadataVisitor(metadataConfig.getDefaultProviderId(), MetadataPluginOptions.getOnDemandUpdaterPersistence(), options, SearchQuery.Type.MOVIE, tracker);
            
            FilteredResourceVisitor scanVisitor = new FilteredResourceVisitor(new MovieResourceFilter(), autoUpdater);
            MediaScanner scanner = new MediaScanner(new SageMediaFolder(sageMediaFiles), scanVisitor);
            currentTrackerId = ProgressTrackerManager.getInstance().runWithProgress(scanner, tracker);
            log.info("BMT Media Scan was started with tracker id: " + currentTrackerId);
        } catch (Throwable e) {
            log.error("Scan Failed!", e);
            currentTrackerId = null;
        }
        return currentTrackerId;
    }

    public boolean cancelMetadataScan(Object tracker) {
        ProgressTracker<IMediaFile> mt = getTracker(tracker);
        if (mt!=null) {
            log.info("Cancelling Media Scan: " + tracker);
            mt.setCancelled(true);
            currentTrackerId=null;
        }
        return true;
    }
}