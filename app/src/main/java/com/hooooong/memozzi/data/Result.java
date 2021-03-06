package com.hooooong.memozzi.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android Hong on 2018-02-26.
 */

public class Result {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
