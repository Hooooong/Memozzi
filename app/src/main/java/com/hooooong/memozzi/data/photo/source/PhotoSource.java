package com.hooooong.memozzi.data.photo.source;

import android.content.Context;

import com.hooooong.memozzi.data.photo.Photo;

import java.util.List;

/**
 * Created by Android Hong on 2018-02-26.
 */

public interface PhotoSource {

    List<Photo> loadPhotoList(Context context);
}
