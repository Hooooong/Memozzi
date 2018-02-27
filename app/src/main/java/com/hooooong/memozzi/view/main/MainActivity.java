package com.hooooong.memozzi.view.main;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.Const;
import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.view.detail.DetailActivity_;
import com.hooooong.memozzi.view.main.adapter.ArticlesAdapter;
import com.hooooong.memozzi.view.main.contract.MainContract;
import com.hooooong.memozzi.view.main.presenter.MainPresenter;
import com.hooooong.memozzi.view.write.WriteActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainContract.iView {

    private static final String TAG = "MainActivity";

    private Intent intent;
    private ArticlesAdapter articlesAdapter;

    @Bean(MainPresenter.class)
    MainContract.iPresenter presenter;

    @ViewById(R.id.layout_background_empty)
    ConstraintLayout emptyLayout;

    @ViewById(R.id.rv_article_list)
    RecyclerView recyclerView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @AfterInject
    void initObject() {
        presenter.attachView(this);

        articlesAdapter = new ArticlesAdapter(this);
        presenter.setArticlesAdapterModel(articlesAdapter);
        presenter.setArticlesAdapterView(articlesAdapter);
    }

    @AfterViews
    void bindAdapter() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(articlesAdapter);
        recyclerView.addItemDecoration(new ArticleItemDivider(2, 42, true));
    }

    @AfterViews
    void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_list);
        toolbar.setNavigationOnClickListener(view -> {
            // Navigation Click 했을 경우에는
        });
        // 타이틀 가ㅕ주기
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @AfterViews
    void loadArticles() {
        // View 가 끝난 후 데이터를 로드해야 한다.
        presenter.loadArticles();
    }

    @Override
    public void showData(boolean flag) {
        if (flag) {
            // true 일 경우에는
            // recyclerView 를 보여주고, background_empty 를 가린다.
            recyclerView.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.GONE);
        } else {
            //
            recyclerView.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goToDetail(Article article) {
        intent = new Intent(this, DetailActivity_.class);
        intent.putExtra(Const.INTENT_EXTRA_ARTICLE, article);
        startActivityForResult(intent, Const.REQ_DETAIL);
    }

    /*
    Floating Action Button 을 눌렀을 시 작성 화면으로 넘어가는 메소드
     */
    @Click(R.id.fab_article_add)
    void addToArticle() {
        intent = new Intent(this, WriteActivity_.class);
        startActivityForResult(intent, Const.REQ_WRITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Const.REQ_WRITE:
                if (resultCode == RESULT_OK) {
                    presenter.loadArticles();
                }
                break;
            case Const.REQ_DETAIL:
                if (resultCode == RESULT_OK) {
                    presenter.loadArticles();
                }
        }
    }
}
