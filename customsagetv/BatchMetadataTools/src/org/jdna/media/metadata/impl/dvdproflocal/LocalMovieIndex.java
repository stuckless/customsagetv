package org.jdna.media.metadata.impl.dvdproflocal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.jdna.media.metadata.IMediaSearchResult;
import org.jdna.media.metadata.MediaSearchResult;
import org.w3c.dom.Element;

public class LocalMovieIndex implements IDVDProfMovieNodeVisitor {
	private static final Logger log = Logger.getLogger(LocalMovieIndex.class);
	private static LocalMovieIndex indexer = new LocalMovieIndex();
	
	private IndexReader reader = null;
	private IndexWriter writer = null;
	private File indexDir = null;
	
    private Searcher searcher = null;
    private Analyzer analyzer = new StandardAnalyzer();
    private QueryParser parser = new QueryParser("title", analyzer);
	
	public static LocalMovieIndex getInstance() {
		return indexer;
	}
	
	public boolean isNew() {
		File ch[] = getIndexDir().listFiles();
		return ch==null || ch.length==0;
	}
	
	public void beginIndexing() throws Exception {
		writer = new IndexWriter(getIndexDir(), new StandardAnalyzer(), true);
	}
	
	public void endIndexing() throws Exception {
		writer.optimize();
		writer.close();
		
		// open the index, for searching
		openIndex();
	}
	
	public void openIndex() throws Exception {
		indexDir  = getIndexDir();
		if (!indexDir.exists()) {
			log.debug("Creating Lucene Index Dir: " + indexDir.getAbsolutePath());
		}
		
		log.debug("Opening Lucene Index: " + indexDir.getAbsolutePath());
		
		reader = IndexReader.open(indexDir);
		searcher = new IndexSearcher(reader);
	}

	public void addMovie(String name, String date, String id) throws Exception {
		log.debug("Indexing Movie: " + name + "; date: " + date + "; id: " + id);
		Document doc = createDocument(name, date, id);
		writer.addDocument(doc);
	}
	
	private File getIndexDir() {
		return indexDir;
	}

	public static Document createDocument(String name, String date, String id) {
	    // make a new, empty document
	    Document doc = new Document();

	    // index titles
	    doc.add(new Field("title", name, Field.Store.YES, Field.Index.TOKENIZED));

	    // Store release date but not index
	    doc.add(new Field("release", date, Field.Store.YES, Field.Index.NO));
	    doc.add(new Field("id", id, Field.Store.YES, Field.Index.NO));

	    // return the document
	    return doc;
	}
	
	
	public List<IMediaSearchResult> searchTitle(String title) throws Exception {
		if (searcher==null) openIndex();
		
		Query query = parser.parse(title);
		Hits hits = searcher.search(query);
		
		int l = hits.length();
		List<IMediaSearchResult> results = new ArrayList<IMediaSearchResult>(l);
		
		for (int i=0;i<l;i++) {
			Document d = hits.doc(i);
			int type=IMediaSearchResult.RESULT_TYPE_UNKNOWN;
			if (hits.score(i)>0.99) {
				type = IMediaSearchResult.RESULT_TYPE_EXACT_MATCH;
			} else if(hits.score(i)>0.9) {
				type = IMediaSearchResult.RESULT_TYPE_POPULAR_MATCH;
			}
			
			String name = d.get("title");
			String date = d.get("release");
			String id = d.get("id");
			
			results.add(new MediaSearchResult(LocalDVDProfMetaDataProvider.PROVIDER_ID, id, name, date, type));
		}
		
		return results;
	}

	public void clean() {
		if (isNew()) return;
		
		log.debug("Deleting All Currently indexed documents.");
		try {
			openIndex();
			int s = reader.numDocs();
			for (int i=0;i<s;i++) {
				reader.deleteDocument(i);
			}
		} catch (Exception e) {
			log.error("Failed to delete index documents: Consider manually removing the directory: " + indexDir.getAbsolutePath());
		}
		log.debug("Finished Deleting documents.");
	}

	public void setIndexDir(String indexDir2) {
		this.indexDir=new File(indexDir2);
		if (!indexDir.exists()) {
			log.debug("Creating Lucene Index Dir: " + indexDir.getAbsolutePath());
		}
	}

	public void visitMovie(Element el) {
		String id = DVDProfXmlFile.getElementValue(el, "ID");
		String title = DVDProfXmlFile.getElementValue(el, "Title");
		String year = DVDProfXmlFile.getElementValue(el, "ProductionYear");
		try {
			addMovie(title, year, id);
		} catch (Exception e) {
			log.error("Can't index movie node: " + el.getTextContent(), e);
		}
	}
}