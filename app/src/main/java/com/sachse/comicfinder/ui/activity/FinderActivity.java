package com.sachse.comicfinder.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.models.Character;
import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.api.models.Comic;
import com.sachse.comicfinder.api.models.ComicDataWrapper;
import com.sachse.comicfinder.ui.BaseActivity;
import com.sachse.comicfinder.ui.views.ComicsAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinderActivity extends BaseActivity {

	public EditText mSearchField;
	public ImageView mProfileIV;
	public TextView mProfileHistoryTV;
	public RecyclerView mComicsRV;
	public RecyclerView.Adapter mComicsAdapter;
	public RecyclerView.LayoutManager mComicsLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finder);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mSearchField = (EditText) findViewById(R.id.search_et);
	  mProfileIV = (ImageView) findViewById(R.id.profile_iv);
	  mProfileHistoryTV = (TextView) findViewById(R.id.profile_story_tv);

	  mComicsRV = (RecyclerView) findViewById(R.id.comics_rv);
	  mComicsRV.setHasFixedSize(true);

	  // use a linear layout manager
	  mComicsLayoutManager = new LinearLayoutManager(this);
	  mComicsRV.setLayoutManager(mComicsLayoutManager);

	  mSearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		  @Override
		  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			  performCall();
			  return false;
		  }
	  });
  }

	private void performCall(){
		Call<CharacterDataWrapper> call = mApiCall.getCharacterByName(mSearchField.getText().toString());

		call.enqueue(new Callback<CharacterDataWrapper>() {
			@Override
			public void onResponse(Call<CharacterDataWrapper> call, Response<CharacterDataWrapper> response) {

				if(response.isSuccess()) {
					CharacterDataWrapper characterDataWrapper = new CharacterDataWrapper(response.body());
					final int size = characterDataWrapper.data.results.size();

					if(size >= 1) {
						Character character = new Character(characterDataWrapper.data.results.get(0));
						callCharacterComics(character.id);

						String profileUrl = character.thumbnail.getResourcePath();
						Picasso.with(getApplicationContext()).load(profileUrl).into(mProfileIV);
						mProfileHistoryTV.setText(characterDataWrapper.data.results.get(0).description);
					} else {
						mProfileHistoryTV.setText("No results!");
					}
				}
			}

			@Override
			public void onFailure(Call<CharacterDataWrapper> call, Throwable t) {
				Log.d("Call", "fail"+t.toString());
			}
		});
	}

	private void callCharacterComics(int id){
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


		mApiCall.getCharacterComics(id).enqueue(new Callback<ComicDataWrapper>() {
			@Override
			public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
				ComicDataWrapper comicDataWrapper = new ComicDataWrapper(response.body());
				final List<Comic> comics = comicDataWrapper.data.results;
				mComicsAdapter = new ComicsAdapter(getApplicationContext(), comics);
				mComicsRV.setAdapter(mComicsAdapter);

			}

			@Override
			public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
				Log.d("Call", "fail"+t.toString());
			}
		});
	}
}
