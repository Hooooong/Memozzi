package com.hooooong.memozzi.view.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.article.Article;
import com.hooooong.memozzi.data.article.ArticleCategoryType;
import com.hooooong.memozzi.view.OnArticleClickListener;
import com.hooooong.memozzi.view.main.adapter.contract.ArticlesAdapterContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2018-02-23.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> implements ArticlesAdapterContract.iView, ArticlesAdapterContract.iModel {

    private Context context;

    private List<Article> articleList;
    private OnArticleClickListener listener;

    public ArticlesAdapter(Context context) {
        articleList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.setPosition(position);
        holder.setArticleData(article);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @Override
    public void clear() {
        this.articleList.clear();
    }

    @Override
    public void addItems(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public void notifyAdapter() {
        this.notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnArticleClickListener listener) {
        this.listener = listener;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        private int position;

        private Article article;

        private TextView tvTitle;
        private View viewCategoryColor;
        private TextView tvCategory;
        private TextView tvDate;
        private TextView tvDescription;
        private LinearLayout linearImageContainer;


        public ArticleViewHolder(View itemView, OnArticleClickListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            viewCategoryColor = itemView.findViewById(R.id.view_category_color);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            linearImageContainer = itemView.findViewById(R.id.linear_image_container);

            itemView.setOnClickListener(v -> listener.onArticleClick(article));
        }

        public void setArticleData(Article articleData) {
            this.article = articleData;
            tvTitle.setText(articleData.getTitle());
            viewCategoryColor.setBackgroundResource(getBackgroundColor(articleData.getCatagory()));
            tvCategory.setText(articleData.getCatagory());
            tvDate.setText(articleData.getSettingDate().substring(0, 10));
            tvDescription.setText(articleData.getDescription());
        }

        public int getBackgroundColor(String category) {
            ArticleCategoryType type = ArticleCategoryType.valueOf(category);
            return type.colorValue();
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
