package org.jdna.bmt.web.client.ui.browser;


import java.util.List;

import org.jdna.bmt.web.client.media.GWTMediaFile;
import org.jdna.bmt.web.client.media.GWTMediaFolder;
import org.jdna.bmt.web.client.media.GWTMediaMetadata;
import org.jdna.bmt.web.client.media.GWTMediaResource;
import org.jdna.bmt.web.client.media.GWTMediaSearchResult;
import org.jdna.bmt.web.client.media.GWTPersistenceOptions;
import org.jdna.bmt.web.client.media.GWTProviderInfo;
import org.jdna.bmt.web.client.ui.util.ServiceReply;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MetadataServiceAsync {
    void scan(GWTMediaFolder folder, PersistenceOptionsUI options, AsyncCallback<String> callback);
    void loadMetadata(GWTMediaFile mediaFile, AsyncCallback<GWTMediaMetadata> callback);
    void getMetadata(GWTMediaSearchResult result, GWTPersistenceOptions options,AsyncCallback<GWTMediaMetadata> callback);
    void searchForMetadata(GWTMediaFile item, SearchQueryOptions options, AsyncCallback<List<GWTMediaSearchResult>> jsonReply);
    void getStatus(String id, AsyncCallback<ProgressStatus> status);
    void saveMetadata(GWTMediaFile file, PersistenceOptionsUI options, AsyncCallback<ServiceReply<GWTMediaFile>> result);
    void getProviders(AsyncCallback<List<GWTProviderInfo>> result);
    void cancelScan(String scanId, AsyncCallback<Void> result);
    void getScansInProgress(AsyncCallback<ProgressStatus[]> callback);
    void removeScan(String progressId, AsyncCallback<Void> asyncCallback);
    void getProgressItems(String progressId, boolean b, AsyncCallback<GWTMediaResource[]> asyncCallback);
}