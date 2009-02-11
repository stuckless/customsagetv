package org.jdna.media.metadata;

import java.io.Serializable;

public class MediaSearchResult implements IMediaSearchResult, Serializable {
    private static final long serialVersionUID = 1L;

    private String            providerId, url, title, year, imdbId;
    private SearchResultType  resultType = SearchResultType.UNKNOWN;

    public MediaSearchResult() {
    }

    public MediaSearchResult(String providerId, SearchResultType resultType) {
        this.providerId = providerId;
        this.resultType = resultType;
    }

    public MediaSearchResult(String providerId, String url, String title, String year, SearchResultType resultType) {
        super();
        this.providerId = providerId;
        this.url = url;
        this.title = title;
        this.year = year;
        this.resultType = resultType;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public SearchResultType getResultType() {
        return resultType;
    }

    public void setResultType(SearchResultType resultType) {
        this.resultType = resultType;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url=url;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
