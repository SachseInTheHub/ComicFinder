package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.model.Character;

import rx.Observable;

public interface ResultService {

    Character fetchCharacterFromDB(String characterName);

    void fetchCharacterFromAPI(String characterName);

    Observable<Character> onDataRefresh();
}
