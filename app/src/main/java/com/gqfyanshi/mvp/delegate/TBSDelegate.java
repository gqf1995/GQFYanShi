package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class TBSDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tbs;
    }


    public static class ViewHolder {
        public View rootView;

        public LinearLayout lin_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.lin_root = (LinearLayout) rootView.findViewById(R.id.lin_root);
        }

    }
}