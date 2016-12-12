package com.sachse.comicfinder.home;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.api.models.Thumbnail;
import com.sachse.comicfinder.database.model.Character;

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
        // spider-man
        // hulk
        // thor
        // iron man
        addSubscription(characterService.getCharacterByName("thor")
                .observeOn(uiScheduler)
                .subscribe(characterDataWrapper -> {
                    Character character = characterDataWrapper.getCharacter();
                    if (character != null) {
                        view.showCharacterName(character.getName());
                        view.showCharacterThumbnail(character.getThumbnail());

                        String description = character.getDescription();
                        if (!description.isEmpty()) {
                            view.showCharacterDescription(description);
                        }
                    }
                }, throwable -> {
                    Timber.e(throwable, "Failed to get character");
                }));
    }

    public interface View {

        void showCharacterName(String characterName);
        void showCharacterThumbnail(Thumbnail characterThumbnail);
        void showCharacterDescription(String description);
    }
}
