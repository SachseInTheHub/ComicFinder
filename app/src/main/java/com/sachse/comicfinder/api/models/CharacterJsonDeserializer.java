package com.sachse.comicfinder.api.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sachse.comicfinder.database.model.Character;

import java.lang.reflect.Type;

public class CharacterJsonDeserializer implements JsonDeserializer<Character> {
	@Override
	public Character deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		return new Character(
				obj.get("id").getAsLong(),
				obj.get("name").getAsString(),
				obj.get("thumbnail").getAsString(),
				obj.get("resourceURI").getAsString(),
				obj.get("description").getAsString());
	}
}
