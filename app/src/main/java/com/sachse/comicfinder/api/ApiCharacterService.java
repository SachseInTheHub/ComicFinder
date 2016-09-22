package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.CharacterDataWrapper;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Scheduler;

public class ApiCharacterService implements CharacterService {

    private final Api api;
    private final Scheduler ioScheduler;

    public ApiCharacterService(Scheduler ioScheduler){
        this.ioScheduler = ioScheduler;
        api = ApiRetrofit.getRetrofit().create(Api.class);
    }

    @Override
    public Observable<CharacterDataWrapper> getAllCharacters() {
        return api.getAllCharacters().subscribeOn(ioScheduler);
    }

    @Override
    public Observable<CharacterDataWrapper> getCharacterByName(String name) {
        return api.getCharacterByName(name).subscribeOn(ioScheduler);
    }

    interface Api {

        @GET("characters") Observable<CharacterDataWrapper> getAllCharacters();

        @GET("characters") Observable<CharacterDataWrapper> getCharacterByName(@Query("name") String name);
    }
}
