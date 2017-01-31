package com.sachse.comicfinder.search;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.model.Character;
import com.sachse.comicfinder.repository.DataRepository;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

public class SearchPresenter extends BasePresenter<SearchPresenter.View> {

    private final DataRepository dataRepository;
    private final Scheduler uiScheduler;
    private final Scheduler ioScheduler;

    public SearchPresenter(DataRepository dataRepository, Scheduler uiScheduler, Scheduler ioScheduler) {
        this.dataRepository = dataRepository;
        this.uiScheduler = uiScheduler;
        this.ioScheduler = ioScheduler;
    }

    @Override public void onViewAttached(View view) {
        addSubscription(view.onSearchSubmitted()
                .subscribeOn(uiScheduler)
                .observeOn(uiScheduler)
                .doOnError(Throwable::printStackTrace)
                .subscribe(query -> dataRepository.searchCharacter(query).subscribe(view::showSearchQuery))

        );
    }

    public interface View {

        Observable<String> onSearchSubmitted();
        Observable<Void> goToCharacterClicked();

        void showSearchQuery(List<Character> characters);
    }
}
