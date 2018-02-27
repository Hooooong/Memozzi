package com.hooooong.memozzi.data.sign.source;

import com.hooooong.memozzi.data.user.User;

import io.reactivex.Observable;

/**
 * Created by Android Hong on 2018-02-22.
 */

public class SignRepository implements SignSource {

    private SignRemoteRepository remoteRepository;

    private static SignRepository instance;

    public static SignRepository getInstance() {
        if (instance == null)
            instance = new SignRepository();
        return instance;
    }

    private SignRepository() {
        remoteRepository = SignRemoteRepository.getInstance();
    }

    @Override
    public Observable<User> signUp(String email, String password) {
        return remoteRepository.signUp(email, password);
    }

    @Override
    public Observable<User> signIn(String email, String password) {
        return remoteRepository.signIn(email, password);
    }
}
