package com.photogallery.amrelmasry.simplephotogallery.common.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PhotosRepositoryImpTest {

    @Mock
    PhotosRepository photosRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        photosRepository = new PhotosRepositoryImp();
    }


    @Test
    public void getPhotosUrls_shouldReturnListOfUrlsWithTheSameLength() throws Exception {
        // Arrange
        int count = 5;
        // Act
        List<String> photosUrls = photosRepository.getPhotosUrls(count);
        // Assert
        assertThat(photosUrls)
                .isNotNull()
                .hasSize(count);
    }
}