package com.photogallery.amrelmasry.simplephotogallery.common.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class StorageUtils {

    /**
     * this method saves a bitmap to internal storage and
     * returns with its absolute path
     */
    public static Observable<String> savePhotoToInternalStorage(final Context context, final Bitmap bitmapImage, final String photoName) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                File path = new File(context.getFilesDir(), photoName + ".png");

                try (FileOutputStream fos = new FileOutputStream(path)) {
                    // Use the compress method on the BitMap object to write image to the OutputStream
                    bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    subscriber.onNext(path.getAbsolutePath());
                } catch (Exception e) {
                    Timber.e("Failed to save photo " + photoName + e.getMessage());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * this method deletes all saved photos
     */
    public static Observable<Boolean> clearSavedPhotos(final Context context) {

        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {

                File f = new File(context.getFilesDir().getPath());
                // select all photos files
                File[] files = f.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String name) {
                        return name.endsWith(".png");
                    }
                });

                // delete all selected photos
                for (File file : files) {
                    boolean deleted = file.delete();
                    if (deleted) {
                        subscriber.onNext(true);
                        Timber.d("photo " + file.getName() + " deleted successfully");
                    } else {
                        Timber.e("couldn't delete photo " + file.getName());
                        subscriber.onNext(false);
                    }
                }
            }
        });
    }
}

