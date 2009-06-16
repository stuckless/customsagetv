package org.jdna.media.util;

import org.apache.commons.lang.StringUtils;
import org.jdna.media.IMediaResource;
import org.jdna.media.IMediaResourceVisitor;
import org.jdna.media.metadata.IMediaMetadata;
import org.jdna.media.metadata.IMediaMetadataPersistence;
import org.jdna.media.metadata.MetadataAPI;

/**
 * Resource Visitor that collects ONLY resources that contain missing metadata.
 * 
 * @author seans
 * 
 */
public class MissingMetadataVisitor implements IMediaResourceVisitor {
    private IMediaResourceVisitor missingVisitor;
    private IMediaMetadataPersistence persistence=null;

    /**
     * 
     * @param persistence
     *            Where to load the metadata (required)
     * @param missingVisitor
     *            visitor to receive the missing metadata resources (optional)
     */
    public MissingMetadataVisitor(IMediaMetadataPersistence persistence, IMediaResourceVisitor missingVisitor) {
        this.persistence=persistence;
        this.missingVisitor=missingVisitor;
    }

    public void visit(IMediaResource resource) {
        if (resource.getType() == IMediaResource.Type.File) {
            if (isMissingMetadata(persistence, resource)) {
                missingVisitor.visit(resource);
            }
        }
    }

    public static boolean isMissingMetadata(IMediaMetadataPersistence persistence, IMediaResource resource) {
        if (resource.getType() == IMediaResource.Type.File) {
            try {
                IMediaMetadata md = persistence.loadMetaData(resource);

              // TODO: Use Phoenix_HasFanart() to check for missing fanart
                
                if (md == null || StringUtils.isEmpty(MetadataAPI.getMediaTitle(md))) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
        }
        return false;
    }

    protected IMediaResourceVisitor getMissingVisitor() {
        return missingVisitor;
    }
}
