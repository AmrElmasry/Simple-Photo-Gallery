package com.photogallery.amrelmasry.simplephotogallery.common.data;

import android.content.Context;
import android.graphics.Bitmap;

import com.photogallery.amrelmasry.simplephotogallery.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.observers.TestSubscriber;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PhotosRepositoryImpTest {

    @Mock
    Context context;
    private PhotosRepository photosRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        photosRepository = new PhotosRepositoryImp(context);
    }

    @Test
    public void getPhotosShould_shouldReturnObservablesWithBitmaps() throws Exception {

        // Arrange
        TestSubscriber<Bitmap> testSubscriber = new TestSubscriber<>();

        // Act
        photosRepository.getPhotos(2).subscribe(testSubscriber);

        // Assert
        testSubscriber.assertNoErrors();

    }
}