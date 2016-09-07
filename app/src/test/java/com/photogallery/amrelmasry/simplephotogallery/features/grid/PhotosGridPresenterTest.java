package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PhotosGridPresenterTest {

    @Mock
    PhotosGridPresenter photosGridPresenter;

    @Mock
    PhotosGridContract.View view;

    @Mock
    PhotosRepository photosRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        photosGridPresenter = new PhotosGridPresenter(photosRepository);
        photosGridPresenter.attachView(view);
    }

    @Test
    public void downloadPhotos_shouldCallShowPhotosOnAttachedView() throws Exception {
        // Arrange
        ArrayList<String> photosUrls = new ArrayList<>();
        photosUrls.add("http://lorempixel.com/300/500/");
        photosUrls.add("http://lorempixel.com/350/200/");
        when(photosRepository.getPhotosUrls(anyInt()))
                .thenReturn(photosUrls);
        // Act
        photosGridPresenter.downloadPhotos(anyInt());
        // Assert
        verify(view).showPhotos(photosUrls);
    }
}