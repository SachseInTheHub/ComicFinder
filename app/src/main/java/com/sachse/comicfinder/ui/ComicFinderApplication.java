package com.sachse.comicfinder.ui;

import com.memoizrlabs.Shank;
import com.memoizrlabs.ShankModuleInitializer;
import com.sachse.comicfinder.di.ApplicationShankModule;
import com.sachse.comicfinder.di.PresenterShankModule;
import com.sachse.comicfinder.di.ServiceModule;
import com.sachse.comicfinder.di.StorageShankModule;
import com.sachse.comicfinder.repository.DataRepository;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ComicFinderApplication extends Application {

    private DataRepository dataRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        ShankModuleInitializer.initializeModules(new ApplicationShankModule(this),
                new ServiceModule(),
                new PresenterShankModule(),
                new StorageShankModule());

        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(config);

        fetchInitialCharacters();
    }

    private void fetchInitialCharacters() {
        int[] list = { 1699, 1702, 1455, 5569 };
        dataRepository = Shank.provideSingleton(DataRepository.class);
        if (dataRepository.isEmpty()) {
            dataRepository.fetchListOfCharactersFromApi(list);
        }
    }

    public static ComicFinderApplication from(final Context context) {
        if (context instanceof ComicFinderApplication) {
            return (ComicFinderApplication) context;
        } else {
            return (ComicFinderApplication) context.getApplicationContext();
        }
    }
}
