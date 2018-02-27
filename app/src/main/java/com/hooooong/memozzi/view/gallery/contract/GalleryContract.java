package com.hooooong.memozzi.view.Gallery.contract;

import android.content.Context;

import com.hooooong.memozzi.data.photo.Photo;
import com.hooooong.memozzi.view.BasePresenter;

import java.util.List;

/**
 * Created by Android Hong on 2018-02-26.
 */

public interface GalleryContract {

    interface iView{

    }

    interface iPresenter extends BasePresenter<iView>{
        List<Photo> loadImages(Context context);
    }
}
