package com.sachse.comicfinder.database.model;

import com.sachse.comicfinder.api.models.Thumbnail;

public class Character {

    public Long _id;
    public String name;
    public java.lang.String description;
    public java.lang.String resourceURI;
    public Thumbnail thumbnail;

    public Long getId() {
        return _id;
    }

    public java.lang.String getName() {
        return name;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public java.lang.String getResourceURI() {
        return resourceURI;
    }

    public long _id() {
        return _id;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
