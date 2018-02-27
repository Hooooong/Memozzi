package com.hooooong.memozzi.data.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2018-02-22.
 */

public class User {

    @SerializedName("user_id")
    private String userId;
    @SerializedName("nickname")
    private String nickName;
    @SerializedName("provider")
    private String provider;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
