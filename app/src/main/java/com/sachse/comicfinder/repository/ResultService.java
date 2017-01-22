package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.model.Character;

import rx.Observable;

public interface ResultService {

    void fetchCharacterFromAPI(int characterId);
    void fetchListOfCharactersFromApi(int[] charactersIds);

    Character fetchCharacterFromDB(String characterName);

    Observable<Character> onDataRefresh();
}
