package com.photogallery.amrelmasry.simplephotogallery.common.data;

import java.util.ArrayList;

public interface PhotosRepository {

    ArrayList<String> getPhotosUrls(int count);
}
