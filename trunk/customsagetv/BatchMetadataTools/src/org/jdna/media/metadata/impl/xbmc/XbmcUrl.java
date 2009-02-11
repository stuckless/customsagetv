package org.jdna.media.metadata.impl.xbmc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdna.url.CachedUrl;
import org.jdna.url.IUrl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This represents the "smart" url of the xbmc metadata. It has to understand
 * the concepts of post, spoof, etc.
 * 
 * @author seans
 * 
 */
public class XbmcUrl {
    private static final Logger log = Logger.getLogger(XbmcUrl.class);
    
    private String urlString;
    //private URL    url;
    private IUrl url;
    private String functionName;
    private String spoof;
    private String post;
    private XbmcScraper scraper;

    /**
     * Creates an XbmcUrl from an Element.
     * 
     * @param url
     */
    public XbmcUrl(Element url) {
        updateFromElement(url);
    }
    
    /**
     * Creates xbmc url from string.  String can be just a simple url, or an xml fragment <url>text</url>
     * 
     * @param url
     */
    public XbmcUrl(String url) {
        log.debug("Creating XbmcUrl from String url: " + url);
        if (url == null) return;
        if (url.trim().contains("<url")) {
            // parse <url>url</url> syntax
            try {
                DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                DocumentBuilder parser = f.newDocumentBuilder();
                Document d = parser.parse(new ByteArrayInputStream(url.getBytes()));
                Element e = (Element) d.getElementsByTagName("url").item(0);
                updateFromElement(e);
            } catch (Exception e) {
                throw new RuntimeException("Invalid xml url: " + url, e);
            }
        } else {
            this.urlString = url;
        }
    }

    /**
     * Creates an XbmcUrl from a given xml url fragment, and stores a reference from the scraper that
     * created the url fragment.  This is typically used when processing <url> fragments during scraping.
     * 
     * @param url
     * @param xbmcScraper
     */
    public XbmcUrl(String url, XbmcScraper xbmcScraper) {
        this(url);
        this.scraper = xbmcScraper;
    }

    public XbmcUrl(Element url, XbmcScraper xbmcScraper) {
        this(url);
        this.scraper = xbmcScraper;
    }

    private void updateFromElement(Element e) {
        urlString = e.getTextContent();
        if (urlString!=null) urlString = urlString.trim();
        log.debug("XbmcUrl using Url from Xml: " + urlString);
        functionName = e.getAttribute("function");
        // TODO: pull in post, spoof, etc.
    }

    private IUrl getUrl() throws Exception {
        if (url == null) {
            url = new CachedUrl(urlString);
            // TODO: Add in the referer, etc
        }

        return url;
    }

    public InputStream getInputStream() throws Exception {
        // check if this is a function lookup, and if so, then let's process this as such.
        if (scraper!=null && !StringUtils.isEmpty(getFunctionName())) {
            XbmcScraperProcessor processor = new XbmcScraperProcessor(scraper);
            log.debug("Processing Url Function: " + getFunctionName() + " with Url: " + urlString);
            // we have a function to process
            // TODO: Set spoof and post attributes...
            XbmcUrl xurl = new XbmcUrl(urlString);
            String results = processor.executeFunction(getFunctionName(), new String[] {"", xurl.getTextContent()});
            if (results==null) results="";
            return new ByteArrayInputStream(results.getBytes());
        } else {
            // TODO: Check for posting, caching, etc
            IUrl u = getUrl();
            
            if (urlString.contains(".zip")) {
                log.debug("Converting ZipFile to Text content for url: " + urlString);
                // turn the zip contents into a text file
                ZipInputStream zis = new ZipInputStream(u.getInputStream(null, true));
                ZipEntry ze = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int MAX_LEN = 2048;
                byte buf[] = new byte[MAX_LEN];
                while ((ze = zis.getNextEntry()) != null) {
                    log.debug("Adding Zip Entry: " + ze.getName() + " to Text content");
                    int len = 0;
                    while ((len=zis.read(buf))>0) {
                        baos.write(buf,0,len);
                    }
                }
                baos.flush();
                zis.close();
                log.debug("Returing Text Context as inputstream...");
                return new ByteArrayInputStream(baos.toByteArray());
            } else {
                return u.getInputStream(null, true);
            }
        }
    }

    public String toExternalForm() {
        return urlString;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getTextContent() throws Exception {
        return IOUtils.toString(getInputStream());
    }
    
    
}
