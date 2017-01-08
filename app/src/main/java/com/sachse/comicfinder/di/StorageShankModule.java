package com.sachse.comicfinder.di;

import com.memoizrlabs.Shank;
import com.memoizrlabs.ShankModule;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.io.FileStorage;
import com.sachse.comicfinder.repository.DataRepository;
import com.sachse.comicfinder.repository.ResultService;

import rx.Scheduler;

import static com.memoizrlabs.Shank.provideSingleton;
import static com.memoizrlabs.Shank.registerFactory;

public class StorageShankModule implements ShankModule {

    @Override
    public void registerFactories() {
        registerFactory(FileStorage.class, FileStorage::new);

        registerFactory(DataRepository.class, () -> new DataRepository(
                provideSingleton(FileStorage.class),
                provideSingleton(CharacterService.class),
                Shank.named("io").provideSingleton(Scheduler.class)));

        registerFactory(ResultService.class, () -> new DataRepository(
                provideSingleton(FileStorage.class),
                provideSingleton(CharacterService.class),
                Shank.named("io").provideSingleton(Scheduler.class)));
    }
}
