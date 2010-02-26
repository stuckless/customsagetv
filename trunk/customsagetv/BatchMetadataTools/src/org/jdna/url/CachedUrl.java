package org.jdna.url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jdna.util.PropertiesUtils;

public class CachedUrl extends Url implements IUrl {

    private static final Logger log             = Logger.getLogger(CachedUrl.class);

    private String urlId = null;
    private File                propFile        = null;
    private Properties          props           = null;
    public File                 urlCacheDir     = null;
    private boolean             followRedirects = false;
    private UrlConfiguration cfg =new UrlConfiguration();

    public CachedUrl(String url) throws IOException {
        super(url);

        urlId = getCachedFileName(url);
        propFile = new File(getCacheDir(), urlId + ".properties");
        props = new Properties();
        if (propFile.exists()) {
            log.debug("Reloading existing cached url: " + propFile.getAbsolutePath() + " with id: " + urlId);
            PropertiesUtils.load(props, propFile);
            File f = getCachedFile();
            if (f.exists() && (isExpired(f) || f.length()==0)) {
                log.info("Removing Cached Url File: " + f);
                f.delete();
            }
        } else {
            File f = propFile.getParentFile();
            f.mkdirs();
            log.debug("Creating a new cached url for: " + url);
            props.setProperty("url", url);
            props.setProperty("file", new File(getCacheDir(), urlId + ".cache").getPath());
        }

        // sanity check
        if (!url.toLowerCase().equals(props.getProperty("url").toLowerCase())) {
            log.error("The Cached url does not match the one passed! " + props.getProperty("url") + " != " + url + "; Propfile Name: " + propFile);
            props.setProperty("url", url);
            File f = getCachedFile();
            if (f.exists()) {
                log.error("Removing cached content for url: " + url);
                f.delete();
            }
        }
    }

    private String getCachedFileName(String url) {
        try {
            if (url==null) return null;
            // now uses a simple md5 hash, which should have a fairly low collision rate, especially for our
            // limited use
            byte[] key = DigestUtils.md5(url);
            return new String(Hex.encodeHex(key));
        } catch (Exception e) {
            log.error("Failed to create cached filename for url: " + url, e);
            throw new RuntimeException(e);
        }
    }

    public boolean isExpired(File cachedFile) {
        long secs = cfg.getCacheExpiryInSeconds();
        long diff = (System.currentTimeMillis() - cachedFile.lastModified())/1000;
        boolean expired = (diff > secs);
        if (expired) {
            log.debug("CachedUrl.isExpired(): " + expired + "; File: " + cachedFile + "; LastModified: " + cachedFile.lastModified() + "; Current Time: " + System.currentTimeMillis() + "; Expiry: " + secs + "s; Diff: " + diff + "s");
        }
        return expired;
    }
    
    public static boolean isExpired(File cachedFile, long expirySecs) {
        long diff = (System.currentTimeMillis() - cachedFile.lastModified())/1000;
        boolean expired = (diff > expirySecs);
        if (expired) {
            log.debug("CachedUrl.isExpired(): " + expired + "; File: " + cachedFile + "; LastModified: " + cachedFile.lastModified() + "; Current Time: " + System.currentTimeMillis() + "; Expiry: " + expirySecs + "s; Diff: " + diff + "s");
        }
        return expired;
    }

    private File getCacheDir() {
        if (urlCacheDir == null) {
            urlCacheDir = new File(cfg.getCacheDir());
            if (!urlCacheDir.exists()) urlCacheDir.mkdirs();
        }
        return urlCacheDir;
    }

    public URL getOriginalUrl() throws IOException {
        return new URL(props.getProperty("url"));
    }

    public File getPropertyFile() {
        return propFile;
    }

    public File getCachedFile() {
        return getCachedFile(props);
    }
    
    public static File getCachedFile(Properties props) {
        return new File(props.getProperty("file"));
    }

    @Override
    public boolean hasMoved() {
        return Boolean.parseBoolean(props.getProperty("moved", "false"));
    }

