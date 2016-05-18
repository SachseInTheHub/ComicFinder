package com.sachse.comicfinder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sachse.comicfinder.database.model.CharacterModel;

public class DatabaseManager extends SQLiteOpenHelper {

	public static final String DB_NAME = "comicfinder.db";
	public static final int DB_VERSION = 1;

	private static DatabaseManager instance;

	public static DatabaseManager getInstance(Context context) {
		if (instance == null) {
			instance = new DatabaseManager(context);
		}
		return instance;
	}

	private DatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CharacterModel.CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
