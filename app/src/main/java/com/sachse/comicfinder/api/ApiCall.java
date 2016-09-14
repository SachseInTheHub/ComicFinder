package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.api.models.ComicDataWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiCall {

	@GET("characters")
	Observable<CharacterDataWrapper> getAllCharacters(@Query("limit")int limit, @Query("offset") int offset);

	@GET("characters")
	Observable<CharacterDataWrapper> getCharacterByName(@Query("name") String name);

	@GET("characters/{characterId}/comics")
	Observable<ComicDataWrapper> getCharacterComics(@Path("characterId") int characterId, @Query("format") String format, @Query("orderBy") String orderBy);
}