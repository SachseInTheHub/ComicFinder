package com.sachse.comicfinder.database.model;

import io.realm.RealmObject;

public class Character extends RealmObject {

    public  Long _id;
    public String name;
    public String description;
    public String resourceURI;
//	public final String thumbnail;

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


}
