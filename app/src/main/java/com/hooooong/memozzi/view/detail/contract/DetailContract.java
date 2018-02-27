package com.hooooong.memozzi.view.detail.contract;

import com.hooooong.memozzi.view.BasePresenter;

/**
 * Created by Android Hong on 2018-02-23.
 */

public interface DetailContract {

    interface iView{
        void goToMain();

        void showError();
    }

    interface iPresenter extends BasePresenter<iView>{

        void deleteArticle(String boardId);
    }
}
