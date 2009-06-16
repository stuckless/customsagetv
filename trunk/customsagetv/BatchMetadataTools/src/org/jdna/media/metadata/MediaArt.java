package org.jdna.media.metadata;

import java.io.Serializable;

import sagex.phoenix.fanart.MediaArtifactType;

public class MediaArt implements IMediaArt, Serializable {
    private static final long serialVersionUID = 1L;
    private String downloadUrl;
    private String providerId;
    private MediaArtifactType type;
    private String label;
    private int season;

    public MediaArt() {
    }

    public MediaArt(IMediaArt ma) {
        if (ma != null) {
            this.providerId = ma.getProviderId();
            this.downloadUrl = ma.getDownloadUrl();
            this.type = ma.getType();
            this.label = ma.getLabel();
        }
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public MediaArtifactType getType() {
        return type;
    }

    public void setType(MediaArtifactType type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
