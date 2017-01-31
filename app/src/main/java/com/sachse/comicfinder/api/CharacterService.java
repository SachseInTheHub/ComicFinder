package com.sachse.comicfinder.api;

import com.sachse.comicfinder.model.Response;
import com.sachse.comicfinder.model.SearchResponse;

import rx.Observable;

public interface CharacterService {

    Observable<Response> getAllCharacters();

    Observable<Response> getCharacterByName(String name);

    Observable<Response> getCharacterById(int characterId, String fields);

    Observable<SearchResponse> searchCharacter(String fields, String resourcesToSearch, String characterName);
}
