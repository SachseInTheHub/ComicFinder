package com.sachse.comicfinder.api.models;

import io.realm.RealmObject;

public class Thumbnail extends RealmObject {

    private String path;
    private String extension;

    public Thumbnail() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(final String extension) {
        this.extension = extension;
    }

    public String getResourcePath() {
        return path + "." + extension;
    }
}

