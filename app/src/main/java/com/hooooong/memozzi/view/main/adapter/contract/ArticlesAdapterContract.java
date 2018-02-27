package com.hooooong.memozzi.view.main.adapter.contract;

import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.view.OnArticleClickListener;

import java.util.List;

/**
 * Created by Android Hong on 2018-02-23.
 */

public interface ArticlesAdapterContract {

    interface iModel{
        void clear();
        void addItems(List<Article> articleList);
    }

    interface iView{
        void notifyAdapter();
        void setOnItemClickListener(OnArticleClickListener listener);
    }
}
