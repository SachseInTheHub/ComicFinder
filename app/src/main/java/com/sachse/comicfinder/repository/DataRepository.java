package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.api.models.Response;
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
            return Observable.empty();
        }
    }

    @Override
    public Character fetchCharacterFromDB(final String characterName) {
        return fileStorage.getCharacter(characterName);
    }

    @Override
    public void fetchCharacterFromAPI(final int characterId) {
        String fields = String.format("%s,%s,%s,%s,%s", "name", "aliases", "birth", "power", "image");

        characterService.getCharacterById(characterId, fields)
                .subscribeOn(ioScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .map(Response::getResults)
                .doOnError(Throwable::printStackTrace)
                .subscribe(fileStorage::storeCharacter, Throwable::printStackTrace);
    }

    @Override
    public Observable<Character> onDataRefresh() {
        return fileStorage.onDataChanged();
    }

    @Override public Observable<Object> fetchListOfCharactersFromApi(int[] charactersIds) {
        for (int i = 0; i < charactersIds.length; i++) {
            fetchCharacterFromAPI(charactersIds[i]);
        }

        return Observable.empty();
    }
}
