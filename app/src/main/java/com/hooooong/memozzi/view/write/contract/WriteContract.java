package com.hooooong.memozzi.view.write.contract;

import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.view.BasePresenter;

/**
 * Created by Android Hong on 2018-02-23.
 */

public interface WriteContract {

    interface iView {
        void goToMain();

        void goToDetail();
    }

    interface iPresenter extends BasePresenter<iView> {
        void createArticle(Article article);

        void modifyArticle(Article article);
    }
}
