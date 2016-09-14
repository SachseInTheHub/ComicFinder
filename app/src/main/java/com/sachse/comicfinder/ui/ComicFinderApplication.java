package com.sachse.comicfinder.ui;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ComicFinderApplication extends Application {
    public static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getDefaultInstance();
    }
}
