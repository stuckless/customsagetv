package org.jdna.media.metadata;

import org.jdna.persistence.annotations.Field;
import org.jdna.persistence.annotations.Table;

@Table(name="metadata", requiresKey=false, description="Configuration for Metadata")
public class MetadataConfiguration {
	
	@Field(description="Default class name for storing metadata")
	private String persistenceClass = org.jdna.media.metadata.impl.sage.SageVideoMetaDataPersistence.class.getName();
	
	@Field(description="Comma separated list of known metadata providers (ie, can be used for searching for metadata)")
	private String videoMetadataProviders = "org.jdna.media.metadata.impl.imdb.IMDBMetaDataProvider,org.jdna.media.metadata.impl.nielm.NielmIMDBMetaDataProvider,org.jdna.media.metadata.impl.dvdprof.DVDProfMetaDataProvider,org.jdna.media.metadata.impl.dvdproflocal.LocalDVDProfMetaDataProvider";

	@Field(description="Comma separated list of words that will be removed from a title when doing a search")
	private String wordsToClean = "dvd,dvdrip,cam,ts,tc,scr,screener,dvdscr,xvid,divx,avi,vrs,repack,mallat,proper,dmt,dmd,stv";
	
	public MetadataConfiguration() {
	}

	public String getPersistenceClass() {
		return persistenceClass;
	}

	public void setPersistenceClass(String persistenceClass) {
		this.persistenceClass = persistenceClass;
	}

	public String getMediaMetadataProviders() {
		return videoMetadataProviders;
	}

	public void setVideoMetadataProviders(String videoMetadataProviders) {
		this.videoMetadataProviders = videoMetadataProviders;
	}

	public String getWordsToClean() {
		return wordsToClean;
	}

	public void setWordsToClean(String wordsToClean) {
		this.wordsToClean = wordsToClean;
	}
	
}