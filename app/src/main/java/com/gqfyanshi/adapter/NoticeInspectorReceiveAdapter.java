package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class NoticeInspectorReceiveAdapter extends CommonAdapter<DocumentBean> {


    private LinearLayout lin_root;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public NoticeInspectorReceiveAdapter(Context context, List<DocumentBean> datas) {
        super(context, R.layout.adapter_notice_inspector_receive, datas);

    }

    public void setData(List<DocumentBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, DocumentBean s, final int position) {
        lin_root = holder.getView(R.id.lin_root);
        lin_root.setBackgroundColor(CommonUtils.getColor(position % 2 == 0 ? R.color.white : R.color.transparent));
        tv1 = holder.getView(R.id.tv1);
        tv2 = holder.getView(R.id.tv2);
        tv3 = holder.getView(R.id.tv3);
        tv4 = holder.getView(R.id.tv4);
        tv5 = holder.getView(R.id.tv5);
        tv6 = holder.getView(R.id.tv6);
        tv1.setText(s.getId());

        tv2.setText(s.getTitle());
        tv3.setText(s.getCreatetime());

        //文章状态 00-发布 01-待审核 02-已删除

        if ("00".equals(s.getStatus())) {
            tv4.setText("发布");
        }
        if ("01".equals(s.getStatus())) {
            tv4.setText("待审核");
        }
        if ("02".equals(s.getStatus())) {
            tv4.setText("已删除");
        }


        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v,position,null);
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v,position,null);
            }
        });
    }

}