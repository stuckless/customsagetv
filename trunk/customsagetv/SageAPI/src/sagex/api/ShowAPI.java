package sagex.api;

/**
 * Unofficial SageTV Generated File - Never Edit Generated Date/Time: 05/10/08
 * 4:56 PM See Official Sage Documentation at <a
 * href='http://download.sage.tv/api/sage/api/ShowAPI.html'>ShowAPI</a> This
 * Generated API is not Affiliated with SageTV. It is user contributed.
 */
public class ShowAPI {
	/**
	 * If this is true, then two Airings that both represent this Show will
	 * contain the same content. If this is false then it means the EPG metadata
	 * for the content is 'generic' two different Airings each with this Show
	 * for its metadata may actually represent different content
	 * 
	 * Parameters: Show- the Show object Returns: true if all Airings of this
	 * Show represent the same content, false otherwise
	 */
	public static boolean IsShowEPGDataUnique(Object Show) {
		return (Boolean) sagex.SageAPI.call("IsShowEPGDataUnique", new Object[] { Show });
	}

	/**
	 * Returns the miscellaneous metadata for this Show. This includes things
	 * such as the star rating for a movie, the studio a movie was produced at,
	 * etc.
	 * 
	 * Parameters: Show- the Show object Returns: the miscellaneous metadata for
	 * this Show
	 */
	public static java.lang.String GetShowMisc(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowMisc", new Object[] { Show });
	}

	/**
	 * Returns the category for the specified Show. For music files, this will
	 * be the genre.
	 * 
	 * Parameters: Show- the Show object Returns: the category for the Show
	 */
	public static java.lang.String GetShowCategory(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowCategory", new Object[] { Show });
	}

	/**
	 * Returns the subcategory for the specified Show
	 * 
	 * Parameters: Show- the Show object Returns: the subcategory for the Show
	 */
	public static java.lang.String GetShowSubCategory(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowSubCategory", new Object[] { Show });
	}

	/**
	 * Returns the description for the specified Show
	 * 
	 * Parameters: Show- the Show object Returns: the desccription for the Show
	 */
	public static java.lang.String GetShowDescription(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowDescription", new Object[] { Show });
	}

	/**
	 * Returns the episode name for the specified Show. For music files, this
	 * will be the name of the song.
	 * 
	 * Parameters: Show- the Show object Returns: the episode name for the
	 * specified Show. For music files, this will be the name of the song. For
	 * imported videos, this will be the title of the file
	 */
	public static java.lang.String GetShowEpisode(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowEpisode", new Object[] { Show });
	}

	/**
	 * Returns the epxanded ratings information for the specified Show. This
	 * includes thigs like Violence, Nudity, Adult Language, etc.
	 * 
	 * Parameters: Show- the Show object Returns: the expanded ratings for the
	 * Show
	 */
	public static java.lang.String GetShowExpandedRatings(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowExpandedRatings", new Object[] { Show });
	}

	/**
	 * Deprecated.
	 * 
	 * Returns the parental rating for this show. The parental rating field in
	 * Airing is used instead of this in the standard implementation.
	 * 
	 * Parameters: Show- the Show object Returns: the parental rating info for
	 * this show
	 */
	public static java.lang.String GetShowParentalRating(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowParentalRating", new Object[] { Show });
	}

	/**
	 * Returns the MPAA rating for the specified Show (only used for movies).
	 * 
	 * Parameters: Show- the Show object Returns: the MPAA rating for this Show,
	 * will be one of: G, PG, R, PG-13, etc.
	 */
	public static java.lang.String GetShowRated(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowRated", new Object[] { Show });
	}

	/**
	 * Returns the duration of the specified Show. Most Shows do not contain
	 * duration information, with the exception of movies whose show duration
	 * indicates the runing time of the movie.
	 * 
	 * Parameters: Show- the Show object Returns: the duration in milliseconds
	 * of the specified Show, 0 if it is not set
	 */
	public static long GetShowDuration(Object Show) {
		return (Long) sagex.SageAPI.call("GetShowDuration", new Object[] { Show });
	}

	/**
	 * Returns the title of the specified Show. For music this will correspond
	 * to the Album name. For imported videos, For imported videos, this will be
	 * the title of the file with the relative import path as it's prefix.
	 * 
	 * Parameters: Show- the Show object Returns: the title of the specified
	 * Show
	 */
	public static java.lang.String GetShowTitle(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowTitle", new Object[] { Show });
	}

	/**
	 * Gets the year of the specified Show. This is usually only valid for
	 * movies.
	 * 
	 * Parameters: Show- the Show object Returns: the year the specified Show
	 * was produced in
	 */
	public static java.lang.String GetShowYear(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowYear", new Object[] { Show });
	}

