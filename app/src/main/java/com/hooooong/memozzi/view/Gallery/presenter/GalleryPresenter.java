package com.hooooong.memozzi.view.Gallery.presenter;

import android.content.Context;

import com.hooooong.memozzi.data.photo.Photo;
import com.hooooong.memozzi.data.photo.source.PhotoRepository;
import com.hooooong.memozzi.data.photo.source.PhotoSource;
import com.hooooong.memozzi.view.Gallery.contract.GalleryContract;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by Android Hong on 2018-02-26.
 */

@EBean
public class GalleryPresenter implements GalleryContract.iPresenter{

    @Bean(PhotoRepository.class)
    PhotoSource repository;

    GalleryContract.iView view;

    @Override
    public void attachView(GalleryContract.iView view) {
        this.view = view;
    }

    @Override
    public List<Photo> loadImages(Context context) {
        return repository.loadPhotoList(context);
    }
}
