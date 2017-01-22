package com.sachse.comicfinder.api.models;

import io.realm.RealmObject;

public class Powers extends RealmObject {

    private int id;
    private String aliases;
    private String description;
    private String name;

    public String getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
