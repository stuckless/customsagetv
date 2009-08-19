package test;

import org.jdna.media.metadata.MediaArt;
import org.jdna.media.metadata.MediaMetadata;

import sagex.phoenix.fanart.MediaArtifactType;

public class TestJSONObjectWrapper {
    public static void main(String args[]) {
        MediaMetadata md = new MediaMetadata();
        md.setDescription("test");
        
        MediaArt ma = new MediaArt();
        ma.setSeason(3);
        ma.setType(MediaArtifactType.POSTER);
        ma.setDownloadUrl("test://dets");
        md.addMediaArt(ma);

    }
}
