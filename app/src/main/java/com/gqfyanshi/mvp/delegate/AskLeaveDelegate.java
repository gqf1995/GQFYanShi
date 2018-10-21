package com.gqfyanshi.mvp.delegate;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebensz.eink.api.PennableLayout;
import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;
import com.gqfyanshi.widget.SelectAttrLayout;
import com.gqfyanshi.widget.SelectPeopleLayout;
import com.gqfyanshi.widget.SelectTimeLayout;

public class AskLeaveDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ask_leave;
    }


    public static class ViewHolder {
        public View rootView;

        public SelectAttrLayout selectAttrLayout1;
        public SelectPeopleLayout selectPeopleLayout;
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
        public TextView tv_people;
        public LinearLayout lin2;
        public EditText et_input1;
        public LinearLayout lin1;
        public RecyclerView recycler_view;
        public LinearLayout lin_rcv;
        public LinearLayout lin_edit;
        public RoundButton tv_commit;
        public LinearLayout lin_nestedScrollView;
        public NestedScrollView nestedScrollView;
        public PennableLayout ink;
        public FrameLayout fl_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.selectAttrLayout1 = (SelectAttrLayout) rootView.findViewById(R.id.selectAttrLayout1);
            this.selectPeopleLayout = (SelectPeopleLayout) rootView.findViewById(R.id.selectPeopleLayout);
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
            this.tv_people = (TextView) rootView.findViewById(R.id.tv_people);
            this.lin2 = (LinearLayout) rootView.findViewById(R.id.lin2);
            this.et_input1 = (EditText) rootView.findViewById(R.id.et_input1);
            this.lin1 = (LinearLayout) rootView.findViewById(R.id.lin1);
            this.recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            this.lin_rcv = (LinearLayout) rootView.findViewById(R.id.lin_rcv);
            this.lin_edit = (LinearLayout) rootView.findViewById(R.id.lin_edit);
            this.tv_commit = (RoundButton) rootView.findViewById(R.id.tv_commit);
            this.lin_nestedScrollView = (LinearLayout) rootView.findViewById(R.id.lin_nestedScrollView);
            this.nestedScrollView = (NestedScrollView) rootView.findViewById(R.id.nestedScrollView);
            this.ink = (PennableLayout) rootView.findViewById(R.id.ink);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
        }

    }
}