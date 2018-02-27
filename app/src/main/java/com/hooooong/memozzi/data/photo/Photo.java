package com.hooooong.memozzi.data.photo;

import java.io.Serializable;

/**
 * Created by Android Hong on 2018-02-26.
 */

public class Photo implements Serializable {

    private String imagePath;
    private String thumbnailPath;
    private String imageName;
    private String imageDate;

    public Photo() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

    @Override
    public boolean equals(Object obj) {

        return this.imagePath.equals(((Photo)obj).imagePath);
    }
}
