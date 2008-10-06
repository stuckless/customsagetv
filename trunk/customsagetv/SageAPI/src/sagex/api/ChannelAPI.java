package sagex.api;

/**
 * Unofficial SageTV Generated File - Never Edit Generated Date/Time: 05/10/08
 * 4:56 PM See Official Sage Documentation at <a
 * href='http://download.sage.tv/api/sage/api/ChannelAPI.html'>ChannelAPI</a>
 * This Generated API is not Affiliated with SageTV. It is user contributed.
 */
public class ChannelAPI {
	/**
	 * Gets the descriptive name for this Channel. This is the longer text
	 * string.
	 * 
	 * Parameters: Channel- the Channel Object Returns: the longer descriptive
	 * name for the specified Channel
	 */
	public static java.lang.String GetChannelDescription(Object Channel) {
		return (java.lang.String) sagex.SageAPI.call("GetChannelDescription", new Object[] { Channel });
	}

	/**
	 * Gets the name for this Channel. This is the Channel's call sign.
	 * 
	 * Parameters: Channel- the Channel Object Returns: the name (call sign) for
	 * the specified Channel
	 */
	public static java.lang.String GetChannelName(Object Channel) {
		return (java.lang.String) sagex.SageAPI.call("GetChannelName", new Object[] { Channel });
	}

	/**
	 * Gets the name of the associated network for this Channel. This is a way
	 * of grouping kinds of Channels together.
	 * 
	 * Parameters: Channel- the Channel Object Returns: the network name for the
	 * specified Channel
	 */
	public static java.lang.String GetChannelNetwork(Object Channel) {
		return (java.lang.String) sagex.SageAPI.call("GetChannelNetwork", new Object[] { Channel });
	}

	/**
	 * Gets the channel number to tune to for this Channel. SageTV will return
	 * the first channel number it finds for this Channel since there may be
	 * multiple ones.
	 * 
	 * Parameters: Channel- the Channel Object Returns: a channel number
	 * associated with this Channel
	 */
	public static java.lang.String GetChannelNumber(Object Channel) {
		return (java.lang.String) sagex.SageAPI.call("GetChannelNumber", new Object[] { Channel });
	}

