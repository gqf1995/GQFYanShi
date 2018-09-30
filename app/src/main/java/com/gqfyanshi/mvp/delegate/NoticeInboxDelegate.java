package com.gqfyanshi.mvp.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;
import com.gqfyanshi.widget.PageChangeView;

public class NoticeInboxDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_inbox;
    }


    public static class ViewHolder {
        public View rootView;

        public EditText et_attributes;
        public LinearLayout lin_attributes;
        public RoundButton tv_search;
        public TextView tv_add;
        public RecyclerView recycler_view;
        public TextView tv_nodata;
        public PageChangeView pageChangeView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.et_attributes = (EditText) rootView.findViewById(R.id.et_attributes);
            this.lin_attributes = (LinearLayout) rootView.findViewById(R.id.lin_attributes);
            this.tv_search = (RoundButton) rootView.findViewById(R.id.tv_search);
            this.tv_add = (TextView) rootView.findViewById(R.id.tv_add);
            this.recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            this.tv_nodata = (TextView) rootView.findViewById(R.id.tv_nodata);
            this.pageChangeView = (PageChangeView) rootView.findViewById(R.id.pageChangeView);
        }

    }
}