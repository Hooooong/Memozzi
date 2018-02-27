package com.hooooong.memozzi.view.write.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.article.ArticleCategoryType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Android Hong on 2018-02-26.
 */

public class ArticleCategoryAdapter extends RecyclerView.Adapter<ArticleCategoryAdapter.CategoryItem> {

    private Context context;
    private OnCategoryClickListener listener;
    private List<ArticleCategoryType> categoryTypes;

    public ArticleCategoryAdapter(Context context) {
        categoryTypes = Arrays.asList(ArticleCategoryType.values());
        this.context = context;
        this.listener = (OnCategoryClickListener) context;
    }

    @Override
    public CategoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryItem(LayoutInflater.from(context).inflate(R.layout.item_article_category, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(CategoryItem holder, int position) {
        ArticleCategoryType type = categoryTypes.get(position);
        holder.setCategoryItem(type);
    }

    @Override
    public int getItemCount() {
        return categoryTypes.size();
    }

    class CategoryItem extends RecyclerView.ViewHolder {
        private TextView textCategory;
        private View viewCategoryColor;

        CategoryItem(View itemView, OnCategoryClickListener listener) {
            super(itemView);

            textCategory = itemView.findViewById(R.id.tv_category);
            viewCategoryColor = itemView.findViewById(R.id.view_category_color);

            itemView.setOnClickListener(v -> {
                listener.setOnCategoryClick(textCategory.getText().toString());
            });
        }

        void setCategoryItem(ArticleCategoryType type){
            textCategory.setText(type.name());
            viewCategoryColor.setBackgroundResource(type.colorValue());
        }
    }

    public interface OnCategoryClickListener {
        void setOnCategoryClick(String text);
    }
}
