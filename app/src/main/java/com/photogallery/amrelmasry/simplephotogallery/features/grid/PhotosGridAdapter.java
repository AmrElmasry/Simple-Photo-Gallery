package com.photogallery.amrelmasry.simplephotogallery.features.grid;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.StorageUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.PhotosViewHolder> {

    private ArrayList<String> photosList;
    private Context mContext;

    public PhotosGridAdapter(ArrayList<String> photosPaths, Context mContext) {
        this.photosList = photosPaths;
        this.mContext = mContext;
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PhotosViewHolder holder, final int position) {
        String photoPath = photosList.get(position);

        File file = new File(photoPath);
        Picasso.with(mContext)
                .load(file)
                .placeholder(R.drawable.placeholder).
                into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public ArrayList<String> getPhotosList() {
        return photosList;
    }


    public void add(String photoPath) {
        photosList.add(photoPath);
    }


    public void clear() {
        this.photosList.clear();
        StorageUtils.clearSavedPhotos(mContext).subscribe();
    }


    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemImageView)
        ImageView imageView;
        @BindView(R.id.itemProgressBar)
        ProgressBar progressBar;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
