package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepository;
import com.photogallery.amrelmasry.simplephotogallery.common.mvpbase.BasePresenter;

import java.util.ArrayList;

import javax.inject.Inject;

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
        ArrayList<String> photosUrls = photosRepository.getPhotosUrls(photosCount);
        checkViewAttached();
        getView().showPhotos(photosUrls);
    }
}
