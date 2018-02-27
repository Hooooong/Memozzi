package com.hooooong.memozzi.view.main.contract;

import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.view.BasePresenter;
import com.hooooong.memozzi.view.main.adapter.contract.ArticlesAdapterContract;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface MainContract {

    interface iView{
        /**
         * Data 가 있을 경우와 없을 경우를 보여주는 메소드
         * @param flag
         */
        void showData(boolean flag);

        void goToDetail(Article article);
    }

    interface iPresenter extends BasePresenter<iView>{
        void loadArticles();

        void setArticlesAdapterModel(ArticlesAdapterContract.iModel model);
        void setArticlesAdapterView(ArticlesAdapterContract.iView view);
    }
}
