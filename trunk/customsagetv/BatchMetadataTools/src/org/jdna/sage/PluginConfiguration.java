package org.jdna.sage;

import sagex.phoenix.configuration.proxy.AField;
import sagex.phoenix.configuration.proxy.AGroup;
import sagex.phoenix.configuration.proxy.FieldProxy;
import sagex.phoenix.configuration.proxy.GroupProxy;

@AGroup(label="Automatic Plugin Configuration", path = "bmt/plugin", description = "Configuration for Automatic Plugin")
public class PluginConfiguration extends GroupProxy {
    @AField(label="Enabled", description="Enable/Disable the Automatic Plugin (does not remove it)")
    private FieldProxy<Boolean> enabled = new FieldProxy<Boolean>(true);

    @AField(label="Overwrite Metadata", description="Overwrite Existing Metadata Configuration")
    private FieldProxy<Boolean> overwriteMetadata = new FieldProxy<Boolean>(false);

    @AField(label="Overwrite Fanart", description="Overwrite Existing Fanart")
    private FieldProxy<Boolean> overwriteFanart = new FieldProxy<Boolean>(false);

    @AField(label="Direct Update of Wiz.bin", description="Update the Wiz.bin directly")
    private FieldProxy<Boolean> updateWizBin = new FieldProxy<Boolean>(false);

    @AField(label="Import TV as Sage Recordings", description="Import TV as SageTV recordings. (Will force Update Wiz.bin to true)")
    private FieldProxy<Boolean> importTVAsRecordings = new FieldProxy<Boolean>(false);
    
    @AField(label="Record Failures as System Message", description="Write a SystemMessage for any failed media items")
    private FieldProxy<Boolean> useSystemMessagesForFailed = new FieldProxy<Boolean>(true);

    @AField(label="Push status updates using System Messages", description="If true, then when a bunch of mediafile has been scanned, then a status of success and failed counts will be sent as a system message")
    private FieldProxy<Boolean> useSystemMessagesForStatus = new FieldProxy<Boolean>(true);

    @AField(label="Supported MediaResource File Types", description="Comma Separated list of Media Resource Types that the Automatic scanner will support; TV, DVD, BLURAY, ANY_VIDEO, MUSIC, VIDEO")
    private FieldProxy<String> supportedMediaTypes = new FieldProxy<String>("ANY_VIDEO");

    @AField(label="Create Default STV Thumbnail", description="If true, then create a default STV thumbnail")
    private FieldProxy<Boolean> createDefaultSTVThumbnail = new FieldProxy<Boolean>(Boolean.FALSE);

    public PluginConfiguration() {
        super();
        init();
    }

    public boolean getOverwriteMetadata() {
        return overwriteMetadata.get();
    }

    public void setOverwriteMetadata(boolean overwriteMetadata) {
        this.overwriteMetadata.set(overwriteMetadata);
    }

    public boolean getOverwriteFanart() {
        return overwriteFanart.get();
    }

    public void setOverwriteFanart(boolean overwriteFanart) {
        this.overwriteFanart.set(overwriteFanart);
    }

    public boolean getEnabled() {
        return enabled.get();
    }

    public void setEnabled(boolean enabled) {
        this.enabled.set(enabled);
    }

    /**
     * @return the useSystemMessagesForFailed
     */
    public boolean getUseSystemMessagesForFailed() {
        return useSystemMessagesForFailed.get();
    }

    /**
     * @param useSystemMessagesForFailed the useSystemMessagesForFailed to set
     */
    public void setUseSystemMessagesForFailed(boolean useSystemMessagesForFailed) {
        this.useSystemMessagesForFailed.set(useSystemMessagesForFailed);
    }

    /**
     * @return the useSystemMessagesForStatus
     */
    public boolean getUseSystemMessagesForStatus() {
        return useSystemMessagesForStatus.get();
    }

    /**
     * @param useSystemMessagesForStatus the useSystemMessagesForStatus to set
     */
    public void setUseSystemMessagesForStatus(boolean useSystemMessagesForStatus) {
        this.useSystemMessagesForStatus.set(useSystemMessagesForStatus);
    }

    public void setSupportedMediaTypes(String supportedMediaTypes) {
        this.supportedMediaTypes.set(supportedMediaTypes);
    }

    public String getSupportedMediaTypes() {
        return supportedMediaTypes.get();
    }

    public void setUpdateWizBin(boolean updateWizBin) {
        this.updateWizBin.set(updateWizBin);
    }

    public boolean getUpdateWizBin() {
        return updateWizBin.get();
    }

    public void setImportTVAsRecordings(boolean importTVAsRecordings) {
        this.importTVAsRecordings.set(importTVAsRecordings);
    }

    public boolean getImportTVAsRecordings() {
        return importTVAsRecordings.get();
    }
    
    /**
     * @return the createDefaultSTVThumbnail
     */
    public boolean getCreateDefaultSTVThumbnail() {
        return createDefaultSTVThumbnail.get();
    }

    /**
     * @param createDefaultSTVThumbnail the createDefaultSTVThumbnail to set
     */
    public void setCreateDefaultSTVThumbnail(boolean createDefaultSTVThumbnail) {
        this.createDefaultSTVThumbnail.set(createDefaultSTVThumbnail);
    }

}
