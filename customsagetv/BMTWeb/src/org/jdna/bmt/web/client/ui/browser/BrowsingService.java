package org.jdna.bmt.web.client.ui.browser;

import java.util.ArrayList;
import java.util.List;

import org.jdna.bmt.web.client.media.GWTMediaArt;
import org.jdna.bmt.web.client.media.GWTMediaFile;
import org.jdna.bmt.web.client.media.GWTMediaFolder;
import org.jdna.bmt.web.client.media.GWTMediaMetadata;
import org.jdna.bmt.web.client.media.GWTMediaResource;
import org.jdna.bmt.web.client.media.GWTMediaSearchResult;
import org.jdna.bmt.web.client.media.GWTPersistenceOptions;
import org.jdna.bmt.web.client.media.GWTProviderInfo;
import org.jdna.bmt.web.client.media.GWTView;
import org.jdna.bmt.web.client.media.GWTViewCategories;
import org.jdna.bmt.web.client.ui.util.ServiceReply;

import sagex.phoenix.metadata.MediaArtifactType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;




@RemoteServiceRelativePath("browsing")
public interface BrowsingService extends RemoteService {
    public String scan(GWTMediaFolder folder, PersistenceOptionsUI options);
    public ProgressStatus[] getScansInProgress();
    public ProgressStatus getStatus(String id);
    public void cancelScan(String id);
    public void removeScan(String progressId);
    public GWTMediaResource[] getProgressItems(String progressId, boolean b);

    public GWTMediaResource[] browseChildren(GWTMediaFolder folder, int start, int size);
    public GWTMediaMetadata loadMetadata(GWTMediaFile file);
    public GWTMediaMetadata getMetadata(GWTMediaSearchResult result, GWTPersistenceOptions options);
    public List<GWTMediaSearchResult> searchForMetadata(GWTMediaFile item, SearchQueryOptions options);
    public ServiceReply<GWTMediaFile> saveMetadata(GWTMediaFile file, PersistenceOptionsUI options);
    
    public List<GWTProviderInfo> getProviders();
    public ArrayList<GWTMediaArt> getFanart(GWTMediaFile file, MediaArtifactType artifact);
    public GWTMediaArt downloadFanart(GWTMediaFile file, MediaArtifactType artifact, GWTMediaArt ma);
    
    public boolean deleteFanart(GWTMediaArt art);
    public void makeDefaultFanart(GWTMediaFile file, MediaArtifactType type, GWTMediaArt art);
    
    public GWTMediaFolder searchMediaFiles(String search);
    public SearchQueryOptions discoverQueryOptions(GWTMediaFile file);
    
    public ArrayList<GWTView> getViewCategories();
    public GWTViewCategories getViews(String tag);
	public GWTMediaFolder getView(GWTView view);
}
