package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class DocumentInfoDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_document_info;
    }


    public static class ViewHolder {
        public View rootView;

        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_file;
        public LinearLayout lin_file;
        public TextView tv_content;
        public LinearLayout lin_content;
        public LinearLayout lin_edit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_file = (TextView) rootView.findViewById(R.id.tv_file);
            this.lin_file = (LinearLayout) rootView.findViewById(R.id.lin_file);
            this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
            this.lin_content = (LinearLayout) rootView.findViewById(R.id.lin_content);
            this.lin_edit = (LinearLayout) rootView.findViewById(R.id.lin_edit);
        }

    }
}