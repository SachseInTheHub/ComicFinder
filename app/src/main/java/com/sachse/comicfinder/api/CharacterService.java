package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.Response;

import rx.Observable;

public interface CharacterService {

    Observable<Response> getAllCharacters();

    Observable<Response> getCharacterByName(String name);

    Observable<Response> getCharacterById(int characterId, String fields);
}
