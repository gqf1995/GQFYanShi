package com.gqfyanshi.mvp.delegate;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ebensz.eink.api.PennableLayout;
import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;
import com.gqfyanshi.widget.SelectPeopleLayout;
import com.gqfyanshi.widget.SelectTimeLayout;

public class ApprovalDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_approval;
    }


    public static class ViewHolder {
        public View rootView;

        public EditText et_title;
        public EditText et_num;
        public SelectPeopleLayout selectPeopleLayout;
        public EditText et_jinji;
        public EditText et_miji;
        public EditText et_fanwei;
        public EditText et_danwei;
        public EditText et_nigao;
        public EditText et_hegao;
        public EditText et_hefa;
        public EditText et_chaosong;
        public SelectTimeLayout selectTimeLayout1;
        public EditText et_yin;
        public EditText et_niban;
        public EditText et_huiqian;
        public EditText et_zhuguan;
        public EditText et_shenhe;
        public EditText et_qianfa;
        public LinearLayout lin_edit;
        public LinearLayout lin_nestedScrollView;
        public NestedScrollView nestedScrollView;
        public PennableLayout ink;
        public FrameLayout fl_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;

            this.et_title = (EditText) rootView.findViewById(R.id.et_title);
            this.et_num = (EditText) rootView.findViewById(R.id.et_num);
            this.selectPeopleLayout = (SelectPeopleLayout) rootView.findViewById(R.id.selectPeopleLayout);
            this.et_jinji = (EditText) rootView.findViewById(R.id.et_jinji);
            this.et_miji = (EditText) rootView.findViewById(R.id.et_miji);
            this.et_fanwei = (EditText) rootView.findViewById(R.id.et_fanwei);
            this.et_danwei = (EditText) rootView.findViewById(R.id.et_danwei);
            this.et_nigao = (EditText) rootView.findViewById(R.id.et_nigao);
            this.et_hegao = (EditText) rootView.findViewById(R.id.et_hegao);
            this.et_hefa = (EditText) rootView.findViewById(R.id.et_hefa);
            this.et_chaosong = (EditText) rootView.findViewById(R.id.et_chaosong);
            this.selectTimeLayout1 = (SelectTimeLayout) rootView.findViewById(R.id.selectTimeLayout1);
            this.et_yin = (EditText) rootView.findViewById(R.id.et_yin);
            this.et_niban = (EditText) rootView.findViewById(R.id.et_niban);
            this.et_huiqian = (EditText) rootView.findViewById(R.id.et_huiqian);
            this.et_zhuguan = (EditText) rootView.findViewById(R.id.et_zhuguan);
            this.et_shenhe = (EditText) rootView.findViewById(R.id.et_shenhe);
            this.et_qianfa = (EditText) rootView.findViewById(R.id.et_qianfa);
            this.lin_edit = (LinearLayout) rootView.findViewById(R.id.lin_edit);
            this.lin_nestedScrollView = (LinearLayout) rootView.findViewById(R.id.lin_nestedScrollView);
            this.nestedScrollView = (NestedScrollView) rootView.findViewById(R.id.nestedScrollView);
            this.ink = (PennableLayout) rootView.findViewById(R.id.ink);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
        }

    }
}