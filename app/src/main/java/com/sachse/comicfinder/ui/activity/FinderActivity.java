package com.sachse.comicfinder.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.api.API;
import com.sachse.comicfinder.ui.BaseActivity;

public class FinderActivity extends BaseActivity {

	public EditText mSearchField;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_finder);
	  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	  setSupportActionBar(toolbar);
	  
	  mSearchField = (EditText) findViewById(R.id.search_et);
	  mSearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		  @Override
		  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			  final String query = mSearchField.getText().toString();

			  Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
			  intent.putExtra(API.SEARCH_QUERY, query);
			  startActivityForResult(intent, 0);

			  overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
			  return false;
		  }
	  });
  }


}
