package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.model.Character;

import rx.Observable;

public interface ResultService {

    Character fetchCharacterFromDB(String characterName);

    void fetchCharacterFromAPI(int characterId);

    Observable<Character> onDataRefresh();

    Observable<Object> fetchListOfCharactersFromApi(int[] charactersIds);
}
