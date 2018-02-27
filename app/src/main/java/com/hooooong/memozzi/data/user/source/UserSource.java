package com.hooooong.memozzi.data.user.source;


import com.hooooong.memozzi.data.user.User;

/**
 * Created by Android Hong on 2017-12-13.
 */

public interface UserSource {

    User getUser();

    void setUser(User user);

    void clearUser();

    boolean isExistUser();
}
