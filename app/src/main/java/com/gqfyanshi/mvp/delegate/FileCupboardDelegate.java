package com.gqfyanshi.mvp.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

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

        public ImageView iv_new;
        public ImageView iv_file;
        public EditText et_search;
        public ImageView iv_add_people;
        public RecyclerView recycler_view;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.iv_new = (ImageView) rootView.findViewById(R.id.iv_new);
            this.iv_file = (ImageView) rootView.findViewById(R.id.iv_file);
            this.et_search = (EditText) rootView.findViewById(R.id.et_search);
            this.iv_add_people = (ImageView) rootView.findViewById(R.id.iv_add_people);
            this.recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        }

    }
}