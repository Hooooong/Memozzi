package com.hooooong.memozzi.service;

/**
 * Created by Android Hong on 2017-11-29.
 */

import com.hooooong.memozzi.data.Const;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Hong on 2017-11-16.
 */

public class ServiceGenerator {

    private static final String TAG = "ServiceGenerator";

    /**
     * Retrofit2 생성
     *
     * @param className
     * @param <I>
     * @return
     */
    public static <I> I create(Class<I> className) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(className);
    }

}