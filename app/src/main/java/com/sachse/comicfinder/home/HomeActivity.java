package com.sachse.comicfinder.home;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    @BindView(R.id.textview_character_name) TextView characterNameView;
    @BindView(R.id.imageview_character_thumbnail) ImageView characterThumbnailView;
    @BindView(R.id.textview_character_description) TextView characterDescriptionView;
    @BindView(R.id.edittext_search) EditText searchCharacterView;

    HomePresenter presenter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
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

    @Override public Observable<CharSequence> onSearchClicked() {

        return RxTextView.textChanges(searchCharacterView)
                .filter(t -> t.length() >= 6);

    }

    @Override public void showCharacterName(final String characterName) {
        characterNameView.setText(characterName.toUpperCase());
    }

    @Override public void showCharacterThumbnail(final String thumbnailResourcePath) {
        Picasso.with(getApplicationContext())
                .load(thumbnailResourcePath)
                .into(characterThumbnailView);
    }

    @Override public void showCharacterDescription(String description) {
        characterDescriptionView.setText(description);
    }
}
