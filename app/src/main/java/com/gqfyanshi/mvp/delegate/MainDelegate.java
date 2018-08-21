package com.gqfyanshi.mvp.delegate;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class MainDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    public static class ViewHolder {
        public View rootView;
        public FrameLayout fl_root;
        public FrameLayout fl_left;
        public DrawerLayout main_drawer_layout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
            this.fl_left = (FrameLayout) rootView.findViewById(R.id.fl_left);
            this.main_drawer_layout = (DrawerLayout) rootView.findViewById(R.id.main_drawer_layout);
        }

    }
}