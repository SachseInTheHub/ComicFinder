package com.sachse.comicfinder.ui.activity;

import com.sachse.comicfinder.R;
import com.sachse.comicfinder.result.ResultActivity;
import com.sachse.comicfinder.ui.BaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class FinderActivity extends BaseActivity {

    public EditText mSearchField;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSearchField = (EditText) findViewById(R.id.search_et);
        mSearchField.setOnEditorActionListener((v, actionId, event) -> {
            final String query = mSearchField.getText().toString();
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            startActivityForResult(intent, 0);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            return false;
        });
    }
}
