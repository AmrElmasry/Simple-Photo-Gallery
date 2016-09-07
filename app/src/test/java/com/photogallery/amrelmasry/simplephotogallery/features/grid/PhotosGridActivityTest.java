package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import android.support.v7.widget.RecyclerView;

import com.photogallery.amrelmasry.simplephotogallery.BuildConfig;
import com.photogallery.amrelmasry.simplephotogallery.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.android.recyclerview.v7.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class PhotosGridActivityTest {

    private PhotosGridActivity photosGridActivity;

    @Before
    public void setUp() throws Exception {
        photosGridActivity = Robolectric.setupActivity(PhotosGridActivity.class);
    }

    @Test
    public void photosGridActivity_shouldHaveRecyclerViewWhenCreated() throws Exception {
        RecyclerView recyclerView = (RecyclerView) photosGridActivity.findViewById(R.id.gridRecyclerView);
        assertThat(recyclerView)
                .isNotNull()
                .isVisible();
    }
}