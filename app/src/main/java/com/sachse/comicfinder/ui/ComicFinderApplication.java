package com.sachse.comicfinder.ui;

import android.app.Application;
import android.content.Context;

import com.memoizrlabs.ShankModuleInitializer;
import com.sachse.comicfinder.di.ApplicationShankModule;
import com.sachse.comicfinder.di.PresenterShankModule;
import com.sachse.comicfinder.di.ServiceModule;
import com.sachse.comicfinder.di.StorageShankModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ComicFinderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ShankModuleInitializer.initializeModules(new ApplicationShankModule(this),
                new ServiceModule(),
                new PresenterShankModule(),
                new StorageShankModule());

        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(config);
    }

    public static ComicFinderApplication from(Context context) {
        if (context instanceof ComicFinderApplication) {
            return (ComicFinderApplication) context;
        } else {
            return (ComicFinderApplication) context.getApplicationContext();
        }
    }
}
