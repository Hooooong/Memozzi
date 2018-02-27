package com.hooooong.memozzi.data.sign.source;

import com.hooooong.memozzi.data.user.User;

import io.reactivex.Observable;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface SignSource {

    interface Local{

    }
    interface Remote{

    }

    /**
     * SignUp 메소드
     *
     * @param email
     * @param password
     * @return
     */
    Observable<User> signUp(String email, String password);

    /**
     * SignIn 메소드
     *
     * @param email
     * @param password
     * @return
     */
    Observable<User> signIn(String email, String password);
}
