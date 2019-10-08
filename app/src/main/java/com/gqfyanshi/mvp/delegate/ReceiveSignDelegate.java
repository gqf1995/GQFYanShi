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
import com.gqfyanshi.R;
import com.gqfyanshi.widget.SelectPeopleLayout;

public class ReceiveSignDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_sign;
    }


    public static class ViewHolder {
        public View rootView;
        public EditText et_keshi;
        public EditText et_dianhua;
        public EditText et_shijian;
        public EditText et_bianhao;
        public EditText et_laiwendanwei;
        public EditText et_yuanwenhao;
        public EditText et_shouwenshijian;
        public EditText et_fenshu;
        public EditText et_miji;
        public EditText et_biaoti;
        public EditText et_zhaiyaotishi;
        public EditText et_nibanyijian;
        public EditText et_bangongshilingdaopishi;
        public EditText et_shiweilingdaopishi;
        public EditText et_beizhu;
        public TextView tv_people;
        public LinearLayout lin2;
        public TextView tv_look_people;
        public LinearLayout lin3;
        public SelectPeopleLayout selectPeopleLayout;
        public SelectPeopleLayout selectLookPeopleLayout;
        public TextView et_attributes2;
        public LinearLayout lin_attributes2;
        public RecyclerView rv_file;
        public LinearLayout lin4;
        public RecyclerView recycler_view;
        public LinearLayout lin_rcv;
        public TextView tv_edit_title;
        public LinearLayout lin_edit;
        public LinearLayout lin_nestedScrollView;
        public NestedScrollView nestedScrollView;
        public PennableLayout ink;
        public FrameLayout fl_root;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.et_keshi = (EditText) rootView.findViewById(R.id.et_keshi);
            this.et_dianhua = (EditText) rootView.findViewById(R.id.et_dianhua);
            this.et_shijian = (EditText) rootView.findViewById(R.id.et_shijian);
            this.et_bianhao = (EditText) rootView.findViewById(R.id.et_bianhao);
            this.et_laiwendanwei = (EditText) rootView.findViewById(R.id.et_laiwendanwei);
            this.et_yuanwenhao = (EditText) rootView.findViewById(R.id.et_yuanwenhao);
            this.et_shouwenshijian = (EditText) rootView.findViewById(R.id.et_shouwenshijian);
            this.et_fenshu = (EditText) rootView.findViewById(R.id.et_fenshu);
            this.et_miji = (EditText) rootView.findViewById(R.id.et_miji);
            this.et_biaoti = (EditText) rootView.findViewById(R.id.et_biaoti);
            this.et_zhaiyaotishi = (EditText) rootView.findViewById(R.id.et_zhaiyaotishi);
            this.et_nibanyijian = (EditText) rootView.findViewById(R.id.et_nibanyijian);
            this.et_bangongshilingdaopishi = (EditText) rootView.findViewById(R.id.et_bangongshilingdaopishi);
            this.et_shiweilingdaopishi = (EditText) rootView.findViewById(R.id.et_shiweilingdaopishi);
            this.et_beizhu = (EditText) rootView.findViewById(R.id.et_beizhu);
            this.tv_people = (TextView) rootView.findViewById(R.id.tv_people);
            this.lin2 = (LinearLayout) rootView.findViewById(R.id.lin2);
            this.tv_look_people = (TextView) rootView.findViewById(R.id.tv_look_people);
            this.lin3 = (LinearLayout) rootView.findViewById(R.id.lin3);
            this.selectPeopleLayout = (SelectPeopleLayout) rootView.findViewById(R.id.selectPeopleLayout);
            this.selectLookPeopleLayout = (SelectPeopleLayout) rootView.findViewById(R.id.selectLookPeopleLayout);
            this.et_attributes2 = (TextView) rootView.findViewById(R.id.et_attributes2);
            this.lin_attributes2 = (LinearLayout) rootView.findViewById(R.id.lin_attributes2);
            this.rv_file = (RecyclerView) rootView.findViewById(R.id.rv_file);
            this.lin4 = (LinearLayout) rootView.findViewById(R.id.lin4);
            this.recycler_view = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            this.lin_rcv = (LinearLayout) rootView.findViewById(R.id.lin_rcv);
            this.tv_edit_title = (TextView) rootView.findViewById(R.id.tv_edit_title);
            this.lin_edit = (LinearLayout) rootView.findViewById(R.id.lin_edit);
            this.lin_nestedScrollView = (LinearLayout) rootView.findViewById(R.id.lin_nestedScrollView);
            this.nestedScrollView = (NestedScrollView) rootView.findViewById(R.id.nestedScrollView);
            this.ink = (PennableLayout) rootView.findViewById(R.id.ink);
            this.fl_root = (FrameLayout) rootView.findViewById(R.id.fl_root);
        }

    }
}