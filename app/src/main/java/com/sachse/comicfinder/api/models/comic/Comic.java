package com.sachse.comicfinder.api.models.comic;

import com.sachse.comicfinder.api.models.TextObjects;
import com.sachse.comicfinder.api.models.Thumbnail;

import java.util.List;

public class Comic {

    public int id;
    public int digitalId;
    public String title;
    //public double issueNumber;
    public String variantDescription;
    public String description;
    //public Character modifiedSince;
    public String isbn;
    public String upc;
    public String diamondCode;
    public String ean;
    public String issn;
    public String format;
    public int pageCount;
    public List<TextObjects> textObjects;
    public String resourceURI;
    //public List<Url> urls;

    //public List<Character> variants;
    //public List<Character> collections;
    //public List<Character> collectedIssues;
    //public List<Character> dates;
    //public List<Character> prices;
    public Thumbnail thumbnail;
    //public List<Image> images;
    //public Character creators;
    //public Character characters;
    //public Character stories;
    //public Character events;

    public Comic(final Comic body) {
        id = body.id;
        digitalId = body.digitalId;
        title = body.title;
        variantDescription = body.variantDescription;
        description = body.description;
        isbn = body.isbn;
        resourceURI = body.resourceURI;
        //urls = body.urls;
        thumbnail = body.thumbnail;
        //modifiedSince = body.modifiedSince;
        //issueNumber = body.issueNumber;
        //images = body.images;
    }
}
