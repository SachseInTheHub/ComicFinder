package com.sachse.comicfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sachse.comicfinder.api.API;
import com.sachse.comicfinder.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinderActivity extends BaseActivity implements View.OnClickListener{
    public FloatingActionButton mFloatingActionButton;
	public EditText mSearchField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_finder);
	    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	    setSupportActionBar(toolbar);

	    mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
	    mFloatingActionButton.setOnClickListener(this);
	    mSearchField = (EditText) findViewById(R.id.search_et);
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.fab:
				Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

				APIService service = API.getRetrofit().create(APIService.class);
				Call<String> call = service.getCharacterByName(mSearchField.getText().toString());

				call.enqueue(new Callback<String>() {
					@Override
					public void onResponse(Call<String> call, Response<String> response) {
						Log.d("Call", "response: "+response.body());
					}

					@Override
					public void onFailure(Call<String> call, Throwable t) {
						Log.d("Call", "fail"+t.toString());
					}
				});

				break;
		}
	}


}
