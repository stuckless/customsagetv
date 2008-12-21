package org.jdna.media.metadata.impl.imdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jdna.media.metadata.IMediaSearchResult;
import org.jdna.media.metadata.MediaSearchResult;
import org.jdna.url.URLSaxParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * State Machine parser that will parse an IDMB Search Reults Url and build a list of IVideoSearchResult objects.
 * 
 * Each parsed url will require it's own instance of IMDBSearchResultParser, since each parser will hold state information.
 * A parser can be reused, as long as 2 separate processes are not using the same instance at the same time.
 * 
 * @author seans
 *
 */
public class IMDBSearchResultParser extends URLSaxParser {
	private static final Logger log = Logger.getLogger(IMDBSearchResultParser.class);

	public static final String TITLE_URL = "http://www.imdb.com/title/%s/"; 
	
	private static String POPULAR_TITLE_MATCH="Popular Titles";
	private static String PARTIAL_TITLE_MATCH="Titles (Approx Matches)";
	private static String EXACT_TITLE_MATCH="Titles (Exact Matches)";
	private static String END_OF_LIST_MATCH="Suggestions For Improving Your Results";
	
	private static final int POPULAR_MATCHES=IMediaSearchResult.RESULT_TYPE_POPULAR_MATCH;
	private static final int EXACT_MATCHES=IMediaSearchResult.RESULT_TYPE_EXACT_MATCH;
	private static final int PARTIAL_MATCHES=IMediaSearchResult.RESULT_TYPE_PARTIAL_MATCH;
	private static final int STARTING=88;
	private static final int ENDED=99;
	private int state = STARTING;
	
	private static final int TITLE_READ_TITLE=1;
	private static final int TITLE_READ_YEAR=2;
	private static final int TITLE_DONE=3;
	private int aState = TITLE_DONE;
	
	private String charBuffer = null;
	private MediaSearchResult curResult = null;
	
	private List<IMediaSearchResult> results = new ArrayList<IMediaSearchResult>();
	
	private Comparator<IMediaSearchResult> sorter = new Comparator<IMediaSearchResult>() {

		public int compare(IMediaSearchResult o1, IMediaSearchResult o2) {
			if (o1.getResultType()==IMediaSearchResult.RESULT_TYPE_EXACT_MATCH) return -1;
			if (o2.getResultType()==IMediaSearchResult.RESULT_TYPE_EXACT_MATCH) return -1;
			if (o1.getResultType()==IMediaSearchResult.RESULT_TYPE_POPULAR_MATCH) return -1;
			if (o2.getResultType()==IMediaSearchResult.RESULT_TYPE_POPULAR_MATCH) return -1;
			return 0;
		}
		
	};
	
	public IMDBSearchResultParser(String url) {
		super(url);
	}
	
	public List<IMediaSearchResult> getResults() {
		Collections.sort(results, sorter);
		return results;
	}

	public static String parseTitleId(String href) {
		if (href==null) return null;
		String parts[] = href.split("/");
		if (parts!=null && parts.length>0) {
			int l = parts.length;
			for (int i=0;i<l;i++) {
				if ("title".equals(parts[i])) {
					return parts[i+1];
				}
			}
		}
		return href;
	}



	@Override
	public void startElement(String uri, String localName, String name,	Attributes atts) throws SAXException {
		if (state == ENDED) return;
		
		String elName = localName.toLowerCase();
		if ("img".equals(elName)) {
			// reset the title states when we encounter images
			aState = TITLE_DONE;
		}
		
		if ("a".equals(elName) && state!=STARTING) {
			String href = atts.getValue("href");
			log.debug("Starting, found an A tag: href: " + href);
			if (href!=null && href.indexOf("/title/")!=-1) {
				aState = TITLE_READ_TITLE;
				
				// create the IVIdeoResult
				curResult = new MediaSearchResult(IMDBMetaDataProvider.PROVIDER_ID, state);
				
				// set the imdb title url
				String imdbId = parseTitleId(href);
				log.debug("Setting IMDB ID: " + imdbId + " from href: " + href);
				curResult.setIMDBId(imdbId);
				curResult.setId(String.format(TITLE_URL,imdbId));
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (state==ENDED) return;
		
		charBuffer = getCharacters(ch, start, length);
		if (charBuffer==null || charBuffer.length()==0) return;
		
		if (POPULAR_TITLE_MATCH.equals(charBuffer)) {
			log.debug("Starting Popular Match Titles");
			state = POPULAR_MATCHES;
		} else if (EXACT_TITLE_MATCH.equals(charBuffer)) {
			log.debug("Starting Exact Match Titles");
			state = EXACT_MATCHES;
		} else if (PARTIAL_TITLE_MATCH.equals(charBuffer)) {
			log.debug("Starting Partial Match Titles");
			state = PARTIAL_MATCHES;
		} else if (END_OF_LIST_MATCH.equals(charBuffer)) {
			state = ENDED;
			return;
		}

		if (state==STARTING || state==ENDED) return;

		if (aState==TITLE_READ_TITLE) {
			curResult.setTitle(charBuffer);
			
			// set the state to READ_YEAR
			aState = TITLE_READ_YEAR;
		} else if(aState == TITLE_READ_YEAR) {
			// year should look like this... (NNNN)
			Pattern p = Pattern.compile("\\(([0-9]+[\\/]*[A-Z]*)\\)");
			try {
				Matcher m = p.matcher(charBuffer);
				if (m.find()) {
					curResult.setYear(m.group(1));
				} else {
					log.error("Could not parse Year from: " + charBuffer);
				}
			} catch (Exception e) {
				log.error("Error Parsing Year from CharBuffer:["+charBuffer+"]", e);
			}
			// reset the title reading state... we're done this entry.
			aState = TITLE_DONE;
			
			// add the result.
			log.debug("Adding Result: " + curResult.getTitle());
			results.add(curResult);
			curResult=null;
		} 
	}
}