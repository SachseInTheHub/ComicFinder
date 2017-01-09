package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.io.FileStorage;
import com.sachse.comicfinder.model.Character;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class DataRepository implements ResultService {

    private final FileStorage fileStorage;
    private final CharacterService characterService;
    private final Scheduler ioScheduler;

    public DataRepository(final FileStorage fileStorage,
            final CharacterService characterService,
            final Scheduler ioScheduler) {
        this.fileStorage = fileStorage;
        this.characterService = characterService;
        this.ioScheduler = ioScheduler;
    }

    public Observable<Character> fetchCharacter(final String characterName) {
        final Character character = fetchCharacterFromDB(characterName);

        if (character != null) {
            return Observable.just(character);
        } else {
            fetchCharacterFromAPI(characterName);
            return Observable.empty();
        }
    }

    @Override
    public Character fetchCharacterFromDB(final String characterName) {
        return fileStorage.getCharacter(characterName);
    }

    @Override
    public void fetchCharacterFromAPI(final String characterName) {
        characterService.getCharacterByName(characterName)
                .subscribeOn(ioScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharacterDataWrapper::getCharacter)
                .doOnError(Throwable::printStackTrace)
                .subscribe(fileStorage::storeCharacter, Throwable::printStackTrace);
    }

    @Override
    public Observable<Character> onDataRefresh() {
        return fileStorage.onDataChanged();
    }
}
