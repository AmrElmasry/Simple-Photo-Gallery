package com.photogallery.amrelmasry.simplephotogallery.features.grid;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.StorageUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.PhotosViewHolder> {

    private List<String> photosUrls;
    private Context mContext;
    private ArrayList<Integer> savedPositions;

    public PhotosGridAdapter(List<String> photosUrls, Context mContext) {
        this.photosUrls = photosUrls;
        this.mContext = mContext;
        savedPositions = new ArrayList<>();
    }

    @Override
    public PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PhotosViewHolder holder, final int position) {
        String url = photosUrls.get(position);

        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // TODO add fade animation
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.imageView.setImageBitmap(bitmap);

                // check if the image with current position has been saved before
                if (!savedPositions.contains(holder.getAdapterPosition())) {
                    // use the photo position as the file name to be able to retrieve them
                    // with the same positions order later
                    StorageUtils.savePhotoToInternalStorage(mContext, bitmap, String.valueOf(holder.getAdapterPosition()));
                    savedPositions.add(holder.getAdapterPosition());
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                holder.imageView.setImageDrawable(placeHolderDrawable);
            }
        };

        holder.imageView.setTag(target);
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .into(target);
    }

    @Override
    public int getItemCount() {
        return photosUrls.size();
    }

    public void add(String url) {
        photosUrls.add(url);
    }

    public void addAll(List<String> photosUrls) {
        this.photosUrls.addAll(photosUrls);
    }

    public void clear() {
        this.photosUrls.clear();
        this.savedPositions.clear();
        StorageUtils.clearSavedPhotos(mContext);
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
