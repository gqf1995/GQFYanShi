package com.gqfyanshi.mvp.delegate;

import com.gqfyanshi.R;
import com.fivefivelike.mybaselibrary.base.BaseDelegate;

import android.view.View;

public class WelcomeDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }


    public static class ViewHolder {
        public View rootView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

        }

    }
}