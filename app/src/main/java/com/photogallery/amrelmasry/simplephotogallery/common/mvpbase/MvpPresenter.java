package com.photogallery.amrelmasry.simplephotogallery.common.mvpbase;

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
