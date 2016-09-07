package com.photogallery.amrelmasry.simplephotogallery.features.slider;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.photogallery.amrelmasry.simplephotogallery.R;

import java.util.ArrayList;

public class PhotoSliderAdapter extends PagerAdapter {
    private Activity mActivity;
    private ArrayList<Bitmap> photosBitmaps;

    public PhotoSliderAdapter(Activity activity,
                              ArrayList<Bitmap> imagePaths) {
        this.mActivity = activity;
        this.photosBitmaps = imagePaths;
    }

    @Override
    public int getCount() {
        return this.photosBitmaps.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;

        LayoutInflater inflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.slider_item, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.fullImageView);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = photosBitmaps.get(position);
        imgDisplay.setImageBitmap(bitmap);


        container.addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);

    }
}
