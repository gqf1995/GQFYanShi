package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;
import com.gqfyanshi.widget.SelectTimeLayout;

public class AskForLeaveDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ask_for_leave;
    }


    public static class ViewHolder {
        public View rootView;

        public EditText et_attributes;
        public LinearLayout lin_attributes;
        public EditText et_attributes2;
        public LinearLayout lin_attributes2;
        public EditText et_attributes3;
        public LinearLayout lin_attributes3;
        public SelectTimeLayout selectTimeLayout1;
        public SelectTimeLayout selectTimeLayout2;
        public EditText et_attributes4;
        public LinearLayout lin_attributes4;
        public EditText et_attributes5;
        public LinearLayout lin_attributes5;
        public EditText et_attributes6;
        public LinearLayout lin_attributes6;
        public EditText et_attributes7;
        public LinearLayout lin_attributes7;
        public EditText et_attributes8;
        public LinearLayout lin_attributes8;
        public EditText et_attributes9;
        public LinearLayout lin_attributes9;
        public EditText et_input1;
        public LinearLayout lin1;
        public EditText et_input2;
        public LinearLayout lin2;
        public EditText et_input3;
        public LinearLayout lin3;
        public EditText et_input4;
        public LinearLayout lin4;
        public EditText et_input5;
        public LinearLayout lin5;
        public EditText et_input6;
        public LinearLayout lin6;
        public EditText et_input7;
        public LinearLayout lin7;
        public EditText et_input8;
        public LinearLayout lin8;
        public EditText et_input9;
        public LinearLayout lin9;
        public RoundButton tv_commit;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.et_attributes = (EditText) rootView.findViewById(R.id.et_attributes);
            this.lin_attributes = (LinearLayout) rootView.findViewById(R.id.lin_attributes);
            this.et_attributes2 = (EditText) rootView.findViewById(R.id.et_attributes2);
            this.lin_attributes2 = (LinearLayout) rootView.findViewById(R.id.lin_attributes2);
            this.et_attributes3 = (EditText) rootView.findViewById(R.id.et_attributes3);
            this.lin_attributes3 = (LinearLayout) rootView.findViewById(R.id.lin_attributes3);
            this.selectTimeLayout1 = (SelectTimeLayout) rootView.findViewById(R.id.selectTimeLayout1);
            this.selectTimeLayout2 = (SelectTimeLayout) rootView.findViewById(R.id.selectTimeLayout2);
            this.et_attributes4 = (EditText) rootView.findViewById(R.id.et_attributes4);
            this.lin_attributes4 = (LinearLayout) rootView.findViewById(R.id.lin_attributes4);
            this.et_attributes5 = (EditText) rootView.findViewById(R.id.et_attributes5);
            this.lin_attributes5 = (LinearLayout) rootView.findViewById(R.id.lin_attributes5);
            this.et_attributes6 = (EditText) rootView.findViewById(R.id.et_attributes6);
            this.lin_attributes6 = (LinearLayout) rootView.findViewById(R.id.lin_attributes6);
            this.et_attributes7 = (EditText) rootView.findViewById(R.id.et_attributes7);
            this.lin_attributes7 = (LinearLayout) rootView.findViewById(R.id.lin_attributes7);
            this.et_attributes8 = (EditText) rootView.findViewById(R.id.et_attributes8);
            this.lin_attributes8 = (LinearLayout) rootView.findViewById(R.id.lin_attributes8);
            this.et_attributes9 = (EditText) rootView.findViewById(R.id.et_attributes9);
            this.lin_attributes9 = (LinearLayout) rootView.findViewById(R.id.lin_attributes9);
            this.et_input1 = (EditText) rootView.findViewById(R.id.et_input1);
            this.lin1 = (LinearLayout) rootView.findViewById(R.id.lin1);
            this.et_input2 = (EditText) rootView.findViewById(R.id.et_input2);
            this.lin2 = (LinearLayout) rootView.findViewById(R.id.lin2);
            this.et_input3 = (EditText) rootView.findViewById(R.id.et_input3);
            this.lin3 = (LinearLayout) rootView.findViewById(R.id.lin3);
            this.et_input4 = (EditText) rootView.findViewById(R.id.et_input4);
            this.lin4 = (LinearLayout) rootView.findViewById(R.id.lin4);
            this.et_input5 = (EditText) rootView.findViewById(R.id.et_input5);
            this.lin5 = (LinearLayout) rootView.findViewById(R.id.lin5);
            this.et_input6 = (EditText) rootView.findViewById(R.id.et_input6);
            this.lin6 = (LinearLayout) rootView.findViewById(R.id.lin6);
            this.et_input7 = (EditText) rootView.findViewById(R.id.et_input7);
            this.lin7 = (LinearLayout) rootView.findViewById(R.id.lin7);
            this.et_input8 = (EditText) rootView.findViewById(R.id.et_input8);
            this.lin8 = (LinearLayout) rootView.findViewById(R.id.lin8);
            this.et_input9 = (EditText) rootView.findViewById(R.id.et_input9);
            this.lin9 = (LinearLayout) rootView.findViewById(R.id.lin9);
            this.tv_commit = (RoundButton) rootView.findViewById(R.id.tv_commit);
        }

    }
}