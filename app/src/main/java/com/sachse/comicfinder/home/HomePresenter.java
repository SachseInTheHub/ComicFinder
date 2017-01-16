package com.sachse.comicfinder.home;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.repository.DataRepository;

import rx.Observable;
import rx.Scheduler;
import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomePresenter.View> {

    private final DataRepository dataRepository;
    private final Scheduler uiScheduler;
    private final Scheduler ioScheduler;

    public HomePresenter(final DataRepository dataRepository,
            final Scheduler uiScheduler,
            final Scheduler ioScheduler) {
        this.dataRepository = dataRepository;
        this.uiScheduler = uiScheduler;
        this.ioScheduler = ioScheduler;
    }

    @Override
    public void onViewAttached(final View view) {

        addSubscription(view.onSearchClicked()
                .subscribe(textViewTextChangeEvent -> {
                    String characterSearch = textViewTextChangeEvent.toString();
                    Timber.d("search for " + characterSearch);
                    dataRepository.fetchCharacter(characterSearch);
                }));

        //addSubscription(dataRepository.fetchListOfCharactersFromApi()
        //        .observeOn(uiScheduler)
        //        .subscribe(character -> {
        //            if (character != null) {
        //                Timber.d("do nothing for now");
        //            }
        //        }, throwable -> Timber.d("error showing character info :" + throwable.getMessage())));

        addSubscription(dataRepository.onDataRefresh()
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .subscribe(character -> {
                    Timber.d("onDataRefresh " + Thread.currentThread());
                    if (character != null) {
                        view.showCharacterName(character.getName());
                        view.showCharacterThumbnail(character.getImage().getMediumUrl());
                        view.showCharacterDescription(character.getDescription());
                    }
                }, throwable -> Timber.d("onDataRefresh :" + throwable.getMessage())));
    }

    public interface View {

        Observable<CharSequence> onSearchClicked();

        void showCharacterName(String characterName);
        void showCharacterThumbnail(String thumbnailResourcePath);
        void showCharacterDescription(String description);
    }
}