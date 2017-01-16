package com.sachse.comicfinder.landing;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.model.Character;
import com.sachse.comicfinder.repository.DataRepository;

import rx.Scheduler;

public class LandingPresenter extends BasePresenter<LandingPresenter.View> {

    private final DataRepository dataRepository;
    private final Scheduler uiScheduler;
    private final Scheduler ioScheduler;

    public LandingPresenter(DataRepository dataRepository, Scheduler uiScheduler, Scheduler ioScheduler) {
        this.dataRepository = dataRepository;
        this.uiScheduler = uiScheduler;
        this.ioScheduler = ioScheduler;
    }

    @Override public void onViewAttached(View view) {
        int[] list = { 1699, 1702, 1455 };
        addSubscription(dataRepository.fetchListOfCharactersFromApi(list)
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .subscribe());

        addSubscription(dataRepository.onDataRefresh()
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .subscribe(view::updateAdapter));
    }

    public interface View {

        void updateAdapter(Character character);
    }
}
