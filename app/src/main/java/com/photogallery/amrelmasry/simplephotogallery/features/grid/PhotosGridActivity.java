package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.photogallery.amrelmasry.simplephotogallery.common.PhotoGalleryApp;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.EndlessRecyclerViewScrollListener;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.ItemClickSupport;
import com.photogallery.amrelmasry.simplephotogallery.features.slider.PhotoSliderActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PhotosGridActivity extends AppCompatActivity implements PhotosGridContract.View {

    public static final String CURRENT_POSITION = "current_position";
    public static final String PHOTOS_COUNT = "photos_count";

    @Inject
    PhotosGridContract.Presenter photosGridPresenter;

    @BindView(R.id.gridRecyclerView)
    RecyclerView gridRecyclerView;

    private PhotosGridAdapter photosGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_grid);

        // setup dependencies
        PhotoGalleryApp application = (PhotoGalleryApp) getApplication();
        application.getAppComponent().inject(this);
        // setup ButterKnife
        ButterKnife.bind(this);

        // get the spanCount according to the device orientation
        int spanCount = getResources().getInteger(R.integer.spanCount);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        gridRecyclerView.setLayoutManager(layoutManager);

        photosGridAdapter = new PhotosGridAdapter(new ArrayList<String>(), this);
        gridRecyclerView.setAdapter(photosGridAdapter);

        photosGridPresenter.attachView(this);
        photosGridPresenter.downloadPhotos(8);

        ItemClickSupport.addTo(gridRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(PhotosGridActivity.this, PhotoSliderActivity.class);
                intent.putExtra(CURRENT_POSITION, position);
                intent.putExtra(PHOTOS_COUNT, photosGridAdapter.getItemCount());
                startActivity(intent);
            }
        });

        gridRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                photosGridPresenter.downloadPhotos(8);
                Timber.d("Loading More...");
            }
        });
    }

    @Override
    public void showPhotos(ArrayList<String> photosUrls) {
        photosGridAdapter.addAll(photosUrls);
    }

    @Override
    protected void onDestroy() {
        photosGridPresenter.detachView();
        photosGridAdapter.clear();
        super.onDestroy();
    }
}
