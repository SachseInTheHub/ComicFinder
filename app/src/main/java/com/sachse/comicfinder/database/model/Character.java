package com.sachse.comicfinder.database.model;

import android.support.annotation.Nullable;

public class Character implements CharacterModel {

	public final Long id;
	public final String name;
	public final String description;
	public final String resourceURI;
//	public final String thumbnail;

	public Character(Long id, String name, String description, String resourceURI, String thumbnail){
		this.id = id;
		this.name = name;
		this.description = description;
		this.resourceURI = resourceURI;
//		this.thumbnail = thumbnail;
	}

	@Nullable
	@Override
	public Long _id() {
		return id;
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

	public static final class Marshal extends CharacterMarshal<Marshal>{
		public Marshal(){
			super();
		}
	}
}
