package com.hooooong.memozzi.data.photo.source;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.hooooong.memozzi.data.photo.Photo;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2018-02-26.
 */
@EBean(scope = EBean.Scope.Singleton)
public class PhotoRepository implements PhotoSource {

    @Override
    public List<Photo> loadPhotoList(Context context) {
        ArrayList<Photo> photoList = new ArrayList<>();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DATE_MODIFIED
        };

        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                null,
                null,
                projection[1] + " DESC");
        while (cursor.moveToNext()) {
            Photo photo = new Photo();
            int index = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
            photo.setImagePath(cursor.getString(index));
            photoList.add(photo);
        }
        cursor.close();

        return photoList;
    }
}
