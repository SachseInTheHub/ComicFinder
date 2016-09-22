package com.sachse.comicfinder;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T> {

    private CompositeSubscription subscriptions = new CompositeSubscription();

    public abstract void onViewAttached(T view);

    public void onViewDetached() {
        subscriptions.clear();
    }

    public void addSubscription(Subscription subscribe) {
        subscriptions.add(subscribe);
    }
}
