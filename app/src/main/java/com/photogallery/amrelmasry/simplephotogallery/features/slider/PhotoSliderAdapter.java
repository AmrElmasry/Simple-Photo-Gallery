package com.photogallery.amrelmasry.simplephotogallery.features.slider;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.photogallery.amrelmasry.simplephotogallery.R;

import java.util.List;

public class PhotoSliderAdapter extends PagerAdapter {
    private List<Bitmap> photosBitmaps;
    private LayoutInflater inflater;

    public PhotoSliderAdapter(Context context,
                              List<Bitmap> imagePaths) {
        this.photosBitmaps = imagePaths;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        View itemView = inflater.inflate(R.layout.slider_item, container, false);

        ImageView photoImageView = (ImageView) itemView.findViewById(R.id.fullImageView);

        Bitmap bitmap = photosBitmaps.get(position);
        photoImageView.setImageBitmap(bitmap);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
