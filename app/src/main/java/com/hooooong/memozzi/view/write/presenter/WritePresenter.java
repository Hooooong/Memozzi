package com.hooooong.memozzi.view.write.presenter;

import android.util.Log;

import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.data.article.source.ArticleRepository;
import com.hooooong.memozzi.data.article.source.ArticleSource;
import com.hooooong.memozzi.view.write.contract.WriteContract;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Hong on 2018-02-23.
 */
@EBean
public class WritePresenter implements WriteContract.iPresenter {

    private static final String TAG = "WritePresenter";

    private WriteContract.iView view;

    @Bean(ArticleRepository.class)
    ArticleSource repository;

    @Override
    public void attachView(WriteContract.iView view) {
        this.view = view;
    }

    @Override
    public void createArticle(Article article) {
        repository.createArticle(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.e(TAG, "createArticle: " + data.toString());
                    view.goToMain();
                }, throwable -> {
                    Log.e(TAG, "createArticle: " + throwable.getMessage());
                });
    }

    @Override
    public void modifyArticle(Article article) {
        repository.modifyArticle(article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.e(TAG, "modifyArticle: " + data.toString());
                    view.goToDetail();
                }, throwable -> {
                    Log.e(TAG, "modifyArticle: " + throwable.getMessage());
                });
    }
}
