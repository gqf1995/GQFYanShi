package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;
import com.gqfyanshi.mvp.activity.add.AddFileDelegate;
import com.gqfyanshi.widget.SelectAttrLayout;
import com.gqfyanshi.widget.SelectPeopleLayout;
import com.gqfyanshi.widget.SelectTimeLayout;

public class AddDocumentDelegate extends AddFileDelegate {
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
        public EditText et_attributes3;
        public LinearLayout lin_attributes3;
        public SelectAttrLayout selectAttrLayout;
        public LinearLayout lin_selectAttr;
        public LinearLayout lin2;
        public SelectAttrLayout selectAttrLayout2;
        public SelectTimeLayout selectTimeLayout;
        public LinearLayout lin3;
        public LinearLayout lin4;
        public TextView et_attributes2;
        public LinearLayout lin_attributes2;
        public LinearLayout lin_file;
        public EditText et_input;
        public RoundButton tv_send;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.et_attributes = (EditText) rootView.findViewById(R.id.et_attributes);
            this.lin_attributes = (LinearLayout) rootView.findViewById(R.id.lin_attributes);
            this.selectPeopleLayout1 = (SelectPeopleLayout) rootView.findViewById(R.id.selectPeopleLayout1);
            this.et_attributes3 = (EditText) rootView.findViewById(R.id.et_attributes3);
            this.lin_attributes3 = (LinearLayout) rootView.findViewById(R.id.lin_attributes3);
            this.selectAttrLayout = (SelectAttrLayout) rootView.findViewById(R.id.selectAttrLayout);
            this.lin_selectAttr = (LinearLayout) rootView.findViewById(R.id.lin_selectAttr);
            this.lin2 = (LinearLayout) rootView.findViewById(R.id.lin2);
            this.lin4 = (LinearLayout) rootView.findViewById(R.id.lin4);
            this.selectAttrLayout2 = (SelectAttrLayout) rootView.findViewById(R.id.selectAttrLayout2);
            this.selectTimeLayout = (SelectTimeLayout) rootView.findViewById(R.id.selectTimeLayout);
            this.lin3 = (LinearLayout) rootView.findViewById(R.id.lin3);
            this.et_attributes2 = (TextView) rootView.findViewById(R.id.et_attributes2);
            this.lin_attributes2 = (LinearLayout) rootView.findViewById(R.id.lin_attributes2);
            this.lin_file = (LinearLayout) rootView.findViewById(R.id.lin_file);
            this.et_input = (EditText) rootView.findViewById(R.id.et_input);
            this.tv_send = (RoundButton) rootView.findViewById(R.id.tv_send);
        }

    }
}