package com.sachse.comicfinder.repository;

import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.io.FileStorage;
import com.sachse.comicfinder.model.Character;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

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
        String fields = String.format("%s,%s,%s,%s,%s", "name", "aliases", "birth", "power", "image");
        characterService.getCharacterById(1699, fields)
                .subscribeOn(ioScheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> response.getResults())
                .doOnError(Throwable::printStackTrace)
                .subscribe(fileStorage::storeCharacter, Throwable::printStackTrace);
    }

    @Override
    public Observable<Character> onDataRefresh() {
        return fileStorage.onDataChanged();
    }

    @Override public Observable<Object> fetchAllCharactersFromApi() {
        characterService.getAllCharacters().subscribe(response -> Timber.d(response.getResults().getName()));
        return Observable.empty();
    }
}
