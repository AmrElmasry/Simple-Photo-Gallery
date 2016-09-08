package com.photogallery.amrelmasry.simplephotogallery.common.mvpbase;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * any Presenter must extends this class or implements {@link MvpPresenter}
 */
public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;

    private CompositeSubscription compositeSubscription = new CompositeSubscription();


    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
        compositeSubscription.clear();
    }

    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public void addSubscription(Subscription subscription) {
        this.compositeSubscription.add(subscription);
    }
    public boolean isViewAttached() {
        return view != null;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
