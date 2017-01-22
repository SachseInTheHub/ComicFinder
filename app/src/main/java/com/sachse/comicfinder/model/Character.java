package com.sachse.comicfinder.model;

import com.sachse.comicfinder.api.models.Image;
import com.sachse.comicfinder.api.models.Powers;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Character extends RealmObject {

    @PrimaryKey
    private String name;
    private String description;
    private String birth;
    private String aliases;

    private RealmList<Powers> powers;

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
        setImageIconUrl(image.getIconUrl());
        setImageSmallUrl(image.getSmallUrl());
        setImageMediumUrl(image.getMediumUrl());
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public String getIconUrl() {
        return imageIconUrl;
    }

    public String getMediumUrl() {
        return imageMediumUrl;
    }

    public String getSmallUrl() {
        return imageSmallUrl;
    }

    public Image getImage() {
        return image;
    }

    public String getAliases() {
        return aliases.replace("\n", ", ");
    }

    public RealmList<Powers> getPowers() {
        return powers;
    }

    public String getPowersList() {
        StringBuilder characterPowerList = new StringBuilder();
        for (Powers power : powers) {
            characterPowerList.append(power.getName()).append(", ");
        }
        return characterPowerList.toString();
    }

    public void setPowers(RealmList<Powers> powers) {
        this.powers = powers;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }
}
