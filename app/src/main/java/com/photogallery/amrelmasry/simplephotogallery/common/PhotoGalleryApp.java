package com.photogallery.amrelmasry.simplephotogallery.common;

import android.app.Application;
import android.content.Context;

import com.photogallery.amrelmasry.simplephotogallery.common.injection.AppComponent;
import com.photogallery.amrelmasry.simplephotogallery.common.injection.AppModule;
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

    }

    public AppComponent getAppComponent(Context context) {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(context)).build();
        }
        return appComponent;
    }

    public void releaseBrowseMoviesComponent() {
        appComponent = null;
    }
}
