package com.photogallery.amrelmasry.simplephotogallery.common.injection;

import android.content.Context;

import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepository;
import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepositoryImp;
import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridContract;
import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public PhotosRepository providePhotosRepository() {
        return new PhotosRepositoryImp(mContext);
    }

    @Provides
    public PhotosGridContract.Presenter provideGridPresenter(PhotosRepository photosRepository) {
        return new PhotosGridPresenter(photosRepository);
    }
}
