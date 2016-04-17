package com.sachse.comicfinder.api;

import com.sachse.comicfinder.api.models.ApiStatus;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

	@GET("characters")
	Call<String> getCharacterList();

	@GET("characters")
	Call<ApiStatus> getCharacterByName(@Query("name") String name);

	@GET("comics")
	Call<String> getComics(@Query("name") String name);
}