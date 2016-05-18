package com.sachse.comicfinder.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.database.model.Character;
import com.sachse.comicfinder.ui.BaseActivity;

public class HomeActivity extends BaseActivity {

	private ImageView mCharacterIV;
	private TextView mCharacterNameTV;
	private Character mCharacter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_home);

		mCharacterIV = (ImageView) findViewById(R.id.character_iv);
		mCharacterNameTV = (TextView) findViewById(R.id.character_name_tv);

		mCharacterIV.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
				startActivityForResult(intent, 0);
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
			}
		});
	}
}
