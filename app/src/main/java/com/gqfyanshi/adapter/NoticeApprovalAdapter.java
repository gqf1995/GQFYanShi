package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.ApprovalBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class NoticeApprovalAdapter extends CommonAdapter<ApprovalBean> {


    private LinearLayout lin_root;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public NoticeApprovalAdapter(Context context, List<ApprovalBean> datas) {
        super(context, R.layout.adapter_notice_approval, datas);

    }

    public void setData(List<ApprovalBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, ApprovalBean s, final int position) {
        lin_root = holder.getView(R.id.lin_root);
        lin_root.setBackgroundColor(CommonUtils.getColor(position % 2 == 0 ? R.color.white : R.color.transparent));
        tv1 = holder.getView(R.id.tv1);
        tv2 = holder.getView(R.id.tv2);
        tv3 = holder.getView(R.id.tv3);
        tv4 = holder.getView(R.id.tv4);
        tv5 = holder.getView(R.id.tv5);
        tv6 = holder.getView(R.id.tv6);
        tv7 = holder.getView(R.id.tv7);

        tv1.setText(s.getId() + "");

        tv2.setText(s.getTitle());
        tv3.setText(s.getName());
        tv4.setText(s.getUserName());

        tv5.setText(s.getCTime());

        tv7.setVisibility(View.VISIBLE);
        if (ObjectUtils.equals("00", s.getStatus())) {
            tv7.setText("未签批");
        } else if (ObjectUtils.equals("01", s.getStatus())) {
            tv7.setText("已签批");
        } else if (ObjectUtils.equals("02", s.getStatus())) {
            tv7.setText("待签批");
        } else {
            tv7.setText("未签批");
            tv7.setVisibility(View.INVISIBLE);
        }

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v, position, null);
            }
        });

    }

}