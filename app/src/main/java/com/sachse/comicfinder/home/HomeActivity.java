package com.sachse.comicfinder.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    @BindView(R.id.top_character_thumbnail) ImageView topCharacterThumbnail;
    @BindView(R.id.middle_character_thumbnail) ImageView middleCharacterThumbnail;
    @BindView(R.id.bottom_character_thumbnail) ImageView bottomCharacterThumbnail;

    HomePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = Shank.provideSingleton(HomePresenter.class);
        presenter.onViewAttached(this);
    }

    @Override protected void onResume() {
        super.onResume();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override public void showCharacterName(String characterName) {
    }

    @Override public void showCharacterThumbnail(String thumbnailResourcePath) {
        Picasso.with(getApplicationContext())
                .load(thumbnailResourcePath)
                .into(topCharacterThumbnail);
    }
}
