package com.sachse.comicfinder.api.models;

import com.sachse.comicfinder.model.Character;

public class CharacterDataWrapper {

    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private CharacterDataContainer data;

    public CharacterDataWrapper(final CharacterDataWrapper body) {
        code = body.code;
        status = body.status;
        copyright = body.copyright;
        attributionHTML = body.attributionHTML;
        attributionText = body.attributionText;
        data = body.data;
    }

    public Character getCharacter() {
        return data.results.get(0);
    }
}
