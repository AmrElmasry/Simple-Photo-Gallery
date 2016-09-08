package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import android.graphics.Bitmap;

import com.photogallery.amrelmasry.simplephotogallery.common.mvpbase.MvpPresenter;
import com.photogallery.amrelmasry.simplephotogallery.common.mvpbase.MvpView;

public interface PhotosGridContract {
    interface View extends MvpView {
        void showPhoto(String filePath);

        void savePhoto(Bitmap bitmap);
    }

    interface Presenter extends MvpPresenter<PhotosGridContract.View> {
        void downloadPhotos(int photosCount);

        void onPhotoSaved(String filePath);
    }
}
