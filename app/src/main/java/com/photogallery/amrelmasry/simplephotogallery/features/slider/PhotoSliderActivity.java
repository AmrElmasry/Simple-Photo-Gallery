package com.photogallery.amrelmasry.simplephotogallery.features.slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.photogallery.amrelmasry.simplephotogallery.features.grid.PhotosGridActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoSliderActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

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
        int currentItemPosition = intent.getIntExtra(PhotosGridActivity.CURRENT_POSITION, 0);

        ArrayList<String> photosPaths = (ArrayList<String>)
                intent.getSerializableExtra(PhotosGridActivity.PHOTOS_PATHS);

        PhotoSliderAdapter photosGridAdapter = new PhotoSliderAdapter(this, photosPaths);

        viewPager.setAdapter(photosGridAdapter);
        viewPager.setCurrentItem(currentItemPosition);

    }
}