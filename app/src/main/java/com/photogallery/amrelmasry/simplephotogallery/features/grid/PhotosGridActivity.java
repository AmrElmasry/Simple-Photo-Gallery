package com.photogallery.amrelmasry.simplephotogallery.features.grid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.photogallery.amrelmasry.simplephotogallery.R;
import com.photogallery.amrelmasry.simplephotogallery.common.PhotoGalleryApp;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.EndlessRecyclerViewScrollListener;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.ItemClickSupport;
import com.photogallery.amrelmasry.simplephotogallery.common.utils.StorageUtils;
import com.photogallery.amrelmasry.simplephotogallery.features.slider.PhotoSliderActivity;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class PhotosGridActivity extends AppCompatActivity implements PhotosGridContract.View {

    public static final String CURRENT_POSITION = "current_position";
    public static final String PHOTOS_COUNT = "photos_count";
    public static final String PHOTOS_PATHS = "photos_paths";

    @Inject
    PhotosGridContract.Presenter photosGridPresenter;

    @BindView(R.id.gridRecyclerView)
    RecyclerView gridRecyclerView;

    @BindView(R.id.gridProgressbar)
    ProgressBar progressBar;

    private PhotosGridAdapter mPhotosGridAdapter;
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_grid);

        // setup dependencies
        PhotoGalleryApp application = (PhotoGalleryApp) getApplication();
        application.getAppComponent(this).inject(this);
        // setup ButterKnife
        ButterKnife.bind(this);

        mCompositeSubscription = new CompositeSubscription();

        // get the spanCount according to the device orientation
        int spanCount = getResources().getInteger(R.integer.spanCount);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        gridRecyclerView.setLayoutManager(layoutManager);

        mPhotosGridAdapter = new PhotosGridAdapter(new ArrayList<String>(), this);
        gridRecyclerView.setAdapter(mPhotosGridAdapter);
        gridRecyclerView.setHasFixedSize(true);

        photosGridPresenter.attachView(this);
        photosGridPresenter.downloadPhotos(4);

        ItemClickSupport.addTo(gridRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(PhotosGridActivity.this, PhotoSliderActivity.class);
                intent.putExtra(CURRENT_POSITION, position);
                intent.putExtra(PHOTOS_PATHS, mPhotosGridAdapter.getPhotosList());
                intent.putExtra(PHOTOS_COUNT, mPhotosGridAdapter.getItemCount());
                startActivity(intent);
            }
        });

        gridRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                photosGridPresenter.downloadPhotos(4);
                Timber.d("Loading More...");
            }
        });
    }


    @Override
    protected void onDestroy() {
        photosGridPresenter.detachView();
        mPhotosGridAdapter.clear();
        mCompositeSubscription.clear();
        ((PhotoGalleryApp) getApplication()).releaseBrowseMoviesComponent();
        super.onDestroy();
    }

    @Override
    public void showPhoto(String photoPath) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
        int adapterSize = mPhotosGridAdapter.getItemCount();
        mPhotosGridAdapter.add(photoPath);
        mPhotosGridAdapter.notifyItemRangeInserted(adapterSize, 1);
    }

    @Override
    public void savePhoto(Bitmap bitmap) {
        // generate random number as the photo name
        Random random = new Random();
        int seq = random.nextInt();
        mCompositeSubscription.add(StorageUtils.savePhotoToInternalStorage(this, bitmap, String.valueOf(seq)).subscribe(new Action1<String>() {
            @Override
            public void call(String path) {
                photosGridPresenter.onPhotoSaved(path);
            }
        }));
    }
}
