package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.model.Character;

import java.util.List;

import rx.Observable;

public interface ResultService {

    void fetchCharacterFromAPI(int characterId);
    void fetchListOfCharactersFromApi(int[] charactersIds);

    Character fetchCharacterFromDB(String characterName);
    Observable<Character> onDataRefresh();
    Observable<List<Character>> searchCharacter(String query);
}
