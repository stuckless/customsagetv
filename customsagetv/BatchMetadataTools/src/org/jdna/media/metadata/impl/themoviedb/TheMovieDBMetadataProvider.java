package org.jdna.media.metadata.impl.themoviedb;

import java.util.List;

import org.jdna.media.metadata.IMediaMetadata;
import org.jdna.media.metadata.IMediaMetadataProvider;
import org.jdna.media.metadata.IMediaSearchResult;
import org.jdna.media.metadata.IProviderInfo;
import org.jdna.media.metadata.MetadataID;
import org.jdna.media.metadata.ProviderInfo;
import org.jdna.media.metadata.SearchQuery;
import org.jdna.media.metadata.impl.imdb.IMDBMetaDataProvider;

import sagex.phoenix.fanart.MediaType;

public class TheMovieDBMetadataProvider implements IMediaMetadataProvider {
    public static final String   PROVIDER_ID = "themoviedb.org";
    private static IProviderInfo info        = new ProviderInfo(PROVIDER_ID, "themoviedb.org", "Provides Fanart and Metadata from themoviedb.org", "http://www.themoviedb.org/images/tmdb/header-logo.png");
    private static final MediaType[] supportedSearchTypes = new MediaType[] {MediaType.MOVIE};

    public IProviderInfo getInfo() {
        return info;
    }

    public IMediaMetadata getMetaData(IMediaSearchResult result) throws Exception {
    	return getMetaDataByUrl(result.getUrl());  
    }

    public List<IMediaSearchResult> search(SearchQuery query) throws Exception {
        if (query.getMediaType() ==  MediaType.MOVIE) {
            return new TheMovieDBSearchParser(query).getResults();
        } else {
            throw new Exception("Unsupported Search Type: " + query.getMediaType());
        }
    }

    /**
     * This is the api key provided for the Batch Metadata Tools. Other projects
     * MUST NOT use this key. If you are including these tools in your project,
     * be sure to set the following System property, to set your own key. <code>
     * themoviedb.api_key=YOUR_KEY
     * </code>
     */
    public static Object getApiKey() {
        String key = System.getProperty("themoviedb.api_key");
        if (key == null) key = "d4ad46ee51d364386b6cf3b580fb5d8c";
        return key;
    }

    public MediaType[] getSupportedSearchTypes() {
        return supportedSearchTypes;
    }

    public String getUrlForId(MetadataID id) throws Exception {
        if (IMDBMetaDataProvider.PROVIDER_ID.equals(id.getProvider())) {
            // imdb lookup
            TheMovieDBItemParser p = new TheMovieDBItemParser(String.format(TheMovieDBItemParser.IMDB_ITEM_URL, id.getId()));
            if (p.getMetadata() != null) {
                return String.format(TheMovieDBItemParser.ITEM_URL, p.getTheMovieDBID());
            }
        } else if ("themoviedb".equals(id.getProvider()) || PROVIDER_ID.equals(id.getProvider())) {
            // normal moviedb lookup
            return String.format(TheMovieDBItemParser.ITEM_URL, id.getId());
        }
        throw new Exception("Failed to get metadata by Id: " + id);
    }

    public IMediaMetadata getMetaDataByUrl(String url) throws Exception {
        return new TheMovieDBItemParser(url).getMetadata();
    }
}
