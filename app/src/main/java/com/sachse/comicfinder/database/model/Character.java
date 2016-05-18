package com.sachse.comicfinder.database.model;


import android.support.annotation.Nullable;

import com.sachse.comicfinder.api.models.Thumbnail;
import com.sachse.comicfinder.api.models.Url;

import java.util.List;

public class Character implements CharacterModel{

	public int id;
	public String name;
	public String description;
	public String resourceURI;
	public List<Url> urls;
	public Thumbnail thumbnail;

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getResourceURI() {
		return resourceURI;
	}

	public List<Url> getUrls() {
		return urls;
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public Character(Character character) {
		id = character.id;
		name = character.name;
		description = character.description;
		resourceURI = character.resourceURI;
		urls = character.urls;
		thumbnail = new Thumbnail(character.thumbnail);
	}

	public String getThumbnailResourcePath() {
		return thumbnail.getResourcePath();
	}

	@Nullable
	@Override
	public Long _id() {
		return (long)id;
	}

	@Nullable
	@Override
	public String name() {
		return name;
	}

	@Nullable
	@Override
	public String description() {
		return description;
	}

	@Nullable
	@Override
	public String resourceURI() {
		return resourceURI;
	}

	@Nullable
	@Override
	public String thumbnail() {
		return thumbnail.getResourcePath();
	}

	public static final class Marshal extends CharacterMarshal<Marshal>{
		public Marshal(){
			super();
		}
	}
}
