package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import android.graphics.Bitmap;

import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepository;
import com.photogallery.amrelmasry.simplephotogallery.common.mvpbase.BasePresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import timber.log.Timber;

public class PhotosGridPresenter
        extends BasePresenter<PhotosGridContract.View>
        implements PhotosGridContract.Presenter {

    private PhotosRepository photosRepository;

    @Inject
    public PhotosGridPresenter(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }

    @Override
    public void downloadPhotos(int photosCount) {
        checkViewAttached();
        Observable<Bitmap> photos = photosRepository.getPhotos(photosCount);
        addSubscription(photos.subscribe(new Observer<Bitmap>() {
            @Override
            public void onCompleted() {
                Timber.d("Downloading photos completed");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("Failed to download photo " + e.getMessage());
            }

            @Override
            public void onNext(Bitmap bitmap) {
                Timber.d("Photo downloaded successfully");
                getView().savePhoto(bitmap);
            }
        }));

    }

    @Override
    public void onPhotoSaved(String filePath) {
        checkViewAttached();
        getView().showPhoto(filePath);
    }
}
