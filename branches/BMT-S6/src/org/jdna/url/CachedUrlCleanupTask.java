package org.jdna.url;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.apache.commons.io.DirectoryWalker;
import org.apache.log4j.Logger;
import org.jdna.util.FileExtFileFilter;
import org.jdna.util.PropertiesUtils;

import sagex.phoenix.util.FileUtils;

public class CachedUrlCleanupTask extends TimerTask {
    public static final String TaskID     = "urlcachecleaner";
    private Logger             log        = Logger.getLogger(CachedUrlCleanupTask.class);

    private UrlConfiguration   cfg        = new UrlConfiguration();
    private FileFilter         fileFilter = new FileExtFileFilter("properties");

    public class FileCleaner extends DirectoryWalker {
        public FileCleaner() {
            super();
        }

        public List clean(File startDirectory) throws IOException {
            List results = new ArrayList();
            walk(startDirectory, results);
            return results;
        }

        protected void handleFile(File file, int depth, Collection results) {
            if (file.isFile() && file.getName().endsWith(".properties")) {
                Properties props = new Properties();
                try {
                    PropertiesUtils.load(props, file);
                    File f = CachedUrl.getCachedFile(props);
                    if (f.exists() && (CachedUrl.isExpired(f, cfg.getCacheExpiryInSeconds()) || f.length() == 0)) {
                        log.info("Removing Cached Url File: " + f);
                        f.delete();
                        FileUtils.deleteQuietly(file);
                        results.add(file);
                    }
                } catch (IOException e) {
                    log.error("Failed to read Cached Url Properties", e);
                }
            }
        }
    }

    public CachedUrlCleanupTask() {
        log.info("Url Cleanup Task Created");
    }

    @Override
    public void run() {
        // TODO: Check to see if we are turned off the in configuration
        try {
            File dir = new File(cfg.getCacheDir());
            if (dir.exists()) {
                log.info("Begin URL Cache Cleanup");
                List results = new FileCleaner().clean(dir);
                log.info("Finished URL Cache Cleanup; Cleaned " + results.size() + " items.");
            }
        } catch (Throwable t) {
            log.warn("CachedUrlCleanupTask failed", t);
        }
    }
}
