package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import com.photogallery.amrelmasry.simplephotogallery.common.mvpbase.MvpPresenter;
import com.photogallery.amrelmasry.simplephotogallery.common.mvpbase.MvpView;

import java.util.ArrayList;

public interface PhotosGridContract {
    interface View extends MvpView {
        void showPhotos(ArrayList<String> photosUrls);
    }

    interface Presenter extends MvpPresenter<PhotosGridContract.View> {
        void downloadPhotos(int photosCount);
    }
}
