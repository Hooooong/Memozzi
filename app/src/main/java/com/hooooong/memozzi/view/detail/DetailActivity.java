package com.hooooong.memozzi.view.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.Const;
import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.data.article.ArticleCategoryType;
import com.hooooong.memozzi.util.DialogUtil;
import com.hooooong.memozzi.view.detail.contract.DetailContract;
import com.hooooong.memozzi.view.detail.presenter.DetailPresenter;
import com.hooooong.memozzi.view.write.WriteActivity_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_detail)
@OptionsMenu(R.menu.menu_detail_options)
public class DetailActivity extends AppCompatActivity implements DetailContract.iView {

    private static final String TAG = "DetailActivity";

    private Intent intent;
    private Article article;
    private boolean isModify;

    @Bean(DetailPresenter.class)
    DetailContract.iPresenter presenter;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.tv_title)
    TextView textTitle;
    @ViewById(R.id.tv_date)
    TextView textDate;
    @ViewById(R.id.tv_description)
    TextView textDescription;
    @ViewById(R.id.tv_category)
    TextView textCategory;
    @ViewById(R.id.relativelayout_image)
    RelativeLayout imageLayout;
    @ViewById(R.id.view_category_color)
    View viewCategoryColor;

    @AfterInject
    void initObject() {
        presenter.attachView(this);
    }

    @AfterViews
    void initToolbar() {
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(view -> {
            // Navigation Click 했을 경우에는
            onBackPressed();
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @OptionsItem({R.id.menu_modify, R.id.menu_delete})
    void menuSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_modify) {
            // 수정을 눌럿을 시
            intent = new Intent(this, WriteActivity_.class);
            intent.putExtra(Const.INTENT_EXTRA_ARTICLE, article);
            startActivityForResult(intent, Const.REQ_MODIFY);
        } else {
            // 삭제를 눌렀을 시
            DialogUtil.showBasicDialog(this, "삭제", "글을 삭제하시겠습니까?", (dialog, which) -> {
                // 확인을 누를 경우
                presenter.deleteArticle(article.getBoardId());
            }).show();
        }
    }

    @AfterViews
    void initViews() {
        intent = getIntent();
        this.article = (Article) intent.getSerializableExtra(Const.INTENT_EXTRA_ARTICLE);
        setArticleData();

    }

    private void setArticleData() {
        if (article.getImageUrl() == null) {
            imageLayout.setVisibility(View.GONE);
        }
        textTitle.setText(article.getTitle());
        textDate.setText(article.getSettingDate());
        textDescription.setText(article.getDescription());
        textCategory.setText(article.getCatagory());
        viewCategoryColor.setBackgroundResource(ArticleCategoryType.valueOf(article.getCatagory()).colorValue());
    }

    @Override
    public void goToMain() {
        Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "에러 발생. 다시 시도.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Const.REQ_MODIFY:
                Log.e(TAG, "onActivityResult: 수정");
                if (resultCode == RESULT_OK) {
                    this.isModify = true;
                    this.article = (Article) data.getSerializableExtra(Const.INTENT_EXTRA_ARTICLE);
                    setArticleData();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(isModify){
            // 수정일 경우에는
            setResult(RESULT_OK);
            finish();
        }else{
            finish();
        }
    }
}
