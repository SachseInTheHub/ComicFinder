package com.sachse.comicfinder.model;

import com.sachse.comicfinder.api.models.Image;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Character extends RealmObject {

    @PrimaryKey
    private String name;
    private String description;
    private String birth;
    private Image image;
    private String imageIconUrl;
    private String imageMediumUrl;
    private String imageSmallUrl;

    public Character() {
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setImage(final Image image) {
        this.image = image;
        setImageIconUrl(image.getIconUrl());
        setImageSmallUrl(image.getSmallUrl());
        setImageMediumUrl(image.getMediumUrl());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageIconUrl() {
        return imageIconUrl = image.getIconUrl();
    }

    public String getImageMediumUrl() {
        return imageMediumUrl = image.getMediumUrl();
    }

    public String getImageSmallUrl() {
        return image.getSmallUrl();
    }

    public void setImageIconUrl(String imageIconUrl) {
        this.imageIconUrl = imageIconUrl;
    }

    public void setImageMediumUrl(String imageMediumUrl) {
        this.imageMediumUrl = imageMediumUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
