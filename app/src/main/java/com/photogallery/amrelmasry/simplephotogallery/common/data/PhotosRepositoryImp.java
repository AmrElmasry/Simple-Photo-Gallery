package com.photogallery.amrelmasry.simplephotogallery.common.data;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class provides photos urls generated randomly
 * using this url : http://lorempixel.com/:width/:height/
 * by supplying it with random values for the width and the height
 * <p>
 * this could be replaced with a server call returns with a page of
 * photos urls
 */
public class PhotosRepositoryImp implements PhotosRepository {

    private static final String BASE_URL = "http://lorempixel.com/";


    @Override
    public ArrayList<String> getPhotosUrls(int photosCount) {
        ArrayList<String> photosUrls = new ArrayList<>();
        final int min = 300;
        final int max = 500;
        Random random = new Random();
        for (int i = 0; i < photosCount; i++) {
            int height = random.nextInt(max - min + 1) + min;
            int weight = random.nextInt(max - min + 1) + min;
            String url = String.format("%s/%s/%s/", BASE_URL, weight, height);
            photosUrls.add(url);
        }

        return photosUrls;
    }
}
