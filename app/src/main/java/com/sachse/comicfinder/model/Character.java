package com.sachse.comicfinder.model;

import com.sachse.comicfinder.api.models.Thumbnail;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Character extends RealmObject {

    private int id;
    @PrimaryKey private String name;
    private String description;
    private String resourceURI;
    private Thumbnail thumbnail;
    private String thumbnailResourcePath;

    public Character() {
    }

    public void setId(final int id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setResourceURI(final String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public void setThumbnail(final Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setThumbnailResourcePath(final String thumbnailResourcePath) {
        this.thumbnailResourcePath = thumbnailResourcePath;
    }

    public int getId() {
        return id;
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

    public String getThumbnailResourcePath() {
        return thumbnailResourcePath;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
