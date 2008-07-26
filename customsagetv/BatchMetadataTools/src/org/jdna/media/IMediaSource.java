package org.jdna.media;

public interface IMediaSource {
	public String getName();
	public String getPath();
	public IMediaFolder getMediaFolder();
}