package com.sachse.comicfinder.result;

import com.sachse.comicfinder.BasePresenter;
import com.sachse.comicfinder.api.CharacterService;
import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.model.Character;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import rx.Scheduler;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by nelson on 15/09/2016.
 */
public class ResultPresenter extends BasePresenter<ResultPresenter.View> {

    private CharacterService characterService;
    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    public ResultPresenter(CharacterService characterService, Scheduler ioScheduler, Scheduler uiScheduler) {
        this.characterService = characterService;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void onViewAttached(View view) {
        addSubscription(characterService.getAllCharacters()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(new Subscriber<CharacterDataWrapper>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError");
                    }

                    @Override
                    public void onNext(CharacterDataWrapper characterDataWrapper) {

                        final List<Character> characters = new ArrayList<>();
                        Realm realm = Realm.getDefaultInstance();

                        for (int i = 0; i < 100; i++) {
                            final Character character = characterDataWrapper.data.results.get(i);
                            realm.beginTransaction();
//                            realm.copyToRealm(character);
                            realm.commitTransaction();

                            characters.add(character);
                        }

                        view.setCharacters(characters);
                    }
                }));
    }

    public interface View {

        void setCharacters(List<Character> characters);
    }
}
