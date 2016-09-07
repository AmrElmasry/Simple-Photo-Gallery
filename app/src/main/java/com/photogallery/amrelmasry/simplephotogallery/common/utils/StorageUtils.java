package com.photogallery.amrelmasry.simplephotogallery.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import timber.log.Timber;

public class StorageUtils {

    public static void savePhotoToInternalStorage(Context context, Bitmap bitmapImage, String photoName) {
        File path = new File(context.getFilesDir(), photoName);
        try (FileOutputStream fos = new FileOutputStream(path)) {
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Timber.d("Photo " + photoName + " saved successfully");
        } catch (Exception e) {
            Timber.e("Failed to save photo " + photoName + e.getMessage());
        }
    }

    public static Bitmap loadPhotoFromInternalStorage(Context context, String photoName) {
        String path = context.getFilesDir().getPath();
        try {
            File f = new File(path, photoName);
            Timber.d("Photo " + photoName + " loaded successfully");
            return BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            Timber.e("Failed to load photo " + photoName + e.getMessage());
            return null;
        }
    }


}
