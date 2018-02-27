package com.hooooong.memozzi.data.article.source;

import com.hooooong.memozzi.data.Result;
import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.data.user.source.UserRepository;
import com.hooooong.memozzi.service.ServiceGenerator;
import com.hooooong.memozzi.service.api.ArticleAPI;

import org.androidannotations.annotations.EBean;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Android Hong on 2018-02-22.
 */

@EBean(scope = EBean.Scope.Singleton)
public class ArticleRemoteRepository implements ArticleSource {

    private UserRepository userRepository;
    private ArticleAPI articleAPI;

    public ArticleRemoteRepository() {
        articleAPI = ServiceGenerator.create(ArticleAPI.class);
        userRepository = UserRepository.getInstance();
    }

    @Override
    public Observable<List<Article>> loadArticles() {
        return articleAPI.loadArticles(userRepository.getUser().getUserId());
    }

    @Override
    public Observable<Result> createArticle(Article article) {

        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        //백엔드쪽 데이터 파라미터 수정에 의해 password1, password2가 password 하나로 합쳐짐 12/5
        requestBodyMap.put("title", toRequestBody(article.getTitle()));
        requestBodyMap.put("category", toRequestBody(article.getCatagory()));
        requestBodyMap.put("description", toRequestBody(article.getDescription()));
        requestBodyMap.put("setting_date", toRequestBody(article.getSettingDate()));

        if(article.getImageUrl() != null){
            File file = new File(article.getImageUrl());
            // create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            requestBodyMap.put("image_url\"; filename=\""+file.getName(), requestFile);
        }else{
            RequestBody requestFile = RequestBody.create(MediaType.parse(""),"");
            requestBodyMap.put("image_url\"; filename=\"", requestFile);
        }
        return articleAPI.createArticle(userRepository.getUser().getUserId(), requestBodyMap);
    }

    @Override
    public Observable<Result> modifyArticle(Article article) {

        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        //백엔드쪽 데이터 파라미터 수정에 의해 password1, password2가 password 하나로 합쳐짐 12/5
        requestBodyMap.put("title", toRequestBody(article.getTitle()));
        requestBodyMap.put("category", toRequestBody(article.getCatagory()));
        requestBodyMap.put("description", toRequestBody(article.getDescription()));
        requestBodyMap.put("setting_date", toRequestBody(article.getSettingDate()));

        if(article.getImageUrl() != null){
            File file = new File(article.getImageUrl());
            // create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            requestBodyMap.put("image_url\"; filename=\""+file.getName(), requestFile);
        }else{
            RequestBody requestFile = RequestBody.create(MediaType.parse(""),"");
            requestBodyMap.put("image_url\"; filename=\"", requestFile);
        }
        return articleAPI.modifyArticle(article.getBoardId(), requestBodyMap);
    }

    @Override
    public Observable<Result> deleteArticle(String boardId) {
        return articleAPI.deleteArticle(boardId);
    }


    private RequestBody toRequestBody(String json) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), json);
        return body;
    }
}
