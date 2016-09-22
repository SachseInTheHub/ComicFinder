package com.sachse.comicfinder.ui;

import android.app.Application;
import android.content.Context;

import com.memoizrlabs.ShankModuleInitializer;
import com.sachse.comicfinder.di.ApplicationShankModule;
import com.sachse.comicfinder.di.PresenterShankModule;
import com.sachse.comicfinder.di.ServiceModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ComicFinderApplication extends Application {
    public static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
//        realm = Realm.getDefaultInstance();

        ShankModuleInitializer.initializeModules(new ApplicationShankModule(this),
                        new ServiceModule(),
                        new PresenterShankModule());
    }

    public static ComicFinderApplication from(Context context) {
        if (context instanceof ComicFinderApplication) {
            return (ComicFinderApplication) context;
        } else {
            return (ComicFinderApplication) context.getApplicationContext();
        }
    }
}
