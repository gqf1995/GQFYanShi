package com.gqfyanshi.mvp.delegate;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class ReceivinOofficialDocumentsDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receivin_oofficial_documents;
    }


    public static class ViewHolder {
        public View rootView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

        }

    }
}