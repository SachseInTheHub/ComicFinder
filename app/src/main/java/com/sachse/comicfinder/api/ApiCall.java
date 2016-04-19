package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.api.models.ComicDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {

	@GET("characters")
	Call<CharacterDataWrapper> getCharacterByName(@Query("name") String name);

	@GET("characters/{characterId}/comics")
	Call<ComicDataWrapper> getCharacterComics(@Path("characterId") int characterId);
}