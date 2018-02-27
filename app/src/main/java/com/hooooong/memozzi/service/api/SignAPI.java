package com.hooooong.memozzi.service.api;

import com.hooooong.memozzi.data.user.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface SignAPI {

    // 회원가입 메소드
    @FormUrlEncoded
    @POST("/user/register")
    Observable<User> signUp(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/user/login")
    Observable<User> signIn(@Field("email") String email, @Field("password") String password);

}
