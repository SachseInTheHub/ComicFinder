package com.sachse.comicfinder.landing;

import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.io.FileStorage;
import com.sachse.comicfinder.model.Character;
import com.sachse.comicfinder.ui.BaseActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingActivity extends BaseActivity implements LandingPresenter.View {

    private LandingAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LandingPresenter presenter;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        presenter = Shank.provideSingleton(LandingPresenter.class);
        presenter.onViewAttached(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<Character> allCharacters = new FileStorage().getAllCharacters();
        adapter = new LandingAdapter(allCharacters);

        recyclerView.setAdapter(adapter);
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
}
