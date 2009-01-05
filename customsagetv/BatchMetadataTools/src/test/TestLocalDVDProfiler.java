package test;

import java.io.File;
import java.util.List;

import org.jdna.media.metadata.IMediaMetadataProvider;
import org.jdna.media.metadata.IMediaSearchResult;
import org.jdna.media.metadata.impl.dvdproflocal.DVDProfXmlFile;
import org.jdna.media.metadata.impl.dvdproflocal.IDVDProfMovieNodeVisitor;
import org.jdna.media.metadata.impl.dvdproflocal.LocalDVDProfMetaDataProvider;
import org.jdna.metadataupdater.MetadataUpdater;
import org.w3c.dom.Element;

public class TestLocalDVDProfiler {
    public static void main(String args[]) throws Exception {
        MetadataUpdater.initConfiguration();
        DVDProfXmlFile file = new DVDProfXmlFile(new File("/home/seans/DevelopmentProjects/workspaces/sage/MovieMetadataUpdater/testing/DVDProfiler/dvd.xml"));
        IDVDProfMovieNodeVisitor visitor = new IDVDProfMovieNodeVisitor() {
            public void visitMovie(Element el) {
                System.out.println("ID: " + DVDProfXmlFile.getElementValue(el, "ID"));
                System.out.println("Title: " + DVDProfXmlFile.getElementValue(el, "Title"));
                System.out.println("Year: " + DVDProfXmlFile.getElementValue(el, "ProductionYear"));
            }
        };
        file.visitMovies(visitor);
    }

    public static void main2(String args[]) throws Exception {
        MetadataUpdater.initConfiguration();

        IMediaMetadataProvider prov = new LocalDVDProfMetaDataProvider();
        List<IMediaSearchResult> results = prov.search(IMediaMetadataProvider.SEARCH_TITLE, "Batman Begins");

        TestUtils.dumpResults(results);

        if (results != null && results.size() > 0) {
            IMediaSearchResult res = results.get(0);
            TestUtils.dumpMetaData(prov.getMetaData(res));
        }
    }
}