package com.hooooong.memozzi.data.article;

import com.hooooong.memozzi.R;

/**
 * Created by Android Hong on 2018-02-23.
 */

public enum ArticleCategoryType {

    먹방 {
        @Override
        public int colorValue() {
            return R.color.colorMukBang;
        }
    },
    일기 {
        @Override
        public int colorValue() {
            return R.color.colorDiary;
        }
    },
    쇼핑 {
        @Override
        public int colorValue() {
            return R.color.colorShopping;
        }
    },
    기타 {
        @Override
        public int colorValue() {
            return R.color.colorEtc;
        }
    };

    public abstract int colorValue();

}
