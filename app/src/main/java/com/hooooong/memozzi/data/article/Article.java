package com.hooooong.memozzi.data.article;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Android Hong on 2018-02-22.
 */

public class Article implements Serializable {

    @SerializedName("board_id")
    private String boardId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("title")
    private String title;
    @SerializedName("category")
    private String catagory;
    @SerializedName("description")
    private String description;
    @SerializedName("setting_date")
    private String settingDate;
    @SerializedName("image_url")
    private String imageUrl;

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSettingDate() {
        return settingDate;
    }

    public void setSettingDate(String settingDate) {
        this.settingDate = settingDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Article{" +
                "boardId='" + boardId + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", catagory='" + catagory + '\'' +
                ", description='" + description + '\'' +
                ", settingDate='" + settingDate + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
