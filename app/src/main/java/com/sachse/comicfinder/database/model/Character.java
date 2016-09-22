package com.sachse.comicfinder.database.model;

import com.sachse.comicfinder.api.models.Thumbnail;

public class Character {

    public Long _id;
    public String name;
    public String description;
    public String resourceURI;
	public Thumbnail thumbnail;

    public Long getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public long _id() {
        return _id;
    }

    public Thumbnail getThumbnail(){ return thumbnail; }

}
