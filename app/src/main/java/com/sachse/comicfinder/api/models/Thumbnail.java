package com.sachse.comicfinder.api.models;

public class Thumbnail {

	public String path;
	public String extension;

	public Thumbnail(Thumbnail thumbnail) {
		path = thumbnail.path;
		extension = thumbnail.extension;
	}

	public String getResourcePath(){
		return path + "." + extension;
	}
}

