package org.jdna.media.util;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jdna.media.metadata.MediaMetadataUtils;
import org.jdna.media.metadata.SearchQuery;
import org.jdna.media.metadata.impl.xbmc.XbmcScraper;
import org.jdna.media.metadata.impl.xbmc.XbmcScraperParser;
import org.jdna.media.metadata.impl.xbmc.XbmcScraperProcessor;

import sagex.api.AiringAPI;

public class FileNameUtils {
    private static final Logger log      = Logger.getLogger(FileNameUtils.class);

    private List<XbmcScraper>   scrapers = new ArrayList<XbmcScraper>();

    public FileNameUtils(File scraperDir) {
        File files[] = scraperDir.listFiles();
        XbmcScraperParser p = new XbmcScraperParser();
        for (File f : files) {
            try {
                XbmcScraper scrap = p.parseScraper(f);
                scrapers.add(scrap);
                log.debug("Loaded Filename Scraper: " + scrap.getName());
            } catch (Exception e) {
                log.error("Failed to process scraper file: " + f.getAbsolutePath());
            }
        }
    }

    public SearchQuery createSearchQuery(String filenameUri) {
        // important, or else the filename parser will find %20 in the file
        // names, not good.
        filenameUri = URLDecoder.decode(filenameUri);
        log.debug("Using Scrapers to find a query for: " + filenameUri);
        SearchQuery q = new SearchQuery();
        String args[] = new String[] { "", filenameUri };
        for (XbmcScraper x : scrapers) {
            XbmcScraperProcessor proc = new XbmcScraperProcessor(x);
            String title = proc.executeFunction("GetShowName", args);
            if (StringUtils.isEmpty(title)) {
                continue;
            }
            
            log.debug("Found a showname: " + title + " for " + filenameUri);

            // check if title matches an aired date query
            String airedDate = proc.executeFunction("GetAiredDate", args);
            if (!StringUtils.isEmpty(airedDate)) {
                log.debug("We have a hit for a tv show for: " + filenameUri + "; by aired date: " + airedDate);
                q.setType(SearchQuery.Type.TV);
                q.set(SearchQuery.Field.TITLE, title);
                q.set(SearchQuery.Field.EPISODE_DATE, airedDate);
                break;
            }

            // continue testing if title has season and episode
            String season = proc.executeFunction("GetSeason", args);
            if (!StringUtils.isEmpty(season)) {
                String ep = proc.executeFunction("GetEpisode", args);
                String dp = "";

                if (StringUtils.isEmpty(ep)) {
                    dp = proc.executeFunction("GetDisc", args);
                    if (StringUtils.isEmpty(dp)) continue;
                }

                log.debug("We have a hit for a tv show for: " + filenameUri);
                q.setType(SearchQuery.Type.TV);
                q.set(SearchQuery.Field.TITLE, MediaMetadataUtils.cleanSearchCriteria(title, false));
                q.set(SearchQuery.Field.SEASON, season);
                q.set(SearchQuery.Field.EPISODE, ep);
                q.set(SearchQuery.Field.DISC, dp);
                break;
            }
            
            // try to find a sage title/airing
            // TODO: If sage is not enabled, then just do the compressed airing title
            String sageAiringId = proc.executeFunction("GetAiringId", args);
            if (!StringUtils.isEmpty(sageAiringId)) {
                q.setType(SearchQuery.Type.TV);
                q.set(SearchQuery.Field.TITLE, title);
                Object airing = AiringAPI.GetAiringForID(NumberUtils.toInt(sageAiringId));
                if (airing!=null) {
                    q.set(SearchQuery.Field.EPISODE_TITLE, AiringAPI.GetAiringTitle(airing));
                    break;
                }
                
                // let's just use the compressed title
                q.set(SearchQuery.Field.EPISODE_TITLE, proc.executeFunction("GetEpisodeTitle", args));
                break;
            }
        }
        return q;
    }
}
