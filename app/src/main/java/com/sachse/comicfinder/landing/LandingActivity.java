package com.sachse.comicfinder.landing;

import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.character.CharacterActivity;
import com.sachse.comicfinder.model.Character;
import com.sachse.comicfinder.ui.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class LandingActivity extends BaseActivity implements LandingPresenter.View {

    private final LandingAdapter adapter = new LandingAdapter();

    private LandingPresenter presenter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        presenter = Shank.provideSingleton(LandingPresenter.class);
        presenter.onViewAttached(this);

        setViews();
    }

    @Override protected void onResume() {
        super.onResume();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override public void updateAdapter(Character character) {
        adapter.addCharacter(character);
        adapter.notifyDataSetChanged();
    }

    @Override public void populateAdapter(List<Character> characters) {
        adapter.setCharacters(characters);
        recyclerView.setAdapter(adapter);
    }

    private void setViews() {
        toolbar.setTitle("Comic Finder");

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        adapter.onItemClicked().subscribe(viewHolder -> {
            String characterName = viewHolder.getCharacterName();

            Intent intent = new Intent(this, CharacterActivity.class);
            intent.putExtra("name", characterName);
            startActivity(intent);

            Timber.d("" + characterName);
        });
    }
}
