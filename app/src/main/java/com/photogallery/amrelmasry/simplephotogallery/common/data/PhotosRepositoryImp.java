package com.photogallery.amrelmasry.simplephotogallery.common.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * This class provides photos generated randomly
 * using this url : http://lorempixel.com/:width/:height/
 * by supplying it with random values for the width and the height
 */

public class PhotosRepositoryImp implements PhotosRepository {

    private static final String BASE_URL = "http://lorempixel.com/";
    private Context mContext;

    public PhotosRepositoryImp(Context mContext) {
        this.mContext = mContext;
    }

    public Observable<Bitmap> getPhotos(int count) {
        final ArrayList<String> urls = getPhotosUrls(count);

        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(final Subscriber<? super Bitmap> subscriber) {
                for (String url : urls) {
                    try {
                        // download photo using Picasso
                        Bitmap bitmap = Picasso.with(mContext).load(url).get();
                        subscriber.onNext(bitmap);
                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * this method generates random photos Urls with random dimensions
     */
    private ArrayList<String> getPhotosUrls(int urlsCount) {
        ArrayList<String> photosUrls = new ArrayList<>();
        // min and max photo dimensions
        final int min = 200;
        final int max = 500;

        Random random = new Random();

        for (int i = 0; i < urlsCount; i++) {
            int height = random.nextInt(max - min + 1) + min;
            int weight = random.nextInt(max - min + 1) + min;
            String url = String.format("%s/%s/%s/", BASE_URL, weight, height);
            photosUrls.add(url);
        }

        return photosUrls;
    }
}
