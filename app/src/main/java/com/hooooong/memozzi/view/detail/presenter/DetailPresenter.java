package com.hooooong.memozzi.view.detail.presenter;

import android.util.Log;

import com.hooooong.memozzi.data.article.source.ArticleRepository;
import com.hooooong.memozzi.data.article.source.ArticleSource;
import com.hooooong.memozzi.view.detail.contract.DetailContract;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Hong on 2018-02-23.
 */

@EBean
public class DetailPresenter implements DetailContract.iPresenter {

    private static final String TAG = "DetailPresenter";

    private DetailContract.iView view;

    @Bean(ArticleRepository.class)
    ArticleSource repository;

    @Override
    public void attachView(DetailContract.iView view) {
        this.view = view;
    }

    @Override
    public void deleteArticle(String boardId) {
        repository.deleteArticle(boardId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "deleteArticle: Success");
                            view.goToMain();
                        }, throwable -> {
                            Log.e(TAG, "deleteArticle: " + throwable.getMessage());
                            view.showError();
                        }
                );
    }
}
