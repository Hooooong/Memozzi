package com.hooooong.memozzi.util;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by Android Hong on 2018-02-22.
 */

public class JsonUtil {
    /**
     * Error 메세지 가져오는 메소드
     *
     * @param responseBody
     * @return
     */
    public static String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