	/**
	 * Gets the global unique ID which is used to identify Shows. This ID is
	 * common among all SageTV users.
	 * 
	 * Parameters: Show- the Show object Returns: the global unique ID which
	 * represents this Show
	 */
	public static java.lang.String GetShowExternalID(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowExternalID", new Object[] { Show });
	}

	/**
	 * Gets the date that this Show was originally aired at.
	 * 
	 * Parameters: Show- the Show object Returns: the date that this Show was
	 * originally aired at, same units as java.lang.System.currentTimeMillis()
	 */
	public static long GetOriginalAiringDate(Object Show) {
		return (Long) sagex.SageAPI.call("GetOriginalAiringDate", new Object[] { Show });
	}

	/**
	 * Gets a list of all of the valid roles that people can have in a Show
	 * 
	 * Returns: a list of all of the valid roles that people can have in a Show
	 */
	public static java.lang.String[] GetRoleTypes() {
		return (java.lang.String[]) sagex.SageAPI.call("GetRoleTypes", (Object[]) null);
	}

	/**
	 * Gets a list of all of the people involved in this Show. The order of the
	 * returned list will correlate with the values returned fromGetRolesInShow .
	 * 
	 * Parameters: Show- the Show object Returns: a list of all of the people
	 * involved in this Show as a comma separated list
	 */
	public static java.lang.String GetPeopleInShow(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetPeopleInShow", new Object[] { Show });
	}

	/**
	 * Gets a list of all of the people involved in this Show. The order of the
	 * returned list will correlate with the values returned fromGetRolesInShow .
	 * 
	 * Parameters: Show- the Show object Returns: a list of all of the people
	 * involved in this Show as a String array Since: 5.1
	 */
	public static java.lang.String[] GetPeopleListInShow(Object Show) {
		return (java.lang.String[]) sagex.SageAPI.call("GetPeopleListInShow", new Object[] { Show });
	}

	/**
	 * Gets a list of the roles for each of the people in the specified Show.
	 * The order of the returned list will correlate with the values returned
	 * fromGetPeopleInShow
	 * 
	 * 
	 * Parameters: Show- the Show object Returns: a list of the roles for each
	 * of the people in the specified Show
	 */
	public static java.lang.String[] GetRolesInShow(Object Show) {
		return (java.lang.String[]) sagex.SageAPI.call("GetRolesInShow", new Object[] { Show });
	}

	/**
	 * Gets the people in the specified Show in the specified Role. Returned as
	 * a comma separated list.
	 * 
	 * Parameters: Show- the Show object Role- the role to get the people for
	 * Returns: the people in the specified Show in the specified Role
	 */
	public static java.lang.String GetPeopleInShowInRole(Object Show, java.lang.String Role) {
		return (java.lang.String) sagex.SageAPI.call("GetPeopleInShowInRole", new Object[] { Show, Role });
	}

	/**
	 * Gets the people in the specified Show in the specified Roles. Returned as
	 * a comma separated list.
	 * 
	 * Parameters: Show- the Show object RoleList- the roles to get the people
	 * for Returns: the people in the specified Show in the specified Roles
	 */
	public static java.lang.String GetPeopleInShowInRoles(Object Show, java.lang.String[] RoleList) {
		return (java.lang.String) sagex.SageAPI.call("GetPeopleInShowInRoles", new Object[] { Show, RoleList });
	}

	/**
	 * Gets the people in the specified Show in the specified Role. Returned as
	 * a String array.
	 * 
	 * Parameters: Show- the Show object Role- the role to get the people for
	 * Returns: the people in the specified Show in the specified Role as a
	 * String array Since: 5.1
	 */
	public static java.lang.String[] GetPeopleListInShowInRole(Object Show, java.lang.String Role) {
		return (java.lang.String[]) sagex.SageAPI.call("GetPeopleListInShowInRole", new Object[] { Show, Role });
	}

	/**
	 * Gets the people in the specified Show in the specified Roles. Returned as
	 * a String array.
	 * 
	 * Parameters: Show- the Show object RoleList- the roles to get the people
	 * for Returns: the people in the specified Show in the specified Roles as a
	 * String array Since: 5.1
	 */
	public static java.lang.String[] GetPeopleListInShowInRoles(Object Show, java.lang.String[] RoleList) {
		return (java.lang.String[]) sagex.SageAPI.call("GetPeopleListInShowInRoles", new Object[] { Show, RoleList });
	}

	/**
	 * Returns true if the passed in argument is a Show object. No automatic
	 * type conversion will be done on the argument.
	 * 
	 * Parameters: Show- the object to test to see if its a Show Returns: true
	 * if the passed in argument is a Show object, false otherwise
	 */
	public static boolean IsShowObject(java.lang.Object Show) {
		return (Boolean) sagex.SageAPI.call("IsShowObject", new Object[] { Show });
	}

