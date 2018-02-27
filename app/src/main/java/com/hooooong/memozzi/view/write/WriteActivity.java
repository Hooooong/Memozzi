package com.hooooong.memozzi.view.write;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.Const;
import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.util.DateUtil;
import com.hooooong.memozzi.util.DialogUtil;
import com.hooooong.memozzi.view.Gallery.GalleryActivity_;
import com.hooooong.memozzi.view.write.adapter.ArticleCategoryAdapter;
import com.hooooong.memozzi.view.write.contract.WriteContract;
import com.hooooong.memozzi.view.write.presenter.WritePresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.Locale;

@EActivity(R.layout.activity_write)
@OptionsMenu(R.menu.menu_write_options)
public class WriteActivity extends AppCompatActivity implements WriteContract.iView, ArticleCategoryAdapter.OnCategoryClickListener {

    private static final String TAG = "WriteActivity";

    private Intent intent;
    private Article article;
    private boolean isModify;
    private boolean isCategorySelected;

    @Bean(WritePresenter.class)
    WriteContract.iPresenter presenter;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.tv_date)
    TextView textDate;
    @ViewById(R.id.tv_category)
    TextView textCategory;
    @ViewById(R.id.et_title)
    EditText editTitle;
    @ViewById(R.id.relativelayout_image)
    RelativeLayout relativelayoutImage;
    @ViewById(R.id.tv_image)
    TextView textImage;
    @ViewById(R.id.et_description)
    EditText editDescription;
    @ViewById(R.id.ib_category)
    ImageButton imageButtonCategory;

    @ViewById(R.id.rv_article_category)
    RecyclerView recyclerCategory;

    private ArticleCategoryAdapter adapter;

    @AfterInject
    void initObject() {
        presenter.attachView(this);
        adapter = new ArticleCategoryAdapter(this);
    }

    @AfterViews
    void initToolbar() {
        setSupportActionBar(toolbar);
        // Toolbar Color Test
        toolbar.setSubtitleTextColor(Color.BLACK);
        toolbar.setNavigationOnClickListener(view -> {
            // Navigation Click 했을 경우에는
            onBackPressed();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // No Title
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @AfterViews
    void initView() {
        intent = getIntent();
        article = (Article) intent.getSerializableExtra(Const.INTENT_EXTRA_ARTICLE);
        if (article != null) {
            // 수정인 경우

            Log.e(TAG, "initView: 수정");
            isModify = true;
            textDate.setText(article.getSettingDate());
            textCategory.setTextColor(Color.parseColor("#6c6c6c"));

            editTitle.setText(article.getTitle());
            textCategory.setText(article.getCatagory());
            editDescription.setText(article.getDescription());
        } else {
            // 새 글 작성일 경우
            Log.e(TAG, "initView: 작성");
            article = new Article();
            textDate.setText(DateUtil.getCurrentDateTime());
        }
    }

    @AfterViews
    void initAdapter() {
        recyclerCategory.setLayoutManager(new LinearLayoutManager(this));
        recyclerCategory.setAdapter(adapter);
    }

    @OptionsItem(R.id.menu_save)
    void saveSelected() {
        // 유효성 추가
        article.setSettingDate(textDate.getText().toString());
        article.setTitle(editTitle.getText().toString());
        article.setCatagory(textCategory.getText().toString());
        article.setDescription(editDescription.getText().toString());

        if (isModify) {
            // 수정인 경우
            presenter.modifyArticle(article);
        } else {
            // 새 글인 경우
            presenter.createArticle(article);
        }
    }

    @Click(R.id.tv_date)
    void setArticleDate() {
        DialogUtil.showDatePickerDialog(this, (v, year, month, dayOfMonth) -> {
            String selectedDate;
            if ((month + 1) < 10 && dayOfMonth < 10) {
                selectedDate = String.format(Locale.KOREA, "%d-0%d-0%d", year, month + 1, dayOfMonth);
            } else if ((month + 1) < 10 && dayOfMonth >= 10) {
                selectedDate = String.format(Locale.KOREA, "%d-0%d-%d", year, month + 1, dayOfMonth);
            } else if ((month + 1) >= 10 && dayOfMonth < 10) {
                selectedDate = String.format(Locale.KOREA, "%d-%d-0%d", year, month + 1, dayOfMonth);
            } else {
                selectedDate = String.format(Locale.KOREA, "%d-%d-%d", year, month + 1, dayOfMonth);
            }

            DialogUtil.showTimePickerDialog(this, (view, hourOfDay, minute) -> {
                textDate.setText(DateUtil.setSelectedDateTime(selectedDate + " " + hourOfDay + ":" + minute));
            }, textDate.getText().toString()).show();
        }, textDate.getText().toString()).show();
    }

    @Click(R.id.relativelayout_category)
    void selectCategoryButton() {
        if (isCategorySelected) {
            // 눌려있으면
            isCategorySelected = false;
            recyclerCategory.setVisibility(View.GONE);
            imageButtonCategory.setImageResource(R.drawable.icon_down);
        } else {
            // 눌려있지 않으면
            isCategorySelected = true;
            recyclerCategory.setVisibility(View.VISIBLE);
            imageButtonCategory.setImageResource(R.drawable.icon_up);
        }
    }

    @Click(R.id.ib_image)
    void selectImage(){
        intent = new Intent(this, GalleryActivity_.class);
        startActivityForResult(intent, Const.REQ_GALLERY);
    }

    @Override
    public void goToMain() {
        Toast.makeText(this, "글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void goToDetail() {
        Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, new Intent().putExtra(Const.INTENT_EXTRA_ARTICLE, article));
        finish();
    }

    @Override
    public void setOnCategoryClick(String text) {
        textCategory.setText(text);
        textCategory.setTextColor(Color.parseColor("#6c6c6c"));

        isCategorySelected = false;
        recyclerCategory.setVisibility(View.GONE);
        imageButtonCategory.setImageResource(R.drawable.icon_down);
    }

    @Override
    public void onBackPressed() {
        DialogUtil.showBasicDialog(this, "취소", "작성중인 내용은 저장이 안됩니다.\n취소하시겠습니까?", (dialog, which) -> {
            finish();
        }).show();
    }
}
