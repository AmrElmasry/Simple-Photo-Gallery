package com.photogallery.amrelmasry.simplephotogallery.common.injection;

import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepository;
import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepositoryImp;
import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridContract;
import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public PhotosRepository providePhotosRepository() {
        return new PhotosRepositoryImp();
    }

    @Provides
    public PhotosGridContract.Presenter provideGridPresenter(PhotosRepository photosRepository) {
        return new PhotosGridPresenter(photosRepository);
    }
}
