package com.sachse.comicfinder.di;

import com.memoizrlabs.ShankModule;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.character.CharacterPresenter;
import com.sachse.comicfinder.landing.LandingPresenter;
import com.sachse.comicfinder.repository.DataRepository;
import com.sachse.comicfinder.result.ResultPresenter;

import rx.Scheduler;

import static com.memoizrlabs.Shank.named;
import static com.memoizrlabs.Shank.provideSingleton;
import static com.memoizrlabs.Shank.registerFactory;

public class PresenterShankModule implements ShankModule {

    @Override
    public void registerFactories() {
        registerFactory(CharacterPresenter.class, () -> new CharacterPresenter(
                provideSingleton(DataRepository.class),
                named("ui").provideSingleton(Scheduler.class)));

        registerFactory(ResultPresenter.class, () -> new ResultPresenter(
                provideSingleton(CharacterService.class),
                provideSingleton(Scheduler.class),
                provideSingleton(Scheduler.class)));

        registerFactory(LandingPresenter.class, () -> new LandingPresenter(
                provideSingleton(DataRepository.class),
                named("ui").provideSingleton(Scheduler.class),
                named("io").provideSingleton(Scheduler.class)));
    }
}
