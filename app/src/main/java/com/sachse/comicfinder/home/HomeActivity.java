package com.sachse.comicfinder.home;

import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.models.Thumbnail;
import com.sachse.comicfinder.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    @BindView(R.id.character_name_tv) TextView characterNameTextView;
    @BindView(R.id.character_thumbnail_iv) ImageView characterThumbnailImageView;
    @BindView(R.id.character_description_tv) TextView characterDescriptionTextView;

    private HomePresenter presenter;

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
        presenter.onViewAttached(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override public void showCharacterName(String characterName) {
        characterNameTextView.setText(characterName);
    }

    @Override public void showCharacterThumbnail(Thumbnail characterThumbnail) {
        Picasso.with(getApplicationContext())
                .load(characterThumbnail.getResourcePath())
                .into(characterThumbnailImageView);
    }

    @Override public void showCharacterDescription(String description) {
        characterDescriptionTextView.setText(description);
    }
}
