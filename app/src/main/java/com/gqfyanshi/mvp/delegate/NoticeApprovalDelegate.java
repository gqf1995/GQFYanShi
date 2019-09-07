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
import com.gqfyanshi.widget.SelectTimeLayout;

public class NoticeApprovalDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notice_approval;
    }


    public static class ViewHolder {
        public View rootView;

        public EditText et_attributes;
        public LinearLayout lin_attributes;
        public EditText et_attributes2;
        public LinearLayout lin_attributes2;
        public SelectTimeLayout selectTimeLayout1;
        public SelectTimeLayout selectTimeLayout2;
        public RoundButton tv_search;
        public RecyclerView recycler_view;
        public TextView tv_nodata;
        public TextView tv_status;
        public PageChangeView pageChangeView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.et_attributes = (EditText) rootView.findViewById(R.id.et_attributes);
            this.lin_attributes = (LinearLayout) rootView.findViewById(R.id.lin_attributes);
            this.et_attributes2 = (EditText) rootView.findViewById(R.id.et_attributes2);
            this.lin_attributes2 = (LinearLayout) rootView.findViewById(R.id.lin_attributes2);
            this.selectTimeLayout1 = (SelectTimeLayout) rootView.findViewById(R.id.selectTimeLayout1);
            this.selectTimeLayout2 = (SelectTimeLayout) rootView.findViewById(R.id.selectTimeLayout2);
            this.tv_search = (RoundButton) rootView.findViewById(R.id.tv_search);
            this.recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            this.tv_nodata = (TextView) rootView.findViewById(R.id.tv_nodata);
            this.tv_status = (TextView) rootView.findViewById(R.id.tv_status);
            this.pageChangeView = (PageChangeView) rootView.findViewById(R.id.pageChangeView);
        }

    }
}