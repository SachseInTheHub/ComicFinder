package com.sachse.comicfinder.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.memoizrlabs.Shank;
import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.ApiCharacterService;
import com.sachse.comicfinder.api.models.CharacterDataWrapper;
import com.sachse.comicfinder.database.model.Character;
import com.sachse.comicfinder.ui.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import rx.Observable;
import timber.log.Timber;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    private ImageView mCharacterIV;
    @BindView(R.id.character_name_tv) TextView mCharacterNameTV;
    private Character mCharacter;
    private HomePresenter presenter;
    private ApiCharacterService apiCharacterService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        presenter = Shank.provideSingleton(HomePresenter.class);
        apiCharacterService = Shank.provideSingleton(ApiCharacterService.class);
        mCharacterIV = (ImageView) findViewById(R.id.character_iv);
        mCharacterNameTV = (TextView) findViewById(R.id.character_name_tv);
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
    public Observable<Void> onCharacterClicked() {
        return RxView.clicks(mCharacterIV);
    }

    @Override
    public void showCharacter(Character character) {
        mCharacterNameTV.setText(character.getName());
        Timber.d(character.getThumbnail().getResourcePath());
        Picasso.with(getApplicationContext()).load(character.getThumbnail().getResourcePath()).into(mCharacterIV);
    }

    @Override
    public void goToResults() {
        Observable<CharacterDataWrapper> allCharacters = apiCharacterService.getAllCharacters();


//        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//        startActivityForResult(intent, 0);
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
