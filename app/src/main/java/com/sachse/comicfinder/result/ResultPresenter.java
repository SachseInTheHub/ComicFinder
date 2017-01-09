package com.sachse.comicfinder.result;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.model.Character;

import java.util.List;

import rx.Scheduler;

public class ResultPresenter extends BasePresenter<ResultPresenter.View> {

    private CharacterService characterService;
    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    public ResultPresenter(final CharacterService characterService,
            final Scheduler ioScheduler,
            final Scheduler uiScheduler) {
        this.characterService = characterService;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void onViewAttached(final View view) {
        addSubscription(characterService.getAllCharacters()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe());
    }

    interface View {

        void setCharacters(List<Character> characters);
    }
}
