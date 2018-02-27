package com.hooooong.memozzi.view.signUp.contract;

import com.hooooong.memozzi.view.BasePresenter;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface SignUpContract {

    interface iView{
        void showProgress();
        void hideProgress();

        void showError(String error);

        void goToMain();
    }

    interface iPresenter extends BasePresenter<iView>{
        void setSignUp(String email, String password);
    }
}
