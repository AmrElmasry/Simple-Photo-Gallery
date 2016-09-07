package com.photogallery.amrelmasry.simplephotogallery.common.data;

import java.util.List;

public interface PhotosRepository {

    List<String> getPhotosUrls(int count);
}
