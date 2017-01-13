package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Scheduler;

public class ApiCharacterService implements CharacterService {

    private final Api api;
    private final Scheduler ioScheduler;

    public ApiCharacterService(final Scheduler ioScheduler) {
        this.ioScheduler = ioScheduler;
        api = ApiRetrofit.getRetrofit().create(Api.class);
    }

    @Override
    public Observable<Response> getAllCharacters() {
        return api.getAllCharacters().subscribeOn(ioScheduler);
    }

    @Override
    public Observable<Response> getCharacterByName(final String name) {
        return api.getCharacterByName(name).subscribeOn(ioScheduler);
    }

    @Override public Observable<Response> getCharacterById(int characterId, String fields) {
        return api.getCharacterById(characterId, fields).subscribeOn(ioScheduler);
    }

    interface Api {

        @GET("characters") Observable<Response> getAllCharacters();

        @GET("character/") Observable<Response> getCharacterByName(String name);

        @GET("character/4005-{id}") Observable<Response> getCharacterById(@Path("id") int characterId, @Query("field_list") String fields);
    }
}
