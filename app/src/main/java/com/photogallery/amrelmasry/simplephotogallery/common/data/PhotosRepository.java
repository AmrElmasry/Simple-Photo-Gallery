package com.photogallery.amrelmasry.simplephotogallery.common.data;

import android.graphics.Bitmap;

import rx.Observable;

public interface PhotosRepository {
    Observable<Bitmap> getPhotos(int photosCount);
}