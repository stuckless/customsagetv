package test;

import org.jdna.media.metadata.SearchQuery;
import org.jdna.media.metadata.impl.imdb.IMDBMetaDataProvider;

public class TestIMDBProvider extends TestIMDBParser {
    public static void main(String args[]) {
        TestIMDBProvider p = new TestIMDBProvider();
        p.go(args[0]);
    }

    public void go(String arg) {
        IMDBMetaDataProvider finder = new IMDBMetaDataProvider();
        try {
            TestUtils.dumpResults(finder.search(new SearchQuery(arg)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}