package org.jdna.media.metadata;

import java.io.Serializable;

public class MediaSearchResult implements IMediaSearchResult, Serializable {
    private static final long serialVersionUID = 2L;

    private String            providerId, url, title, year;
    private MetadataID metadataId;
    private float			score;
    
    public MediaSearchResult() {
    	
    }
    
    public MediaSearchResult(String providerId, float score) {
        this.providerId = providerId;
        this.score = score;
    }

    public MediaSearchResult(String providerId, String url, String title, String year, float score) {
        super();
        this.providerId = providerId;
        this.url = url;
        this.title = title;
        this.year = year;
        this.score = score;
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
    
    public float getScore(){
    	return score;
    }
    
    public void setScore(float score){
    	this.score = score;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url=url;
    }

    public MetadataID getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(MetadataID id) {
        this.metadataId = id;
    }
}