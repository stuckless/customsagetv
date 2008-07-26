package org.jdna.media.metadata;

import java.io.IOException;

import org.jdna.media.IMediaFile;

public interface IVideoMetaDataPersistence {
	/**
	 * should return the short id of this persistence engine
	 * @return
	 */
	public String getId();
	
	/**
	 * Description about what the persistence engine provides.
	 * @return
	 */
	public String getDescription();
	
	/**
	 * Must store all metadata for the given IVideoMetaData object.  This includes thumbnails, etc,
	 * 
	 * @param md video metadata to store
	 * @param mediaFile file to which this metadata attached
	 * @throws IOException if it connot be saved
	 */
	public void storeMetaData(IVideoMetaData md, IMediaFile mediaFile) throws IOException;
	
	/**
	 * loads the saved IVideoMetaData associated with the given media file.  This method must return null if there is no metadata.
	 * 
	 * @param mediaFile mediaFile with possible metadata 
	 * @return IVideoMetaData if it exists, or null, if no matedata exists.
	 */
	public IVideoMetaData loadMetaData(IMediaFile mediaFile);
}