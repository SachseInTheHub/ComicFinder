package com.sachse.comicfinder.landing;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.model.Character;
import com.sachse.comicfinder.repository.DataRepository;

import java.util.List;

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
        addSubscription(dataRepository.fetchAllCharacterFromDB()
                .subscribeOn(ioScheduler)
                .subscribe(view::populateAdapter));

        addSubscription(dataRepository.onDataRefresh()
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .subscribe(view::updateAdapter));
    }

    public interface View {

        void updateAdapter(Character character);

        void populateAdapter(List<Character> characterList);
    }
}
