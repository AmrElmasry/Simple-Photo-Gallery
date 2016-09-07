package com.photogallery.amrelmasry.simplephotogallery.common;

import android.app.Application;

import com.photogallery.amrelmasry.simplephotogallery.common.injection.AppComponent;
import com.photogallery.amrelmasry.simplephotogallery.common.injection.DaggerAppComponent;

import timber.log.Timber;

public class PhotoGalleryApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                return super.createStackElementTag(element) + ":" + element.getLineNumber();
            }
        });

        appComponent = DaggerAppComponent.builder().build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
