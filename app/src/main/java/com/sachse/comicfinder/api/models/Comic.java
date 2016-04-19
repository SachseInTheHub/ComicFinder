package com.sachse.comicfinder.api.models;

import java.util.List;

public class Comic {
	public int id;
	public int digitalId;
	public String title;
//	public double issueNumber;
	public String variantDescription;
	public String description;
//	public String modifiedSince;
	public String isbn;
	public String upc;
	public String diamondCode;
	public String ean;
	public String issn;
	public String format;
	public int pageCount;
	public List<TextObjects> textObjects;
	public String resourceURI;
	public List<Url> urls;

//	public List<String> variants;
//	public List<String> collections;
//	public List<String> collectedIssues;
//	public List<String> dates;
//	public List<String> prices;
	public Thumbnail thumbnail;
	public List<Image> images;
//	public String creators;
//	public String characters;
//	public String stories;
//	public String events;

	public Comic(Comic body) {
		id = body.id;
		digitalId = body.digitalId;
		title = body.title;
//		issueNumber = body.issueNumber;
		variantDescription = body.variantDescription;
		description = body.description;
//		modifiedSince = body.modifiedSince;
		isbn = body.isbn;
		resourceURI = body.resourceURI;
		urls = body.urls;
		thumbnail = body.thumbnail;
		images = body.images;
	}
}
