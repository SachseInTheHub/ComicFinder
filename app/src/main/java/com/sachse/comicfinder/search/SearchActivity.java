package com.sachse.comicfinder.search;

import com.jakewharton.rxbinding.widget.RxSearchView;
import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.model.Character;
import com.sachse.comicfinder.ui.BaseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class SearchActivity extends BaseActivity implements SearchPresenter.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private SearchPresenter presenter;
    private SearchView searchView;
    private SearchAdapter adapter = new SearchAdapter();

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        presenter = Shank.provideSingleton(SearchPresenter.class);
        presenter.onViewAttached(this);
        return true;
    }

    @Override public Observable<String> onSearchSubmitted() {
        return RxSearchView.queryTextChangeEvents(searchView)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(searchViewQueryTextEvent -> searchViewQueryTextEvent.queryText().length() > 3)
                .debounce(500, TimeUnit.MILLISECONDS)
                .concatMap(searchViewQueryTextEvent -> Observable.just(searchViewQueryTextEvent.queryText()
                        .toString()));
    }

    @Override public Observable<Void> goToCharacterClicked() {
        return null;
    }

    @Override public void showSearchQuery(List<Character> characters) {
        adapter.setSearchAdapter(characters);
        for (Character character : characters) {
            Timber.d(character.getName());
        }
    }
}
