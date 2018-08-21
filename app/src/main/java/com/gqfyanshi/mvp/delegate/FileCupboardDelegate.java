package com.gqfyanshi.mvp.delegate;

import com.gqfyanshi.R;
import com.fivefivelike.mybaselibrary.base.BaseDelegate;

import android.view.View;

public class FileCupboardDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_cupboard;
    }


    public static class ViewHolder {
        public View rootView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

        }

    }
}