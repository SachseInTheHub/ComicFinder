package com.sachse.comicfinder.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.API;
import com.sachse.comicfinder.api.models.Character;
import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.api.models.Comic;
import com.sachse.comicfinder.api.models.ComicDataWrapper;
import com.sachse.comicfinder.ui.BaseActivity;
import com.sachse.comicfinder.ui.views.ComicsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends BaseActivity {

	public RecyclerView mComicsRV;
	public ComicsAdapter mComicsAdapter;
	public RecyclerView.LayoutManager mComicsLayoutManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		mComicsRV = (RecyclerView) findViewById(R.id.comics_rv);
		mComicsRV.setHasFixedSize(true);
		mComicsLayoutManager = new LinearLayoutManager(this);
		mComicsRV.setLayoutManager(mComicsLayoutManager);

		if(getIntent().hasExtra(API.SEARCH_QUERY)){
			final String query = getIntent().getStringExtra(API.SEARCH_QUERY);
			performCall(query);
		}
		getSupportActionBar().setHomeButtonEnabled(true);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_revert, R.anim.slide_out_revert);
	}

	private void performCall(String query) {
		Call<CharacterDataWrapper> call = mApiCall.getCharacterByName(query);

		call.enqueue(new Callback<CharacterDataWrapper>() {
			@Override
			public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {

				if (response.isSuccess()) {
					CharacterDataWrapper characterDataWrapper = new CharacterDataWrapper(response.body());
					final int size = characterDataWrapper.data.results.size();

					if (size >= 1) {
						Character character = new Character(characterDataWrapper.data.results.get(0));
						callCharacterComics(character);
						final String name = characterDataWrapper.data.results.get(0).name;
						if(name != null){
							getSupportActionBar().setTitle(name);
						}
					}
				}
			}

			@Override
			public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
				Log.d("Call", "fail" + t.toString());
			}
		});
	}

	private void callCharacterComics(final Character character){
//		mApiCall.getCharacterComics(id)
//				.subscribeOn(Schedulers.newThread())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(new Subscriber<ComicDataWrapper>() {
//					@Override
//					public void onCompleted() {
//
//					}
//
//					@Override
//					public void onError(Throwable e) {
//						Log.d("ComicFinder", "Error "+e.getMessage());
//					}
//
//					@Override
//					public void onNext(ComicDataWrapper comicDataWrapper) {
//						final List<Comic> comics = comicDataWrapper.data.results;
//						mComicsAdapter = new ComicsAdapter(getApplicationContext(), comics);
//						mComicsRV.setAdapter(mComicsAdapter);
//					}
//				});


		mApiCall.getCharacterComics(character.id).enqueue(new Callback<ComicDataWrapper>() {
			@Override
			public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
				final ComicDataWrapper comicDataWrapper = new ComicDataWrapper(response.body());
				final List<Comic> comics = comicDataWrapper.data.results;
				mComicsAdapter = new ComicsAdapter(getApplicationContext(), character, comics);
				mComicsRV.setAdapter(mComicsAdapter);
			}

			@Override
			public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
				Log.d("Call", "fail"+t.toString());
			}
		});
	}
}