	/**
	 * Returns true if the specified Airing represents the first run of the Show
	 * content.
	 * 
	 * Parameters: Airing- the Airing object Returns: true if the specified
	 * Airing represents the first run of its Show content, false otherwise
	 */
	public static boolean IsShowFirstRun(Object Airing) {
		return (Boolean) sagex.SageAPI.call("IsShowFirstRun", new Object[] { Airing });
	}

	/**
	 * Returns true if the specified Airing represents a rerun of the Show
	 * content.
	 * 
	 * Parameters: Airing- the Airing object Returns: true if the specified
	 * Airing represents a rerun of its Show content, false otherwise
	 */
	public static boolean IsShowReRun(Object Airing) {
		return (Boolean) sagex.SageAPI.call("IsShowReRun", new Object[] { Airing });
	}

	/**
	 * Returns the language that the specified Show is in.
	 * 
	 * Parameters: Show- the Show object Returns: the language that the
	 * specified Show is in
	 */
	public static java.lang.String GetShowLanguage(Object Show) {
		return (java.lang.String) sagex.SageAPI.call("GetShowLanguage", new Object[] { Show });
	}

	/**
	 * Adds a new Show to the database. Null or the empty string ("") can be
	 * passed in for any unneeded fields.
	 * 
	 * Parameters: Title- the title of the Show (for music this should be album
	 * name) IsFirstRun- true if this Show is a first run, false otherwise (this
	 * parameter has no effect anymore since Airings determine first/rerun
	 * status) Episode- the episode name for this Show (for music this should be
	 * the song title) Description- the description of the Show Duration- the
	 * duration of the Show, not necessary and can be zero; this is only used
	 * for indicating differences between Airing duration and the actual content
	 * duration Category- the category of the Show (should be genre for music)
	 * SubCategory- the subcategory of the Show PeopleList- a list of all of the
	 * people in the Show, the roles of the people should correspond to the
	 * RolesListForPeopleList argument RolesListForPeopleList- a list of the
	 * roles for the people in the Show, this should correspond to the
	 * PeopleList argument Rated- the rating for the Show seeGetShowRated()
	 * 
	 * ExpandedRatingsList- the expanded ratings list for the show,
	 * seeGetShowExpandedRatings()
	 * 
	 * Year- the year of the Show ParentalRating- the parental rating for the
	 * Show (this is no longer used since Airing contains the parental rating)
	 * MiscList- miscellaneous metadata for the Show ExternalID- the global ID
	 * which should be used to uniquely identify this Show Language- the
	 * language for the Show OriginalAirDate- the original airing date of the
	 * Show Returns: the newly created Show object
	 */
	public static Object AddShow(java.lang.String Title, boolean IsFirstRun, java.lang.String Episode, java.lang.String Description, long Duration, java.lang.String Category, java.lang.String SubCategory, java.lang.String[] PeopleList, java.lang.String[] RolesListForPeopleList,
			java.lang.String Rated, java.lang.String[] ExpandedRatingsList, java.lang.String Year, java.lang.String ParentalRating, java.lang.String[] MiscList, java.lang.String ExternalID, java.lang.String Language, long OriginalAirDate) {
		return (Object) sagex.SageAPI.call("AddShow", new Object[] { Title, IsFirstRun, Episode, Description, Duration, Category, SubCategory, PeopleList, RolesListForPeopleList, Rated, ExpandedRatingsList, Year, ParentalRating, MiscList, ExternalID, Language, OriginalAirDate });
	}

	/**
	 * Returns a list of all of the Airings for the specified Show starting
	 * after the specified time.
	 * 
	 * Parameters: Show- the Show object StartingAfterTime- the time that all
	 * returned Airings should start after Returns: a list of all of the Airings
	 * for the specified Show starting after the specified time
	 */
	public static Object[] GetAiringsForShow(Object Show, long StartingAfterTime) {
		return (Object[]) sagex.SageAPI.call("GetAiringsForShow", new Object[] { Show, StartingAfterTime });
	}

	/**
	 * Gets a Show based on the global unique ID which is used to identify
	 * Shows. This ID is common among all SageTV users. This value can be
	 * obtained fromGetShowExternalID()
	 * 
	 * 
	 * Parameters: ExternalID- the external ID to find the corresponding Show
	 * for Returns: the Show which corresponds to the specified externalID, or
	 * null if it isn't found in the database
	 */
	public static Object GetShowForExternalID(java.lang.String ExternalID) {
		return (Object) sagex.SageAPI.call("GetShowForExternalID", new Object[] { ExternalID });
	}

	/**
	 * Gets the SeriesInfo object for a specified Show if that Show is for a
	 * television series and there is information on that series.
	 * 
	 * Parameters: Show- the Show object Returns: the SeriesInfo for the
	 * specified Show, or null if the Show has no SeriesInfo Since: 5.1
	 */
	public static Object GetShowSeriesInfo(Object Show) {
		return (Object) sagex.SageAPI.call("GetShowSeriesInfo", new Object[] { Show });
	}

}
