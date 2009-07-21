package sagex.remote.factory.request;

/**
 * Unofficial SageTV Generated File - Never Edit
 * Generated Date/Time: 20/07/09 5:47 PM
 * See Official Sage Documentation at <a href='http://download.sage.tv/api/sage/api/ConfigurationFactory.html'>ConfigurationFactory</a>
 * This Generated API is not Affiliated with SageTV.  It is user contributed.
 */

import java.util.Map;
import sagex.remote.RemoteRequest;
import sagex.remote.xmlrpc.RequestHelper;

public class ConfigurationFactory {
   public static RemoteRequest createRequest(String context, String command, String[] parameters) {
   if (command.equals("SaveProperties")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SaveProperties",parameters,null);
   }
   if (command.equals("GetLibraryImportPaths")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetLibraryImportPaths",parameters,null);
   }
   if (command.equals("GetPictureLibraryImportPaths")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetPictureLibraryImportPaths",parameters,null);
   }
   if (command.equals("GetMusicLibraryImportPaths")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetMusicLibraryImportPaths",parameters,null);
   }
   if (command.equals("GetVideoLibraryImportPaths")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoLibraryImportPaths",parameters,null);
   }
   if (command.equals("IsMajorMinorDTVChannelTuningEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsMajorMinorDTVChannelTuningEnabled",parameters,null);
   }
   if (command.equals("GetAutodialForInternet")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAutodialForInternet",parameters,null);
   }
   if (command.equals("SetAutodialForInternet")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAutodialForInternet",parameters,boolean.class);
   }
   if (command.equals("IsProvideoPentium4FixEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsProvideoPentium4FixEnabled",parameters,null);
   }
   if (command.equals("SetProvideoPentium4FixEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetProvideoPentium4FixEnabled",parameters,boolean.class);
   }
   if (command.equals("GetInfraredTuningPlugins")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetInfraredTuningPlugins",parameters,null);
   }
   if (command.equals("GetRemotesForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetRemotesForInfraredTuningPlugin",parameters,java.lang.String.class,int.class);
   }
   if (command.equals("AddRemoteForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"AddRemoteForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("LearnCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"LearnCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("RenameCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RenameCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("RemoveRemoteForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveRemoteForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("RemoveCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("SetMaxChannelDigitsForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetMaxChannelDigitsForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,int.class);
   }
   if (command.equals("GetMaxChannelDigitsForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetMaxChannelDigitsForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("SetButtonDelayForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetButtonDelayForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,int.class);
   }
   if (command.equals("GetButtonDelayForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetButtonDelayForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("SetSequenceDelayForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetSequenceDelayForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,int.class);
   }
   if (command.equals("GetSequenceDelayForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSequenceDelayForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("SetConfirmCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetConfirmCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("SetPrefixCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetPrefixCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("GetConfirmCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetConfirmCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("GetPrefixCommandForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetPrefixCommandForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("GetCarrierFrequencyForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetCarrierFrequencyForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("GetBitTimeForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetBitTimeForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("GetCommandsForInfraredTuningPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetCommandsForInfraredTuningPlugin",parameters,java.lang.String.class,int.class,java.lang.String.class);
   }
   if (command.equals("GetSubpropertiesThatAreBranches")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSubpropertiesThatAreBranches",parameters,java.lang.String.class);
   }
   if (command.equals("GetSubpropertiesThatAreLeaves")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSubpropertiesThatAreLeaves",parameters,java.lang.String.class);
   }
   if (command.equals("GetServerSubpropertiesThatAreBranches")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetServerSubpropertiesThatAreBranches",parameters,java.lang.String.class);
   }
   if (command.equals("GetServerSubpropertiesThatAreLeaves")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetServerSubpropertiesThatAreLeaves",parameters,java.lang.String.class);
   }
   if (command.equals("GetProperty")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetProperty",parameters,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("SetProperty")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetProperty",parameters,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("GetServerProperty")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetServerProperty",parameters,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("SetServerProperty")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetServerProperty",parameters,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("RemoveProperty")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveProperty",parameters,java.lang.String.class);
   }
   if (command.equals("RemovePropertyAndChildren")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemovePropertyAndChildren",parameters,java.lang.String.class);
   }
   if (command.equals("RemoveServerProperty")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveServerProperty",parameters,java.lang.String.class);
   }
   if (command.equals("RemoveServerPropertyAndChildren")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveServerPropertyAndChildren",parameters,java.lang.String.class);
   }
   if (command.equals("GetDefaultRecordingQuality")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDefaultRecordingQuality",parameters,null);
   }
   if (command.equals("IsIntelligentRecordingDisabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsIntelligentRecordingDisabled",parameters,null);
   }
   if (command.equals("AddLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"AddLibraryImportPath",parameters,java.lang.String.class);
   }
   if (command.equals("AddPictureLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"AddPictureLibraryImportPath",parameters,java.lang.String.class);
   }
   if (command.equals("AddMusicLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"AddMusicLibraryImportPath",parameters,java.lang.String.class);
   }
   if (command.equals("AddVideoLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"AddVideoLibraryImportPath",parameters,java.lang.String.class);
   }
   if (command.equals("RemoveLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveLibraryImportPath",parameters,java.io.File.class);
   }
   if (command.equals("RemovePictureLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemovePictureLibraryImportPath",parameters,java.io.File.class);
   }
   if (command.equals("RemoveMusicLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveMusicLibraryImportPath",parameters,java.io.File.class);
   }
   if (command.equals("RemoveVideoLibraryImportPath")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveVideoLibraryImportPath",parameters,java.io.File.class);
   }
   if (command.equals("SetDefaultRecordingQuality")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDefaultRecordingQuality",parameters,java.lang.String.class);
   }
   if (command.equals("SetIntelligentRecordingDisabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetIntelligentRecordingDisabled",parameters,boolean.class);
   }
   if (command.equals("SetMajorMinorDTVChannelTuningEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetMajorMinorDTVChannelTuningEnabled",parameters,boolean.class);
   }
   if (command.equals("IsScreenSaverOnSleepEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsScreenSaverOnSleepEnabled",parameters,null);
   }
   if (command.equals("SetScreenSaverOnSleepEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetScreenSaverOnSleepEnabled",parameters,boolean.class);
   }
   if (command.equals("IsSyncSystemClockWithServerEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsSyncSystemClockWithServerEnabled",parameters,null);
   }
   if (command.equals("SetSyncSystemClockWithServerEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetSyncSystemClockWithServerEnabled",parameters,boolean.class);
   }
   if (command.equals("GetAspectRatioMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAspectRatioMode",parameters,null);
   }
   if (command.equals("SetAspectRatioMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAspectRatioMode",parameters,java.lang.String.class);
   }
   if (command.equals("GetAspectRatioModes")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAspectRatioModes",parameters,null);
   }
   if (command.equals("GetDisplayAspectRatio")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDisplayAspectRatio",parameters,null);
   }
   if (command.equals("SetDisplayAspectRatio")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDisplayAspectRatio",parameters,float.class);
   }
   if (command.equals("IsParentalControlEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsParentalControlEnabled",parameters,null);
   }
   if (command.equals("SetParentalControlEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetParentalControlEnabled",parameters,boolean.class);
   }
   if (command.equals("IsMutedOnAlternateSpeedPlayback")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsMutedOnAlternateSpeedPlayback",parameters,null);
   }
   if (command.equals("SetMuteOnAlternateSpeedPlayback")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetMuteOnAlternateSpeedPlayback",parameters,boolean.class);
   }
   if (command.equals("HasParentalLockCode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"HasParentalLockCode",parameters,null);
   }
   if (command.equals("GetRatingsUnderParentalLock")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetRatingsUnderParentalLock",parameters,null);
   }
   if (command.equals("IsRatingUnderParentalLock")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsRatingUnderParentalLock",parameters,java.lang.String.class);
   }
   if (command.equals("GetVideoBackgroundColor")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoBackgroundColor",parameters,null);
   }
   if (command.equals("SetVideoBackgroundColor")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoBackgroundColor",parameters,java.awt.Color.class);
   }
   if (command.equals("GetVideoOrbitingDuration")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoOrbitingDuration",parameters,null);
   }
   if (command.equals("SetVideoOrbitingDuration")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoOrbitingDuration",parameters,long.class);
   }
   if (command.equals("GetVideoZoomX")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoZoomX",parameters,java.lang.String.class);
   }
   if (command.equals("SetVideoZoomX")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoZoomX",parameters,java.lang.String.class,float.class);
   }
   if (command.equals("GetVideoZoomY")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoZoomY",parameters,java.lang.String.class);
   }
   if (command.equals("SetVideoZoomY")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoZoomY",parameters,java.lang.String.class,float.class);
   }
   if (command.equals("GetVideoOffsetX")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoOffsetX",parameters,java.lang.String.class);
   }
   if (command.equals("SetVideoOffsetX")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoOffsetX",parameters,java.lang.String.class,int.class);
   }
   if (command.equals("GetVideoOffsetY")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoOffsetY",parameters,java.lang.String.class);
   }
   if (command.equals("SetVideoOffsetY")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoOffsetY",parameters,java.lang.String.class,int.class);
   }
   if (command.equals("SetParentalLockCode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetParentalLockCode",parameters,java.lang.String.class);
   }
   if (command.equals("GetRecordingQualities")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetRecordingQualities",parameters,null);
   }
   if (command.equals("GetRecordingQualityBitrate")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetRecordingQualityBitrate",parameters,java.lang.String.class);
   }
   if (command.equals("GetRecordingQualityFormat")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetRecordingQualityFormat",parameters,java.lang.String.class);
   }
   if (command.equals("GetVideoDirectories")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoDirectories",parameters,null);
   }
   if (command.equals("GetDiskspaceRuleForVideoDirectory")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDiskspaceRuleForVideoDirectory",parameters,java.io.File.class);
   }
   if (command.equals("GetDiskspaceSizeForVideoDirectory")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDiskspaceSizeForVideoDirectory",parameters,java.io.File.class);
   }
   if (command.equals("RemoveVideoDirectory")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RemoveVideoDirectory",parameters,java.io.File.class);
   }
   if (command.equals("AddVideoDirectory")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"AddVideoDirectory",parameters,java.lang.String.class,java.lang.String.class,long.class);
   }
   if (command.equals("ChangeVideoDirectory")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"ChangeVideoDirectory",parameters,java.io.File.class,java.io.File.class,java.lang.String.class,long.class);
   }
   if (command.equals("GetVideoDiskspaceRules")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoDiskspaceRules",parameters,null);
   }
   if (command.equals("GetIRCodesLinkedToSageCommand")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetIRCodesLinkedToSageCommand",parameters,java.lang.String.class);
   }
   if (command.equals("GetKeystrokesLinkedToSageCommand")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetKeystrokesLinkedToSageCommand",parameters,java.lang.String.class);
   }
   if (command.equals("GetNameForIRCode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetNameForIRCode",parameters,long.class);
   }
   if (command.equals("GetSageCommandLinkedToIRCode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSageCommandLinkedToIRCode",parameters,long.class);
   }
   if (command.equals("GetSageCommandLinkedToKeystroke")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetSageCommandLinkedToKeystroke",parameters,java.lang.String.class);
   }
   if (command.equals("SetNameForIRCode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetNameForIRCode",parameters,long.class,java.lang.String.class);
   }
   if (command.equals("LinkIRCodeToSageCommand")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"LinkIRCodeToSageCommand",parameters,long.class,java.lang.String.class);
   }
   if (command.equals("LinkKeystrokeToSageCommand")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"LinkKeystrokeToSageCommand",parameters,java.lang.String.class,java.lang.String.class);
   }
   if (command.equals("UnlinkIRCodeFromSageCommands")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"UnlinkIRCodeFromSageCommands",parameters,long.class);
   }
   if (command.equals("UnlinkKeystrokeFromSageCommands")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"UnlinkKeystrokeFromSageCommands",parameters,java.lang.String.class);
   }
   if (command.equals("SetRatingUnderParentalControl")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetRatingUnderParentalControl",parameters,java.lang.String.class,boolean.class);
   }
   if (command.equals("GetInfraredReceiverPort")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetInfraredReceiverPort",parameters,null);
   }
   if (command.equals("SetInfraredReceiverPort")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetInfraredReceiverPort",parameters,java.lang.String.class);
   }
   if (command.equals("GetInactivityTimeout")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetInactivityTimeout",parameters,null);
   }
   if (command.equals("SetInactivityTimeout")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetInactivityTimeout",parameters,int.class);
   }
   if (command.equals("GetScreenSaverTimeout")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetScreenSaverTimeout",parameters,null);
   }
   if (command.equals("SetScreenSaverTimeout")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetScreenSaverTimeout",parameters,int.class);
   }
   if (command.equals("IsSageTVServerEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsSageTVServerEnabled",parameters,null);
   }
   if (command.equals("SetSageTVServerEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetSageTVServerEnabled",parameters,boolean.class);
   }
   if (command.equals("IsAcceleratedDrawingEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsAcceleratedDrawingEnabled",parameters,null);
   }
   if (command.equals("SetAcceleratedDrawingEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAcceleratedDrawingEnabled",parameters,boolean.class);
   }
   if (command.equals("GetOverscanOffsetX")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetOverscanOffsetX",parameters,null);
   }
   if (command.equals("GetOverscanOffsetY")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetOverscanOffsetY",parameters,null);
   }
   if (command.equals("GetOverscanScaleWidth")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetOverscanScaleWidth",parameters,null);
   }
   if (command.equals("GetOverscanScaleHeight")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetOverscanScaleHeight",parameters,null);
   }
   if (command.equals("SetOverscanOffsetX")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetOverscanOffsetX",parameters,int.class);
   }
   if (command.equals("SetOverscanOffsetY")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetOverscanOffsetY",parameters,int.class);
   }
   if (command.equals("SetOverscanScaleWidth")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetOverscanScaleWidth",parameters,float.class);
   }
   if (command.equals("SetOverscanScaleHeight")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetOverscanScaleHeight",parameters,float.class);
   }
   if (command.equals("GetAvailableCountries")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAvailableCountries",parameters,null);
   }
   if (command.equals("SetCountry")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetCountry",parameters,java.lang.String.class);
   }
   if (command.equals("GetCountry")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetCountry",parameters,null);
   }
   if (command.equals("SetAnalogVideoFormat")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAnalogVideoFormat",parameters,java.lang.String.class);
   }
   if (command.equals("GetAnalogVideoFormat")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAnalogVideoFormat",parameters,null);
   }
   if (command.equals("GetUILanguage")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetUILanguage",parameters,null);
   }
   if (command.equals("SetUILanguage")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetUILanguage",parameters,java.lang.String.class);
   }
   if (command.equals("GetAvailableUILanguages")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAvailableUILanguages",parameters,null);
   }
   if (command.equals("GetTimeZone")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetTimeZone",parameters,null);
   }
   if (command.equals("SetTimeZone")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetTimeZone",parameters,java.lang.String.class);
   }
   if (command.equals("GetAllTimeZones")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAllTimeZones",parameters,null);
   }
   if (command.equals("GetDVBTRegions")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVBTRegions",parameters,null);
   }
   if (command.equals("SetDVBTRegion")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVBTRegion",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVBTRegion")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVBTRegion",parameters,null);
   }
   if (command.equals("GetDVBCRegions")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVBCRegions",parameters,null);
   }
   if (command.equals("SetDVBCRegion")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVBCRegion",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVBCRegion")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVBCRegion",parameters,null);
   }
   if (command.equals("GetDVBSRegions")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVBSRegions",parameters,null);
   }
   if (command.equals("SetDVBSRegion")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVBSRegion",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVBSRegion")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVBSRegion",parameters,null);
   }
   if (command.equals("CanDoNativeOutputResolution")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"CanDoNativeOutputResolution",parameters,null);
   }
   if (command.equals("IsNativeOutputResolutionEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsNativeOutputResolutionEnabled",parameters,null);
   }
   if (command.equals("SetNativeOutputResolutionEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetNativeOutputResolutionEnabled",parameters,boolean.class);
   }
   if (command.equals("GetAudioOutput")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioOutput",parameters,null);
   }
   if (command.equals("GetAudioOutputOptions")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioOutputOptions",parameters,null);
   }
   if (command.equals("SetAudioOutput")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAudioOutput",parameters,java.lang.String.class);
   }
   if (command.equals("GetHDMIAutodetectedConnector")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetHDMIAutodetectedConnector",parameters,null);
   }
   if (command.equals("RunFilterAutoconfiguration")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"RunFilterAutoconfiguration",parameters,null);
   }
   if (command.equals("GetDXVAMpegMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDXVAMpegMode",parameters,null);
   }
   if (command.equals("SetDXVAMpegMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDXVAMpegMode",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVDDXVAMpegMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVDDXVAMpegMode",parameters,null);
   }
   if (command.equals("SetDVDDXVAMpegMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVDDXVAMpegMode",parameters,java.lang.String.class);
   }
   if (command.equals("GetDXVAMpegModes")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDXVAMpegModes",parameters,null);
   }
   if (command.equals("GetDXVADeinterlaceMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDXVADeinterlaceMode",parameters,null);
   }
   if (command.equals("SetDXVADeinterlaceMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDXVADeinterlaceMode",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVDDXVADeinterlaceMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVDDXVADeinterlaceMode",parameters,null);
   }
   if (command.equals("SetDVDDXVADeinterlaceMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVDDXVADeinterlaceMode",parameters,java.lang.String.class);
   }
   if (command.equals("GetDXVADeinterlaceModes")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDXVADeinterlaceModes",parameters,null);
   }
   if (command.equals("GetDscalerMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDscalerMode",parameters,null);
   }
   if (command.equals("SetDscalerMode")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDscalerMode",parameters,java.lang.String.class);
   }
   if (command.equals("GetDscalerModes")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDscalerModes",parameters,null);
   }
   if (command.equals("GetAudioDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioDecoderFilter",parameters,null);
   }
   if (command.equals("SetAudioDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAudioDecoderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVDAudioDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVDAudioDecoderFilter",parameters,null);
   }
   if (command.equals("SetDVDAudioDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVDAudioDecoderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetAudioRenderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioRenderFilter",parameters,null);
   }
   if (command.equals("SetAudioRenderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAudioRenderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVDAudioRenderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVDAudioRenderFilter",parameters,null);
   }
   if (command.equals("SetDVDAudioRenderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVDAudioRenderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVDVideoRenderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVDVideoRenderFilter",parameters,null);
   }
   if (command.equals("SetDVDVideoRenderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVDVideoRenderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetVideoDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoDecoderFilter",parameters,null);
   }
   if (command.equals("SetVideoDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoDecoderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetDVDVideoDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDVDVideoDecoderFilter",parameters,null);
   }
   if (command.equals("SetDVDVideoDecoderFilter")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDVDVideoDecoderFilter",parameters,java.lang.String.class);
   }
   if (command.equals("GetAudioDelay")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioDelay",parameters,null);
   }
   if (command.equals("SetAudioDelay")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAudioDelay",parameters,int.class);
   }
   if (command.equals("GetAudioDecoderFilters")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioDecoderFilters",parameters,null);
   }
   if (command.equals("GetVideoDecoderFilters")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetVideoDecoderFilters",parameters,null);
   }
   if (command.equals("IsSoftwareDecodingDisabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsSoftwareDecodingDisabled",parameters,null);
   }
   if (command.equals("SetSoftwareDecodingDisabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetSoftwareDecodingDisabled",parameters,boolean.class);
   }
   if (command.equals("IsDscalerDoubleRefresh")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsDscalerDoubleRefresh",parameters,null);
   }
   if (command.equals("SetDscalerDoubleRefresh")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDscalerDoubleRefresh",parameters,boolean.class);
   }
   if (command.equals("IsDscalerOddFieldFirst")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsDscalerOddFieldFirst",parameters,null);
   }
   if (command.equals("SetDscalerOddFieldFirst")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDscalerOddFieldFirst",parameters,boolean.class);
   }
   if (command.equals("HasPVR350TVOutput")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"HasPVR350TVOutput",parameters,null);
   }
   if (command.equals("IsPVR350TVOutputEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsPVR350TVOutputEnabled",parameters,null);
   }
   if (command.equals("SetPVR350TVOutputEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetPVR350TVOutputEnabled",parameters,boolean.class);
   }
   if (command.equals("IsPVR350OSDEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsPVR350OSDEnabled",parameters,null);
   }
   if (command.equals("SetPVR350OSDEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetPVR350OSDEnabled",parameters,boolean.class);
   }
   if (command.equals("IsDscalerEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsDscalerEnabled",parameters,null);
   }
   if (command.equals("SetDscalerEnabled")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDscalerEnabled",parameters,boolean.class);
   }
   if (command.equals("GetDscalerPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDscalerPlugin",parameters,null);
   }
   if (command.equals("SetDscalerPlugin")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetDscalerPlugin",parameters,java.lang.String.class);
   }
   if (command.equals("GetDscalerPlugins")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetDscalerPlugins",parameters,null);
   }
   if (command.equals("HasDirectX9")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"HasDirectX9",parameters,null);
   }
   if (command.equals("HasEVRSupport")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"HasEVRSupport",parameters,null);
   }
   if (command.equals("IsVideoRendererOverlay")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsVideoRendererOverlay",parameters,null);
   }
   if (command.equals("IsVideoRendererVMR9")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsVideoRendererVMR9",parameters,null);
   }
   if (command.equals("IsVideoRendererEVR")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsVideoRendererEVR",parameters,null);
   }
   if (command.equals("IsVideoRendererDefault")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsVideoRendererDefault",parameters,null);
   }
   if (command.equals("SetVideoRendererToDefault")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoRendererToDefault",parameters,null);
   }
   if (command.equals("SetVideoRendererToOverlay")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoRendererToOverlay",parameters,null);
   }
   if (command.equals("SetVideoRendererToVMR9")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoRendererToVMR9",parameters,null);
   }
   if (command.equals("SetVideoRendererToEVR")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetVideoRendererToEVR",parameters,null);
   }
   if (command.equals("GetAudioRendererFilters")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetAudioRendererFilters",parameters,null);
   }
   if (command.equals("GetMixerAudioInputPaths")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"GetMixerAudioInputPaths",parameters,null);
   }
   if (command.equals("SetAllowPlaceshifterLoginCaching")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"SetAllowPlaceshifterLoginCaching",parameters,boolean.class);
   }
   if (command.equals("IsPlaceshifterLoginCachingAllowed")) {
      return sagex.remote.xmlrpc.RequestHelper.createRequest(context,"IsPlaceshifterLoginCachingAllowed",parameters,null);
   }
   throw new RuntimeException("Invalid ConfigurationFactory Command: "+command);
   }


}
