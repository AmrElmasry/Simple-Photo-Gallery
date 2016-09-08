package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import android.graphics.Bitmap;

import com.photogallery.amrelmasry.simplephotogallery.BuildConfig;
import com.photogallery.amrelmasry.simplephotogallery.common.data.PhotosRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PhotosGridPresenterTest {

    @Mock
    PhotosGridContract.View view;
    @Mock
    PhotosRepository photosRepository;
    private PhotosGridPresenter photosGridPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        photosGridPresenter = new PhotosGridPresenter(photosRepository);
        photosGridPresenter.attachView(view);
    }

    @Test
    public void downloadPhotos_shouldCallSavePhotoOnAttachedView() throws Exception {
        // Arrange
        Bitmap bm1 = Bitmap.createBitmap(100, 200, Bitmap.Config.ARGB_8888);
        when(photosRepository.getPhotos(anyInt())).thenReturn(Observable.just(bm1));

        // Act
        photosGridPresenter.downloadPhotos(anyInt());

        // Assert
        verify(view).savePhoto(bm1);
    }

    @Test
    public void onPhotoSaved_shouldCallShowPhotoOnAttachedView() throws Exception {
        // Arrange
        String photoPath = anyString();

        // Act
        photosGridPresenter.onPhotoSaved(photoPath);

        // Assert
        verify(view).showPhoto(photoPath);
    }
}