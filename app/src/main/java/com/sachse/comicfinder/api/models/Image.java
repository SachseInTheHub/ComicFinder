package com.sachse.comicfinder.api.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Image extends RealmObject {

    @SerializedName("icon_url")
    private String iconUrl;
    @SerializedName("medium_url")
    private String mediumUrl;
    @SerializedName("small_url")
    private String smallUrl;
    @SerializedName("super_url")
    private String superUrl;
    @SerializedName("tiny_url")
    private String tinyUrl;

    public String getIconUrl() {
        return iconUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getSuperUrl() {
        return superUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public void setSuperUrl(String superUrl) {
        this.superUrl = superUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }
}
