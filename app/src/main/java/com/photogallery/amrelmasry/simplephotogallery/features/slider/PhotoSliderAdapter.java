package com.photogallery.amrelmasry.simplephotogallery.features.slider;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class PhotoSliderAdapter extends PagerAdapter {
    private ArrayList<String> photosPaths;
    private LayoutInflater inflater;
    private Context mContext;

    public PhotoSliderAdapter(Context context,
                              ArrayList<String> imagePaths) {
        this.mContext = context;
        this.photosPaths = imagePaths;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.photosPaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = inflater.inflate(R.layout.slider_item, container, false);

        ImageView photoImageView = (ImageView) itemView.findViewById(R.id.fullImageView);

        String photoPath = photosPaths.get(position);
        File file = new File(photoPath);
        Picasso.with(mContext).load(file).into(photoImageView);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
