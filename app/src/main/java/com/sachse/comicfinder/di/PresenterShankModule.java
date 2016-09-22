package com.sachse.comicfinder.di;

import com.memoizrlabs.ShankModule;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.home.HomePresenter;
import com.sachse.comicfinder.result.ResultPresenter;

import rx.Scheduler;

import static com.memoizrlabs.Shank.named;
import static com.memoizrlabs.Shank.provideSingleton;
import static com.memoizrlabs.Shank.registerFactory;

public class PresenterShankModule implements ShankModule {

    @Override
    public void registerFactories() {
        registerFactory(HomePresenter.class, () -> new HomePresenter(provideSingleton(CharacterService.class), named("ui").provideSingleton(Scheduler.class)));

        registerFactory(ResultPresenter.class, () -> {
            return new ResultPresenter(provideSingleton(CharacterService.class), provideSingleton(Scheduler.class), provideSingleton(Scheduler.class));
        });
    }
}
