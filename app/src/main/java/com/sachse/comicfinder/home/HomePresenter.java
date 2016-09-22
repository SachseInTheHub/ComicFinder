package com.sachse.comicfinder.home;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.database.model.Character;

import rx.Observable;
import rx.Scheduler;
import timber.log.Timber;

public class HomePresenter extends BasePresenter<HomePresenter.View> {
    private final Scheduler uiScheduler;
    private final CharacterService characterService;

    public HomePresenter(final CharacterService characterService, final Scheduler uiScheduler) {
        this.characterService = characterService;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void onViewAttached(View view) {
        addSubscription(characterService.getCharacterByName("gambit")
                .observeOn(uiScheduler)
                .subscribe(characterDataWrapper -> {
                    if (characterDataWrapper.getCharacter() != null) {
                        Timber.e("got character");
                        view.showCharacter(characterDataWrapper.getCharacter());
                    }
                }, throwable -> {
                    Timber.e(throwable, "Failed to get car status");
                }));
    }

    public interface View {
        Observable<Void> onCharacterClicked();

        void showCharacter(Character character);

        void goToResults();
    }
}
