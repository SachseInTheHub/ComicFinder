package com.sachse.comicfinder.api.models;

import java.util.List;

public class ApiResults {

	public int id;
	public String name;
	public String description;
	public String resourceURI;
	public List<ApiUrl> urls;
	public ApiThumbnail thumbnail;

	public ApiResults(ApiResults apiResults) {
		id = apiResults.id;
		name = apiResults.name;
		description = apiResults.description;
		resourceURI = apiResults.resourceURI;
		urls = apiResults.urls;
		thumbnail = new ApiThumbnail(apiResults.thumbnail);
	}
}
