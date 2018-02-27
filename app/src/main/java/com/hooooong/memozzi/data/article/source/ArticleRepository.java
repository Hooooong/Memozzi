package com.hooooong.memozzi.data.article.source;

import com.hooooong.memozzi.data.Result;
import com.hooooong.memozzi.data.article.Article;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Android Hong on 2018-02-22.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ArticleRepository implements ArticleSource{

    @Bean
    ArticleRemoteRepository repository;

    @Override
    public Observable<List<Article>> loadArticles() {
        return repository.loadArticles();
    }

    @Override
    public Observable<Result> createArticle(Article article) {
        return repository.createArticle(article);
    }

    @Override
    public Observable<Result> modifyArticle(Article article) {
        return repository.modifyArticle(article);
    }

    @Override
    public Observable<Result> deleteArticle(String boardId) {
        return repository.deleteArticle(boardId);
    }

}
