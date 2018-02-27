package com.hooooong.memozzi.service.api;

import com.hooooong.memozzi.data.Result;
import com.hooooong.memozzi.data.article.Article;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * Created by Android Hong on 2018-02-22.
 */

public interface ArticleAPI {

    @GET("/article/{user_id}")
    Observable<List<Article>> loadArticles(@Path("user_id") String userId);

    @Multipart
    @POST("/article/{user_id}")
    Observable<Result> createArticle(@Path("user_id") String userId, @PartMap Map<String, RequestBody> articleMap);

    @DELETE("/article/{article_id}")
    Observable<Result> deleteArticle(@Path("article_id")String boardId);

    @Multipart
    @PUT("/article/{article_id}")
    Observable<Result> modifyArticle(@Path("article_id")String boardId, @PartMap Map<String, RequestBody> requestBodyMap);
}