	/**
	 * Gets the channel number to tune to for this Channel on the specified
	 * lineup. SageTV will return the first channel number it finds for this
	 * Channel on the lineup since there may be multiple ones.
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Returns: the channel number for the specified Channel on the specified
	 * Lineup
	 */
	public static java.lang.String GetChannelNumberForLineup(Object Channel, java.lang.String Lineup) {
		return (java.lang.String) sagex.SageAPI.call("GetChannelNumberForLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Gets the physical channel number to tune to for this Channel on the
	 * specified lineup.
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Returns: the physical channel number for the specified Channel on the
	 * specified Lineup Since: 5.1
	 */
	public static java.lang.String GetPhysicalChannelNumberForLineup(Object Channel, java.lang.String Lineup) {
		return (java.lang.String) sagex.SageAPI.call("GetPhysicalChannelNumberForLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Returns true if there is a configured lineup for which this channel is
	 * viewable.
	 * 
	 * Parameters: Channel- the Channel object Returns: true if there is a
	 * configured lineup for which this channel is viewable.
	 */
	public static boolean IsChannelViewable(Object Channel) {
		return (Boolean) sagex.SageAPI.call("IsChannelViewable", new Object[] { Channel });
	}

	/**
	 * Returns true if this Channel is viewable on the specified Lineup
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Returns: true if this Channel is viewable on the specified Lineup
	 */
	public static boolean IsChannelViewableOnLineup(Object Channel, java.lang.String Lineup) {
		return (Boolean) sagex.SageAPI.call("IsChannelViewableOnLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Returns true if this Channel is viewable on the specified Lineup on the
	 * specified channel number
	 * 
	 * Parameters: Channel- the Channel object ChannelNumber- the channel number
	 * to check Lineup- the name of the Lineup Returns: true if this Channel is
	 * viewable on the specified Lineup on the specified channel number
	 */
	public static boolean IsChannelViewableOnNumberOnLineup(Object Channel, java.lang.String ChannelNumber, java.lang.String Lineup) {
		return (Boolean) sagex.SageAPI.call("IsChannelViewableOnNumberOnLineup", new Object[] { Channel, ChannelNumber, Lineup });
	}

	/**
	 * Gets the channel numbers which can be used to tune this Channel on the
	 * specified lineup.
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Returns: the channel numbers for the specified Channel on the specified
	 * Lineup
	 */
	public static java.lang.String[] GetChannelNumbersForLineup(Object Channel, java.lang.String Lineup) {
		return (java.lang.String[]) sagex.SageAPI.call("GetChannelNumbersForLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Clears any associated channel mappings that were created for this Channel
	 * on the specified Lineup
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 */
	public static void ClearChannelMappingsOnLineup(Object Channel, java.lang.String Lineup) {
		sagex.SageAPI.call("ClearChannelMappingsOnLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Returns true if the user has remapped this Channel to a different number
	 * than it's default on the specified Lineup
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Returns: true if the user has remapped this Channel to a different number
	 * than it's default on the specified Lineup
	 */
	public static boolean IsChannelRemappedOnLineup(Object Channel, java.lang.String Lineup) {
		return (Boolean) sagex.SageAPI.call("IsChannelRemappedOnLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Maps a channel on a lineup to a new channel number.
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * NewNumber- the new channel number to use for this Channel
	 */
	public static void SetChannelMappingForLineup(Object Channel, java.lang.String Lineup, java.lang.String NewNumber) {
		sagex.SageAPI.call("SetChannelMappingForLineup", new Object[] { Channel, Lineup, NewNumber });
	}

	/**
	 * Maps a channel on a lineup to a new channel number(s).
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * NewNumbers- the new channel numbers to use for this Channel Since: 6.4.3
	 */
	public static void SetChannelMappingsForLineup(Object Channel, java.lang.String Lineup, java.lang.String[] NewNumbers) {
		sagex.SageAPI.call("SetChannelMappingsForLineup", new Object[] { Channel, Lineup, NewNumbers });
	}

	/**
	 * Clears any associated physical channel mappings that were created for
	 * this Channel on the specified Lineup
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Since: 5.1
	 */
	public static void ClearPhysicalChannelMappingsOnLineup(Object Channel, java.lang.String Lineup) {
		sagex.SageAPI.call("ClearPhysicalChannelMappingsOnLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Returns true if the user has remapped this physical Channel to a
	 * different physical number than it's default on the specified Lineup
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Returns: true if the user has remapped this physical Channel to a
	 * different number than it's default on the specified Lineup Since: 5.1
	 */
	public static boolean IsPhysicalChannelRemappedOnLineup(Object Channel, java.lang.String Lineup) {
		return (Boolean) sagex.SageAPI.call("IsPhysicalChannelRemappedOnLineup", new Object[] { Channel, Lineup });
	}

	/**
	 * Maps a Channel on a lineup to a new physical channel number.
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * NewNumber- the new phyical channel number to use for this Channel Since:
	 * 5.1
	 */
	public static void SetPhysicalChannelMappingForLineup(Object Channel, java.lang.String Lineup, java.lang.String NewNumber) {
		sagex.SageAPI.call("SetPhysicalChannelMappingForLineup", new Object[] { Channel, Lineup, NewNumber });
	}

	/**
	 * Returns an ID which can be used withGetChannelForStationID() for doing
	 * keyed lookups of Channel objects
	 * 
	 * Parameters: Channel- the Channel object Returns: the station ID for the
	 * specified Channel
	 */
	public static int GetStationID(Object Channel) {
		return (Integer) sagex.SageAPI.call("GetStationID", new Object[] { Channel });
	}

	/**
	 * Gets the logo image for the specified Channel if one exists
	 * 
	 * Parameters: Channel- the Channel object Returns: the logo image for the
	 * Channel
	 */
	public static Object GetChannelLogo(Object Channel) {
		return (Object) sagex.SageAPI.call("GetChannelLogo", new Object[] { Channel });
	}

	/**
	 * Returns true if the argument is a Channel object. Automatic type
	 * conversion is NOT done in this call.
	 * 
	 * Parameters: Channel- the object to test Returns: true if the argument is
	 * a Channel object
	 */
	public static boolean IsChannelObject(Object Channel) {
		return (Boolean) sagex.SageAPI.call("IsChannelObject", new Object[] { Channel });
	}

	/**
	 * Sets whether or not the specified Channel is viewable on the specified
	 * number on the specified Lineup
	 * 
	 * Parameters: Channel- the Channel object ChannelNumber- the channel number
	 * to set the viewability state for Lineup- the name of the Lineup Viewable-
	 * true if is viewable, false if it is not
	 */
	public static void SetChannelViewabilityForChannelNumberOnLineup(Object Channel, java.lang.String ChannelNumber, java.lang.String Lineup, boolean Viewable) {
		sagex.SageAPI.call("SetChannelViewabilityForChannelNumberOnLineup", new Object[] { Channel, ChannelNumber, Lineup, Viewable });
	}

	/**
	 * Sets whether or not the specified Channel is viewable on the specified
	 * Lineup. This affects all channel numbers it appears on.
	 * 
	 * Parameters: Channel- the Channel object Lineup- the name of the Lineup
	 * Viewable- true if is viewable, false if it is not
	 */
	public static void SetChannelViewabilityForChannelOnLineup(Object Channel, java.lang.String Lineup, boolean Viewable) {
		sagex.SageAPI.call("SetChannelViewabilityForChannelOnLineup", new Object[] { Channel, Lineup, Viewable });
	}

	/**
	 * Returns the Channel object that has the corresponding station ID. The
	 * station ID is retrieved usingGetStationID()
	 * 
	 * 
	 * Parameters: StationID- the station ID to look up Returns: the Channel
	 * with the specified station ID
	 */
	public static Object GetChannelForStationID(int StationID) {
		return (Object) sagex.SageAPI.call("GetChannelForStationID", new Object[] { StationID });
	}

	/**
	 * Adds a new Channel to the database. The CallSign should not match that of
	 * any other channel; but this rule is not enforced. The StationID is what
	 * is used as the unique key to identify a station. Be sure that if you're
	 * creating new station IDs they do not conflict with existing ones. The
	 * safest way to pick a station ID (if you need to at random) is to make it
	 * less than 10000 and ensure that no channel already exists with that
	 * station ID.
	 * 
	 * Parameters: CallSign- the 'Name' to assign to the new Channel Name- the
	 * 'Description' to assign to the new Channel Network- the 'Network' that
	 * the Channel is part of (can be "") StationID- the unique ID to give to
	 * this Channel Returns: the newly created Channel object, if the station ID
	 * is already in use it will return the existing Channel object, but updated
	 * with the passed in values
	 */
	public static Object AddChannel(java.lang.String CallSign, java.lang.String Name, java.lang.String Network, int StationID) {
		return (Object) sagex.SageAPI.call("AddChannel", new Object[] { CallSign, Name, Network, StationID });
	}

	/**
	 * Returns all of the Channels that are defined in the system
	 * 
	 * Returns: all of the Channel objects that are defined in the system
	 */
	public static Object[] GetAllChannels() {
		return (Object[]) sagex.SageAPI.call("GetAllChannels", (Object[]) null);
	}

}