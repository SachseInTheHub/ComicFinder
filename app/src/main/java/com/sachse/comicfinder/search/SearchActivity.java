package com.sachse.comicfinder.search;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.ui.BaseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import rx.Observable;

import static android.support.v7.widget.SearchView.*;

public class SearchActivity extends BaseActivity implements SearchPresenter.View {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override public Observable<Void> onEditViewClicked() {
        return null;
    }

    @Override public Observable<Void> goToCharacterClicked() {
        return null;
    }
}
