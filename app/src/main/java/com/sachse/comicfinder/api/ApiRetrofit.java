package com.sachse.comicfinder.api;

import com.sachse.comicfinder.BuildConfig;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

final class ApiRetrofit {

    static final String BASE_URL = "http://comicvine.gamespot.com/api/";

    private ApiRetrofit() {
    }

    static Retrofit getRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl.Builder builder = originalHttpUrl.newBuilder()
                    .addQueryParameter("format", "json")
                    .addQueryParameter("api_key", BuildConfig.KEY);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Request.Builder requestBuilder = original.newBuilder()
                    .url(builder.build())
                    .method(original.method(), original.body());

            Request request = requestBuilder
                    .addHeader("Accept", "application/json")
                    .build();

            return chain.proceed(request);
        });

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
