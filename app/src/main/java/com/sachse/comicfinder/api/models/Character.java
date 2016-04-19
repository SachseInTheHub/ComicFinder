package com.sachse.comicfinder.api.models;

import java.util.List;

public class Character {

	public int id;
	public String name;
	public String description;
	public String resourceURI;
	public List<Url> urls;
	public Thumbnail thumbnail;

	public Character(Character character) {
		id = character.id;
		name = character.name;
		description = character.description;
		resourceURI = character.resourceURI;
		urls = character.urls;
		thumbnail = new Thumbnail(character.thumbnail);
	}
}
