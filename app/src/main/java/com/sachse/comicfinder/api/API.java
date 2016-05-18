package com.sachse.comicfinder.api;

import com.sachse.comicfinder.BuildConfig;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  API {
	public static final String BASE_URL = "http://gateway.marvel.com/v1/public/";
	public static final String SEARCH_QUERY = "SEARCH_QUERY";

	public static Retrofit getRetrofit() {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(logging);

		httpClient.addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request original = chain.request();
				HttpUrl originalHttpUrl = original.url();
				String timestamp = API.getTimestamp();

				HttpUrl.Builder builder = originalHttpUrl.newBuilder()
						.addQueryParameter("ts", timestamp)
						.addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
						.addQueryParameter("hash", getMd5(timestamp  + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY));

				Request.Builder requestBuilder = original.newBuilder()
						.url(builder.build())
						.method(original.method(), original.body());

				Request request = requestBuilder
						.addHeader("Accept", "application/json")
						.build();

				return chain.proceed(request);
			}
		});

		return new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(httpClient.build())
				.build();
	}

	private static String getTimestamp(){
		Long tsLong = System.currentTimeMillis()/1000;
		return tsLong.toString();
	}

	public static final String getMd5(final String s) {
		final String MD5 = "MD5";
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String h = Integer.toHexString(0xFF & aMessageDigest);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
