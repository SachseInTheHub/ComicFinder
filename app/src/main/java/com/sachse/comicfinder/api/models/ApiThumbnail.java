package com.sachse.comicfinder.api.models;

public class ApiThumbnail {

	public String path;
	public String extension;

	public ApiThumbnail(ApiThumbnail thumbnail) {
		path = thumbnail.path;
		extension = thumbnail.extension;
	}

	public String getResourcePath(){
		return path + "." + extension;
	}
}

