package org.jdna.sage.media;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdna.media.IMediaResource;
import org.jdna.media.metadata.ICastMember;
import org.jdna.media.metadata.IMediaMetadata;
import org.jdna.media.metadata.IMediaMetadataPersistence;
import org.jdna.media.metadata.MetadataKey;
import org.jdna.media.metadata.PersistenceOptions;
import org.jdna.media.metadata.impl.sage.SageProperty;
import org.jdna.media.metadata.impl.sage.SageTVPropertiesPersistence;

import sagex.api.AiringAPI;
import sagex.api.MediaFileAPI;
import sagex.api.ShowAPI;

/**
 * Updates the Sage Show Metadata for a TV Episode.
 * 
 * @author seans
 * 
 */
public class SageShowPeristence implements IMediaMetadataPersistence {
    private static final Logger log = Logger.getLogger(SageShowPeristence.class);

    public String getDescription() {
        return "Loads/Save metadata directly to a Sage Show Object";
    }

    public String getId() {
        return "sage";
    }

    public IMediaMetadata loadMetaData(IMediaResource mediaFile) {
        log.debug("loadMetadata() not yet supported.");
        return null;
    }

    public void storeMetaData(IMediaMetadata md, IMediaResource mediaFile, PersistenceOptions options) throws IOException {
        if (!(mediaFile instanceof SageMediaFile)) {
            log.error("Currently the Sage Show Persistence can only work on native Sage Media files.");
            return;
        }

        log.debug("Storing Sage Metdata directly to the Sage SHOW object");
        Object sageMF = ((SageMediaFile) mediaFile).getSageMediaObject();
        Object airing = MediaFileAPI.GetMediaFileAiring(sageMF);
        Object origShow = AiringAPI.GetShow(airing);

        log.debug("Setting default values");

        // need to update the displayable items
        Map<String, String> props = SageTVPropertiesPersistence.getSageTVMetadataMap(md);
        SageTVPropertiesPersistence.updatePropertiesForDisplay(props, md);

        String title = props.get(SageProperty.DISPLAY_TITLE.sageKey);

        log.debug("*** Title: " + title);
        boolean firstRun = ShowAPI.IsShowFirstRun(airing);
        String episode = (String) md.get(MetadataKey.EPISODE_TITLE);
        String description = md.getDescription();
        long duration = AiringAPI.GetAiringDuration(airing);
        String cat1 = ShowAPI.GetShowCategory(origShow);
        String cat2 = ShowAPI.GetShowSubCategory(origShow);
        String actors[] = null;
        String roles[] = null;
        String mpaaRated = (String) md.get(MetadataKey.MPAA_RATING);
        String mpaaExpandedRatings[] = null;
        String year = (String) md.get(MetadataKey.YEAR);
        String parentRating = ShowAPI.GetShowParentalRating(origShow);
        String[] miscList = null;
        String externalID = null;
        String language = ShowAPI.GetShowLanguage(origShow);
        long origAirDate = ShowAPI.GetOriginalAiringDate(origShow);
        
        if (origShow!=null) {
            // update some items from the original show, if it exists
            firstRun = ShowAPI.IsShowFirstRun(airing);
            cat1 = ShowAPI.GetShowCategory(origShow);
            cat2 = ShowAPI.GetShowSubCategory(origShow);
            parentRating = ShowAPI.GetShowParentalRating(origShow);
            language = ShowAPI.GetShowLanguage(origShow);
            origAirDate = ShowAPI.GetOriginalAiringDate(origShow);
            //externalID = ShowAPI.GetShowExternalID(origShow);
        }

        if (origAirDate==0) {
            log.debug("No Original Airing Date, using today");
            origAirDate = System.currentTimeMillis();
        }
        
        if (duration==0) {
            log.debug("No Duration, using duration from media file");
            duration = MediaFileAPI.GetDurationForSegment(sageMF, 0);
        }
        
        String genre[] = md.getGenres();
        if (genre != null && genre.length > 0) {
            cat1 = genre[0];
        }
        if (genre != null && genre.length > 1) {
            cat2 = genre[1];
        }

        if (!StringUtils.isEmpty((String) md.get(MetadataKey.MPAA_RATING_DESCRIPTION))) {
            // NOTE: Even though you set them as an array, when you get them,
            // they show up as Nudity, Violence, and Language, we'll need a
            // parser to
            // get back the original values
            mpaaExpandedRatings = new String[] { (String) md.get(MetadataKey.MPAA_RATING_DESCRIPTION) };
        }

        if (md.getCastMembers(ICastMember.ALL) != null) {
            List<String> l = new LinkedList<String>();
            List<String> rl = new LinkedList<String>();
            for (ICastMember cm : md.getCastMembers(ICastMember.ACTOR)) {
                log.debug("Adding CastMember: " + cm.getName());
                l.add(cm.getName());
                rl.add("Actor");
            }
            for (ICastMember cm : md.getCastMembers(ICastMember.DIRECTOR)) {
                log.debug("Adding Director: " + cm.getName());
                l.add(cm.getName());
                rl.add("Director");
            }
            for (ICastMember cm : md.getCastMembers(ICastMember.WRITER)) {
                log.debug("Adding Writer: " + cm.getName());
                l.add(cm.getName());
                rl.add("Writer");
            }

            if (l.size() > 0) {
                log.debug("Adding " + l.size() + " actors");
                actors = l.toArray(new String[l.size()]);
                roles = rl.toArray(new String[rl.size()]);
            } else {
                log.warn("No Actors");
            }
        }

        if (origShow!=null) {
            String currentId = ShowAPI.GetShowExternalID(origShow);
        }
        
        // TODO: If import show as TV, then create a new airing id
        if (externalID==null) {
            externalID = createShowId(md);
        }

        log.debug("Creating new Show...");
        log.debug("Title: " + title);
        log.debug("FirstRun: " + firstRun);
        log.debug("Episode: " + episode);
        log.debug("Description: " + description);
        log.debug("Duration: " + duration);
        log.debug("Cat1: " + cat1);
        log.debug("Cat2: " + cat2);
        log.debug("mpaaRated: " + mpaaRated);
        log.debug("mappRatedDesc: " + mpaaExpandedRatings);
        log.debug("Year: " + year);
        log.debug("ParentalRating: " + parentRating);
        log.debug("External Id: " + externalID);
        log.debug("Language: " + language);
        log.debug("Original Air Date: " + origAirDate);
        Object show = ShowAPI.AddShow(title, firstRun, episode, description, duration, cat1, cat2, actors, roles, mpaaRated, mpaaExpandedRatings, year, parentRating, miscList, externalID, language, origAirDate);

        if (show == null) {
            log.error("Failed to create a new Show using the provided metadata!");
            return;
        }

        log.debug("Adding new show to mediafile");
        MediaFileAPI.SetMediaFileShow(sageMF, show);
    }

    private String createShowId(IMediaMetadata md) {
        // TODO: if import tv is enabled, then set the prefix to "EP", so that 
        // it will show up in the sage recordings
        String prefix = "MFss";
        String id = null;
        do {
            // keep gen'd EPGID less than 12 chars
            // SEANS: Taken from nielm's xmlinfo... I'm guessing there is a 12 char limit, so i won't mess with it
            id = prefix + Integer.toHexString((int)(java.lang.Math.random()*0xFFFFFFF));
            log.debug("Calculated a showid: " + id);
        } while (ShowAPI.GetShowForExternalID(id) != null);
        return id;
    }
}
