package com.hooooong.memozzi.data.article.source;

import com.hooooong.memozzi.data.Result;
import com.hooooong.memozzi.data.article.Article;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface ArticleSource {

    /**
     * Articles 들을 Load 하는 메소드
     *
     * @return
     */
    Observable<List<Article>> loadArticles();

    /**
     * Article 을 Upload 하는 메소드
     *
     * @param article
     * @return
     */
    Observable<Result> createArticle(Article article);

    /**
     * Article 을 Modify 하는 메소드
     * @param article
     * @return
     */
    Observable<Result> modifyArticle(Article article);

    /**
     * Article 을 Delete 하는 메소드
     * @param boardId
     * @return
     */
    Observable<Result> deleteArticle(String boardId);
}