    @Override
    public URL getMovedUrl() throws IOException {
        return new URL(props.getProperty("movedUrl"));
    }

    @Override
    public URL getUrl() throws IOException {
        return getUrl(null);
    }

    public URL getUrl(ICookieHandler handler) throws IOException {
        File f = getCachedFile();
        if (!f.exists() || f.length()==0) {
            cache(handler);
        } else {
            log.debug("Cached File exists: " + f.getAbsolutePath() + " so we'll just use it.");
        }
        return f.toURI().toURL();
    }

    public void cache(ICookieHandler handler) throws IOException {
        log.debug("Caching Url: " + getOriginalUrl().toExternalForm());
        URL u = getOriginalUrl();
        URLConnection c = u.openConnection();
        sendCookies(u, c, handler);
        if (c instanceof HttpURLConnection) {
            HttpURLConnection conn = (HttpURLConnection) c;
            conn.setInstanceFollowRedirects(followRedirects);
            log.debug("User Agent: " + System.getProperty("http.agent"));
            conn.setRequestProperty("User-Agent", cfg.getHttpUserAgent());
            InputStream is = conn.getInputStream();
            int rc = conn.getResponseCode();
            if (rc == HttpURLConnection.HTTP_MOVED_PERM || rc == HttpURLConnection.HTTP_MOVED_TEMP) {
                props.setProperty("moved", "true");
                String redirectUrl = conn.getHeaderField("Location");
                if (redirectUrl != null) {
                    int p = redirectUrl.indexOf('?');
                    if (p != -1) {
                        redirectUrl = redirectUrl.substring(0, p);
                    }
                    props.setProperty("movedUrl", redirectUrl);
                }
                File f = getCachedFile();
                FileOutputStream fos = new FileOutputStream(f);
                IOUtils.copy(is, fos);
                fos.flush();
                fos.close();
                log.debug("Url " + u.toExternalForm() + " Cached To: " + f.getAbsolutePath());
                log.debug(String.format("Url: %s moved to %s", u.toExternalForm(), redirectUrl));
            } else if (rc == HttpURLConnection.HTTP_OK) {
                handleCookies(u, c, handler);
                File f = getCachedFile();
                FileOutputStream fos = new FileOutputStream(f);
                IOUtils.copy(is, fos);
                fos.flush();
                fos.close();
                log.debug("Url " + u.toExternalForm() + " Cached To: " + f.getAbsolutePath());
            } else {
                throw new IOException("Http Response Code: " + rc + "; Message: " + conn.getResponseMessage());
            }
        } else {
            // do nothing... we can't cache local urls
            log.warn("Cannot Cache Url Connection Type; " + c.getClass().getName());

        }
        PropertiesUtils.store(props, getPropertyFile(), "Cached Url Properties");
        log.debug("Properties for cached url are now stored: " + getPropertyFile().getAbsolutePath());
    }

    @Override
    public InputStream getInputStream(ICookieHandler handler, boolean followRedirects) throws IOException {
        this.followRedirects = followRedirects;

        URL u = getUrl(handler);

        return u.openStream();
    }

    /**
     * Will remove a url from the cache, in the event that url caching is
     * enabled.
     * 
     * @param dataUrl
     */
    public static void remove(String dataUrl) {
        try {
            CachedUrl cu = new CachedUrl(dataUrl);
            cu.remove();
        } catch (IOException e) {
            log.error("Unabled to remove cached data url: " + dataUrl);
        }
    }

    private void remove() {
        try {
            log.debug("Removing Cached Url: " + this.getOriginalUrl().toExternalForm());
            if (props != null) {
                // remove the data
                File f = getCachedFile();
                if (f.exists()) {
                    log.debug("Removing Cached File: " + f.getAbsolutePath());
                    f.delete();
                }
                
                // now remove the propfile
                propFile.delete();
            }
        } catch (IOException e) {
        }
    }
    
    @Override
    public String toString() {
        return "CachedUrl: " + (props!=null ? props.getProperty("url") : "N/A") + "; UrlId: " + urlId;
    }
}
