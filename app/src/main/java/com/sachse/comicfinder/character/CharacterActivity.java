package com.sachse.comicfinder.character;

import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

public class CharacterActivity extends BaseActivity implements CharacterPresenter.View {

    private CharacterPresenter presenter;

    private PublishSubject<String> characterSubject = PublishSubject.create();

    @BindView(R.id.textview_character_name) TextView characterNameView;
    @BindView(R.id.imageview_character_thumbnail) ImageView characterThumbnailView;
    @BindView(R.id.textview_character_dob) TextView characterDateOfBirth;
    @BindView(R.id.textview_character_power) TextView characterPower;
    @BindView(R.id.textview_character_aliases) TextView characterAliases;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);

        presenter = Shank.provideSingleton(CharacterPresenter.class);
        presenter.onViewAttached(this);

        String name = getIntent().getStringExtra("name");
        if (name != null) {
            characterSubject.onNext(name);
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override public Observable<String> fetchCharacterByName() {
        return characterSubject.filter(name -> name != null);
    }

    @Override public void showCharacterName(final String characterName) {
        characterNameView.setText(characterName.toUpperCase());
    }

    @Override public void showCharacterThumbnail(final String thumbnailResourcePath) {
        Picasso.with(getApplicationContext())
                .load(thumbnailResourcePath)
                .into(characterThumbnailView);
    }

    @Override public void showCharacterDateOfBirth(String birth) {
        characterDateOfBirth.setText(birth);
    }

    @Override public void showCharacterPowers(String powers) {
        characterPower.setText(powers);
    }

    @Override public void showCharacterAliases(String aliases) {
        characterAliases.setText(aliases);
    }
}