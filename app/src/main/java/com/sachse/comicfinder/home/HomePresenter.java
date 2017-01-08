package com.sachse.comicfinder.home;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.repository.DataRepository;

import rx.Scheduler;
import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomePresenter.View> {

    private final DataRepository dataRepository;
    private final Scheduler uiScheduler;
    private final Scheduler ioScheduler;

    public HomePresenter(DataRepository dataRepository, Scheduler uiScheduler, Scheduler ioScheduler) {
        this.dataRepository = dataRepository;
        this.uiScheduler = uiScheduler;
        this.ioScheduler = ioScheduler;
    }

    @Override
    public void onViewAttached(View view) {
        addSubscription(dataRepository.fetchCharacter("thor")
                .observeOn(uiScheduler)
                .subscribe(character -> {
                    if (character != null) {
                        Timber.d("do nothing for now");
                    }
                }, throwable -> Timber.d("error showing character info :" + throwable.getMessage())));


        addSubscription(dataRepository.onDataRefresh()
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .subscribe(character -> {
                    Timber.d("onDataRefresh " + Thread.currentThread());
                    if (character != null) {
                        view.showCharacterName(character.getName());
                        view.showCharacterThumbnail(character.getThumbnailResourcePath());
                    }
                }, throwable -> Timber.d("onDataRefresh :" + throwable.getMessage())));
    }

    public interface View {

        void showCharacterName(String characterName);

        void showCharacterThumbnail(String thumbnailResourcePath);
    }
}
