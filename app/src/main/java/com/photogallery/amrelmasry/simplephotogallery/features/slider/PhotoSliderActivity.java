package com.photogallery.amrelmasry.simplephotogallery.features.slider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.StorageUtils;
import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PhotoSliderActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private int mCurrentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_slider);

        ButterKnife.bind(this);

        // Using Immersive Full-Screen Mode
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);


        Intent intent = getIntent();
        int selectedItemPosition = intent.getIntExtra(PhotosGridActivity.CURRENT_POSITION, 0);
        int photosCount = intent.getIntExtra(PhotosGridActivity.PHOTOS_COUNT, 0);

        ArrayList<Bitmap> savedBitmaps = getSavedBitmaps(photosCount, selectedItemPosition);
        PhotoSliderAdapter photosGridAdapter = new PhotoSliderAdapter(this, savedBitmaps);

        viewPager.setAdapter(photosGridAdapter);
        viewPager.setCurrentItem(mCurrentPosition);

        Timber.d("Open position" + mCurrentPosition);
    }

    private ArrayList<Bitmap> getSavedBitmaps(int photosCount, int selectedItemPosition) {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        for (int i = 0; i < photosCount; i++) {
            Bitmap bitmap = StorageUtils.loadPhotoFromInternalStorage(this, String.valueOf(i));
            if (bitmap != null) {
                bitmaps.add(bitmap);
                // check if the selected bitmap position equals to the
                // current loaded bitmap
                if (selectedItemPosition == i) {
                    mCurrentPosition = bitmaps.indexOf(bitmap);
                }
            }
        }
        return bitmaps;
    }
}