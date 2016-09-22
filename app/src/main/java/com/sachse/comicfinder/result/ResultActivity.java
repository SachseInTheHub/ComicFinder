package com.sachse.comicfinder.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.database.model.Character;
import com.sachse.comicfinder.ui.BaseActivity;
import com.sachse.comicfinder.ui.views.CharacterAdapter;
import com.sachse.comicfinder.ui.views.ComicsAdapter;

import java.util.List;

public class ResultActivity extends BaseActivity implements ResultPresenter.View {

    public RecyclerView mComicsRV;
    public ComicsAdapter mComicsAdapter;
    public RecyclerView.LayoutManager mComicsLayoutManager;
    private ResultPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mComicsRV = (RecyclerView) findViewById(R.id.comics_rv);
        mComicsRV.setHasFixedSize(true);
        mComicsLayoutManager = new LinearLayoutManager(this);
        mComicsRV.setLayoutManager(mComicsLayoutManager);

        getSupportActionBar().setHomeButtonEnabled(true);

        presenter = Shank.provideNew(ResultPresenter.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onViewDetached();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewAttached(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_revert, R.anim.slide_out_revert);
    }

    private void populateView(Character character) {
//		getSupportActionBar().setTitle(character.getName());

    }

    private void callCharacterComics(final Character character) {
//		mCall.getCharacterComics(id)
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


//        mCall.getCharacterComics(1, "comic", "onsaleDate").enqueue(new Callback<ComicDataWrapper>() {
//            @Override
//            public void onResponse(Call<ComicDataWrapper> call, Response<ComicDataWrapper> response) {
//                final ComicDataWrapper comicDataWrapper = new ComicDataWrapper(response.body());
//                final List<Comic> comics = comicDataWrapper.data.results;
//                mComicsAdapter = new ComicsAdapter(getApplicationContext(), character, comics);
//                mComicsRV.setAdapter(mComicsAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<ComicDataWrapper> call, Throwable t) {
//                Log.d("Call", "fail" + t.toString());
//            }
//        });
    }

    @Override
    public void setCharacters(List<Character> characters) {
        CharacterAdapter adapter = new CharacterAdapter(getApplicationContext(), characters);
        mComicsRV.setAdapter(adapter);
    }
}
