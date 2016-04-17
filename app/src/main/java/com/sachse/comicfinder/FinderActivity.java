package com.sachse.comicfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sachse.comicfinder.api.models.ApiResults;
import com.sachse.comicfinder.api.models.ApiStatus;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinderActivity extends BaseActivity implements View.OnClickListener{

	public FloatingActionButton mFloatingActionButton;
	public EditText mSearchField;
	public ImageView mProfileIV;
	public TextView mProfileHistoryTV;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_finder);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    mFloatingActionButton.setOnClickListener(this);
    mSearchField = (EditText) findViewById(R.id.search_et);
	  mProfileIV = (ImageView) findViewById(R.id.profile_iv);
	  mProfileHistoryTV = (TextView) findViewById(R.id.profile_story_tv);

	  mSearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		  @Override
		  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			  performCall();
			  return false;
		  }
	  });

  }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.fab:
				Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

				performCall();
				break;
		}
	}

	private void performCall(){
		Call<ApiStatus> call = mApiCall.getCharacterByName(mSearchField.getText().toString());
		call.enqueue(new Callback<ApiStatus>() {
			@Override
			public void onResponse(Call<ApiStatus> call, Response<ApiStatus> response) {

				if(response.isSuccess()) {
					ApiStatus apiStatus = new ApiStatus(response.body());
					final int size = apiStatus.data.results.size();
					Log.d("COMIC", "size: "+apiStatus.data.results.size());
					if(size >= 1) {
						ApiResults apiResults = new ApiResults(apiStatus.data.results.get(0));

						String profileUrl = apiResults.thumbnail.getResourcePath();
						Picasso.with(getApplicationContext()).load(profileUrl).into(mProfileIV);
						mProfileHistoryTV.setText(apiStatus.data.results.get(0).description);
					} else {
						mProfileHistoryTV.setText("No results!");
					}

				}
			}

			@Override
			public void onFailure(Call<ApiStatus> call, Throwable t) {
				Log.d("Call", "fail"+t.toString());
			}
		});
	}
}
