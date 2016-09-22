package com.sachse.comicfinder.api.models;

import com.sachse.comicfinder.database.model.Character;

public class CharacterDataWrapper {

	public int code;
	public String status;
	public String copyright;
	public String attributionText;
	public String attributionHTML;
	public CharacterDataContainer data;

	public CharacterDataWrapper(CharacterDataWrapper body) {
		code = body.code;
		status = body.status;
		copyright = body.copyright;
		attributionHTML = body.attributionHTML;
		attributionText = body.attributionText;
		data = body.data;
	}

	public Character getCharacter(){
		return data.results.get(0);
	}
}
