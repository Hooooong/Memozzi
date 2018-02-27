package com.hooooong.memozzi.view.Gallery;

import android.Manifest;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.photo.Photo;
import com.hooooong.memozzi.view.BaseActivity;
import com.hooooong.memozzi.view.Gallery.adapter.GalleryAdapter;
import com.hooooong.memozzi.view.Gallery.contract.GalleryContract;
import com.hooooong.memozzi.view.Gallery.presenter.GalleryPresenter;
import com.hooooong.memozzi.view.main.ArticleItemDivider;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity
public class GalleryActivity extends BaseActivity implements GalleryContract.iView, GalleryAdapter.GalleryListener {

    private static final String TAG = "GalleryActivity";
    private final int maxCount = 10;

    @Bean(GalleryPresenter.class)
    GalleryContract.iPresenter presenter;

    @ViewById(R.id.rv_gallery)
    RecyclerView recyclerView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private GalleryAdapter adapter;

    public GalleryActivity() {
        super(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_gallery);
    }

    @AfterInject
    void initObject() {
        presenter.attachView(this);
        adapter = new GalleryAdapter(this, true, maxCount);
    }

    @AfterViews
    void initToolbar(){
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(view -> {
            // Navigation Click 했을 경우에는
            finish();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @AfterViews
    void bindAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ArticleItemDivider(4, 5, false));
        // RecyclerView 애니메이션 제거
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    @AfterViews
    void loadGallery() {
        adapter.setPhotoList(presenter.loadImages(this));
    }

    @Override
    public void PhotoClick(int position) {
        Photo photo = adapter.getPhotoList().get(position);
        ArrayList<Photo> selectPhotoList = adapter.getSelectPhotoList();
        if (selectPhotoList.contains(photo)) {
            adapter.removeSelectPhotoList(photo);
        } else {
            adapter.addSelectPhotoList(photo);
        }
    }

    @Override
    public void changeView(int count) {
        if (count == 0) {
            // icon 변경 및 intent 설정
        } else {
            // icon 변경 및 intent 설정
        }
    }

    @Override
    public void selectError() {
        Toast.makeText(this, "사진은 최대 "+ maxCount +"개까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
    }
}
