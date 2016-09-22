package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.CharacterDataWrapper;

import rx.Observable;

public interface CharacterService {

    Observable<CharacterDataWrapper> getAllCharacters();

    Observable<CharacterDataWrapper> getCharacterByName(String name);
}
