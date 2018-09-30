package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;
import com.gqfyanshi.widget.SelectPeopleLayout;

public class AddDocumentDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_document;
    }


    public static class ViewHolder {
        public View rootView;
        public EditText et_attributes;
        public LinearLayout lin_attributes;
        public SelectPeopleLayout selectPeopleLayout1;
        public TextView et_attributes2;
        public LinearLayout lin_attributes2;
        public EditText et_input;
        public RoundButton tv_send;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.et_attributes = (EditText) rootView.findViewById(R.id.et_attributes);
            this.lin_attributes = (LinearLayout) rootView.findViewById(R.id.lin_attributes);
            this.selectPeopleLayout1 = (SelectPeopleLayout) rootView.findViewById(R.id.selectPeopleLayout1);
            this.et_attributes2 = (TextView) rootView.findViewById(R.id.et_attributes2);
            this.lin_attributes2 = (LinearLayout) rootView.findViewById(R.id.lin_attributes2);
            this.et_input = (EditText) rootView.findViewById(R.id.et_input);
            this.tv_send = (RoundButton) rootView.findViewById(R.id.tv_send);
        }

    }
}