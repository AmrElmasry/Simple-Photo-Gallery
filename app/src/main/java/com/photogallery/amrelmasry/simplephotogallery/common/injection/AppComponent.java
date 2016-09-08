package com.photogallery.amrelmasry.simplephotogallery.common.injection;

import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(PhotosGridActivity photosGridActivity);
}
