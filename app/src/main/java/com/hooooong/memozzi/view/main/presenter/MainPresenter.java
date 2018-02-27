package com.hooooong.memozzi.view.main.presenter;

import android.util.Log;

import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.data.article.source.ArticleRepository;
import com.hooooong.memozzi.data.article.source.ArticleSource;
import com.hooooong.memozzi.view.OnArticleClickListener;
import com.hooooong.memozzi.view.main.adapter.contract.ArticlesAdapterContract;
import com.hooooong.memozzi.view.main.contract.MainContract;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import static com.hooooong.memozzi.util.JsonUtil.getErrorMessage;

/**
 * Created by Android Hong on 2018-02-22.
 */
@EBean
public class MainPresenter implements MainContract.iPresenter, OnArticleClickListener {

    private static final String TAG = "MainPresenter";

    private MainContract.iView view;
    private ArticlesAdapterContract.iView adapterView;
    private ArticlesAdapterContract.iModel adapterModel;

    @Bean(ArticleRepository.class)
    ArticleSource repository;

    @Override
    public void attachView(MainContract.iView view) {
        this.view = view;
    }

    @Override
    public void setArticlesAdapterModel(ArticlesAdapterContract.iModel model) {
        this.adapterModel = model;
    }

    @Override
    public void setArticlesAdapterView(ArticlesAdapterContract.iView view) {
        this.adapterView = view;
        this.adapterView.setOnItemClickListener(this);
    }

    @Override
    public void loadArticles() {
        repository.loadArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (data.size() != 0) {
                        Log.e(TAG, "loadArticles: size = " + data.size());

                        adapterModel.clear();
                        adapterModel.addItems(data);
                        adapterView.notifyAdapter();

                        view.showData(true);
                    } else {
                        Log.e(TAG, "loadArticles: 데이터 없음");
                        view.showData(false);
                    }
                }, throwable -> {
                    Log.e(TAG, "loadArticles: Error");

                    if (throwable instanceof HttpException) {
                        ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                        //view.showError(getErrorMessage(responseBody));
                        Log.e(TAG, "loadArticles: " + getErrorMessage(responseBody));
                    } else {
                        Log.e(TAG, "setSignUp(Throwable): " + throwable.getMessage());
                    }
                });
    }

    @Override
    public void onArticleClick(Article article) {
        view.goToDetail(article);
    }
}
