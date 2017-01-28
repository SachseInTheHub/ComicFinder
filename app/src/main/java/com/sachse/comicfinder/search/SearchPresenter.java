package com.sachse.comicfinder.search;

import com.sachse.comicfinder.BasePresenter;

import rx.Observable;

class SearchPresenter extends BasePresenter<SearchPresenter.View> {

    public SearchPresenter() {

    }

    @Override public void onViewAttached(View view) {

    }

    public interface View {

        Observable<Void> onEditViewClicked();
        Observable<Void> goToCharacterClicked();
    }
}
