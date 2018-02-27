package com.hooooong.memozzi.data.sign.source;

import com.hooooong.memozzi.data.user.User;
import com.hooooong.memozzi.service.ServiceGenerator;
import com.hooooong.memozzi.service.api.SignAPI;

import io.reactivex.Observable;

/**
 * Created by Android Hong on 2018-02-22.
 */

public class SignRemoteRepository implements SignSource{

    private static SignRemoteRepository instance;

    private SignAPI signAPI;

    public static SignRemoteRepository getInstance() {
        if (instance == null)
            instance = new SignRemoteRepository();
        return instance;
    }

    private SignRemoteRepository() {
        signAPI = ServiceGenerator.create(SignAPI.class);
    }

    @Override
    public Observable<User> signUp(String email, String password) {
        return signAPI.signUp(email, password);
    }

    @Override
    public Observable<User> signIn(String email, String password) {
        return signAPI.signIn(email, password);
    }
}
