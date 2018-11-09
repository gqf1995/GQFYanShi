package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.RelativeLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class AddSharerDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_sharer;
    }


    public static class ViewHolder {
        public View rootView;

        public RelativeLayout contentView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.contentView = (RelativeLayout) rootView.findViewById(R.id.contentView);
        }

    }
}