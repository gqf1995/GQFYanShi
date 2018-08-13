package com.gqfyanshi.mvp.delegate;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
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

        public EditText et_num;
        public EditText et_name;
        public RoundButton tv_search;
        public TextView tv_title1;
        public LinearLayout lin_title1;
        public RecyclerView recycler_view1;
        public TextView tv_title2;
        public LinearLayout lin_title2;
        public RecyclerView recycler_view2;
        public TextView tv_title3;
        public LinearLayout lin_title3;
        public RecyclerView recycler_view3;
        public TextView tv_title4;
        public LinearLayout lin_title4;
        public RecyclerView recycler_view4;
        public FrameLayout fl_left;
        public DrawerLayout main_drawer_layout;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.et_num = (EditText) rootView.findViewById(R.id.et_num);
            this.et_name = (EditText) rootView.findViewById(R.id.et_name);
            this.tv_search = (RoundButton) rootView.findViewById(R.id.tv_search);
            this.tv_title1 = (TextView) rootView.findViewById(R.id.tv_title1);
            this.lin_title1 = (LinearLayout) rootView.findViewById(R.id.lin_title1);
            this.recycler_view1 = (RecyclerView) rootView.findViewById(R.id.recycler_view1);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
            this.lin_title2 = (LinearLayout) rootView.findViewById(R.id.lin_title2);
            this.recycler_view2 = (RecyclerView) rootView.findViewById(R.id.recycler_view2);
            this.tv_title3 = (TextView) rootView.findViewById(R.id.tv_title3);
            this.lin_title3 = (LinearLayout) rootView.findViewById(R.id.lin_title3);
            this.recycler_view3 = (RecyclerView) rootView.findViewById(R.id.recycler_view3);
            this.tv_title4 = (TextView) rootView.findViewById(R.id.tv_title4);
            this.lin_title4 = (LinearLayout) rootView.findViewById(R.id.lin_title4);
            this.recycler_view4 = (RecyclerView) rootView.findViewById(R.id.recycler_view4);
            this.fl_left = (FrameLayout) rootView.findViewById(R.id.fl_left);
            this.main_drawer_layout = (DrawerLayout) rootView.findViewById(R.id.main_drawer_layout);
        }

    }
}