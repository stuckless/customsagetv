package org.jdna.media.metadata.impl.composite;

import java.util.List;

import org.apache.log4j.Logger;
import org.jdna.media.metadata.IMediaMetadata;
import org.jdna.media.metadata.IMediaMetadataProvider;
import org.jdna.media.metadata.IMediaSearchResult;
import org.jdna.media.metadata.IProviderInfo;
import org.jdna.media.metadata.MediaMetadata;
import org.jdna.media.metadata.MediaMetadataFactory;
import org.jdna.media.metadata.MetadataID;
import org.jdna.media.metadata.MetadataKey;
import org.jdna.media.metadata.ProviderInfo;
import org.jdna.media.metadata.SearchQuery;
import org.jdna.media.metadata.SearchQuery.Type;

public class CompositeMetadataProvider implements IMediaMetadataProvider {
    private static final Logger            log          = Logger.getLogger(CompositeMetadataProvider.class);

    private IProviderInfo                  info;
    private String                         searcherProviderId;
    private String                         detailsProviderId;
    private CompositeMetadataConfiguration conf;

    public CompositeMetadataProvider(CompositeMetadataConfiguration conf) {
        info = new ProviderInfo(conf.getId(), conf.getName(), conf.getDescription(), conf.getIconUrl());
        this.searcherProviderId = conf.getSearchProviderId();
        this.detailsProviderId = conf.getDetailProviderId();
        this.conf = conf;
        log.debug("Composite Provider Created with id: " + getInfo().getId() + "; search: " + searcherProviderId + "; details: " + detailsProviderId);
    }

    public IProviderInfo getInfo() {
        return info;
    }

    public IMediaMetadata getMetaData(IMediaSearchResult result) throws Exception {
        // get the primary results from the search
        IMediaMetadata searcher = MediaMetadataFactory.getInstance().getProvider(searcherProviderId).getMetaData(result);
        
        // now get the search results from the title search
        IMediaMetadata details = searchDetailsByResult(result);

        // return the merged details
        return mergeDetails(details, searcher);
    }
    
    private IMediaMetadata searchDetailsByResult(IMediaSearchResult result) {
        try {
            log.debug("Searching the details provider: " + detailsProviderId + " for movie id: " + result.getMetadataId());
            IMediaMetadata detail = MediaMetadataFactory.getInstance().getProvider(detailsProviderId).getMetaDataById(result.getMetadataId());
            if (detail == null) {
                log.debug("Searching the details provider: " + detailsProviderId + " for movie title: " + result.getTitle());
	            List<IMediaSearchResult> results = MediaMetadataFactory.getInstance().getProvider(detailsProviderId).search(new SearchQuery(result.getTitle()));
	            if (MediaMetadataFactory.getInstance().isGoodSearch(results)) {
	                return MediaMetadataFactory.getInstance().getProvider(detailsProviderId).getMetaData(results.get(0));
	            }
            } else
            	return detail;
        } catch (Exception e) {
            log.error("Failed to find an Exact Match for " + result.getTitle() + " using provider: " + detailsProviderId);
        }
        return null;
    }

    private IMediaMetadata searchDetailsByTitle(String title) {
        try {
            log.debug("Searching the details provider: " + detailsProviderId + " for movie title: " + title);
            List<IMediaSearchResult> results = MediaMetadataFactory.getInstance().getProvider(detailsProviderId).search(new SearchQuery(title));
            if (MediaMetadataFactory.getInstance().isGoodSearch(results)) {
                return searchDetailsByResult(results.get(0));
            }
        } catch (Exception e) {
            log.error("Failed to find an Exact Match for " + title + " using provider: " + detailsProviderId);
        }
        return null;
    }

    public List<IMediaSearchResult> search(SearchQuery query) throws Exception {
    	String providerId = getInfo().getId();
        log.debug("Searching using composite provider: " + providerId);
        List<IMediaSearchResult> results = MediaMetadataFactory.getInstance().getProvider(searcherProviderId).search(query);
        
        for (int i =0; i< results.size(); i++) {
        	results.get(i).setProviderId(providerId);
        }
        
        return results;
    }

    private IMediaMetadata mergeDetails(IMediaMetadata details, IMediaMetadata searcher) {
        MediaMetadata md = new MediaMetadata(details);
        md.setProviderId(getInfo().getId());
        if (searcher == null) return md;

        // find the override fields, and apply them
        String fieldStr = conf.getFieldsFromSearchProvider();
        if (fieldStr!=null && fieldStr.trim().length()>0) {
            String fields[] = fieldStr.split(";");
            for (String f : fields) {
                try {
                    MetadataKey mdKey = MetadataKey.valueOfId(f);
                    Object o = searcher.get(mdKey);
                    // don't overwrite unless we have data
                    if (!isEmpty(o)) {
                        md.set(mdKey, o);
                    }
                } catch (Exception e) {
                    log.error("Invalid Merge Field Name in Composite Provider: " + f);
                }
            }
        }

        // now find all fields that null in the primary, and set them from the
        // seconday
        for (MetadataKey k : MetadataKey.values()) {
            Object o = md.get(k);
            if (isEmpty(o)) {
                md.set(k, searcher.get(k));
            }
        }
        
        // set us to be the provider
        md.setProviderDataUrl(searcher.getProviderDataUrl());
        md.setProviderDataId(searcher.getProviderDataId());
        return md;
    }
    
    private boolean isEmpty(Object o) {
        if (o == null 
                || (o.getClass().isArray() && ((Object[]) o).length == 0) 
                || (o instanceof String && ((String)o).trim().length()==0)
                ) {
            return true;
        } else {
            return false;
        }
    }

    public Type[] getSupportedSearchTypes() {
        return MediaMetadataFactory.getInstance().getProvider(searcherProviderId).getSupportedSearchTypes();
    }

    public IMediaMetadata getMetaDataById(MetadataID id) throws Exception {
        throw new Exception("Search by MetadataID not supported.");
    }

    public IMediaMetadata getMetaDataByUrl(String url) throws Exception {
        IMediaMetadata searcher = MediaMetadataFactory.getInstance().getProvider(searcherProviderId).getMetaDataByUrl(url);
        IMediaMetadata details = null;
        try {
            if (searcher.getProviderDataId()!=null) {
                details = MediaMetadataFactory.getInstance().getProvider(searcherProviderId).getMetaDataById(searcher.getProviderDataId());
            }
        } catch (Exception e) {
            log.error("Failed to find details using searcher's metadataid: " + searcher.getProviderDataId() + " will try using title search",e);
        }
        details = searchDetailsByTitle(searcher.getMediaTitle()); 
        return mergeDetails(details, searcher);
    }
}