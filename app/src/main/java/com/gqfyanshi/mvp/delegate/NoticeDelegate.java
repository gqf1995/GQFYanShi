package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;
import com.gqfyanshi.widget.PageChangeView;

public class NoticeDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice;
    }


    public static class ViewHolder {
        public View rootView;
        public EditText et_attributes;
        public LinearLayout lin_attributes;
        public EditText et_time1;
        public LinearLayout lin_select_time1;
        public EditText et_time2;
        public LinearLayout lin_select_time2;
        public RoundButton tv_search;
        public PageChangeView pageChangeView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.et_attributes = (EditText) rootView.findViewById(R.id.et_attributes);
            this.lin_attributes = (LinearLayout) rootView.findViewById(R.id.lin_attributes);
            this.tv_search = (RoundButton) rootView.findViewById(R.id.tv_search);
            this.pageChangeView = (PageChangeView) rootView.findViewById(R.id.pageChangeView);
        }
    }
}