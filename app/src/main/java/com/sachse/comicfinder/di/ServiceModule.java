package com.sachse.comicfinder.di;

import com.memoizrlabs.Shank;
import com.memoizrlabs.ShankModule;
import com.sachse.comicfinder.api.ApiCharacterService;
import com.sachse.comicfinder.api.CharacterService;

import rx.Scheduler;

import static com.memoizrlabs.Shank.registerFactory;

public class ServiceModule implements ShankModule {

    @Override
    public void registerFactories() {
        registerFactory(ApiCharacterService.class, () -> new ApiCharacterService(
                Shank.named("io").provideSingleton(Scheduler.class)));

        registerFactory(CharacterService.class, () -> new ApiCharacterService(
                Shank.named("io").provideSingleton(Scheduler.class)));
    }
}
