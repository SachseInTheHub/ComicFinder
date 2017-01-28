package com.sachse.comicfinder.character;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.repository.DataRepository;

import rx.Observable;
import rx.Scheduler;
import timber.log.Timber;

public class CharacterPresenter extends BasePresenter<CharacterPresenter.View> {

    private final DataRepository dataRepository;
    private final Scheduler uiScheduler;

    public CharacterPresenter(final DataRepository dataRepository,
            final Scheduler uiScheduler) {
        this.dataRepository = dataRepository;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void onViewAttached(final View view) {

        addSubscription(view.fetchCharacterByName()
                .subscribe(dataRepository::fetchCharacterFromDB));

        addSubscription(dataRepository.onDataRefresh()
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .subscribe(character -> {
                    if (character != null) {
                        view.showCharacterName(character.getName());
                        view.showCharacterThumbnail(character.getImage().getMediumUrl());
                        view.showCharacterDateOfBirth(character.getBirth());
                        view.showCharacterAliases(character.getAliases());
                        view.showCharacterPowers(character.getPowersList());
                    }
                }, throwable -> Timber.d("onDataRefresh :" + throwable.getMessage())));
    }

    public interface View {

        Observable<String> fetchCharacterByName();

        void showCharacterName(String characterName);
        void showCharacterThumbnail(String thumbnailResourcePath);
        void showCharacterDateOfBirth(String birth);
        void showCharacterPowers(String powers);
        void showCharacterAliases(String aliases);
    }
}