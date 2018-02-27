package com.hooooong.memozzi.view.signIn.contract;

import com.hooooong.memozzi.view.BasePresenter;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface SignInContract {

    interface iView{
        void showError(String error);

        void goToMain();
    }
    interface iPresenter extends BasePresenter<iView>{
        void setSignIn(String email, String password);
    }
}
