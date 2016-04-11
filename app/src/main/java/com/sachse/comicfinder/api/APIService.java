package com.sachse.comicfinder.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

	@GET("characters")
	Call<String> getCharacterList();

	@GET("characters")
	Call<String> getCharacterByName(@Query("name") String name);

	@GET("comics")
	Call<String> getComics(@Query("name") String name);
}