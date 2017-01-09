package com.sachse.comicfinder.di;

import com.memoizrlabs.ShankModule;

import android.content.Context;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.memoizrlabs.Shank.registerFactory;
import static com.memoizrlabs.Shank.registerNamedFactory;

public class ApplicationShankModule implements ShankModule {

    private Context context;

    public ApplicationShankModule(final Context context) {
        this.context = context;
    }

    @Override
    public void registerFactories() {
        registerFactory(Context.class, () -> context);
        registerNamedFactory(Scheduler.class, "io", Schedulers::io);
        registerNamedFactory(Scheduler.class, "ui", AndroidSchedulers::mainThread);
    }
}
