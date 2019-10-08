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

public class NoticeReceiveOfficialDocumentAdapter extends CommonAdapter<DocumentBean> {


    private LinearLayout lin_root;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;

    boolean isShowDelect=true;

    public void setShowDelect(boolean showDelect) {
        isShowDelect = showDelect;
    }

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public NoticeReceiveOfficialDocumentAdapter(Context context, List<DocumentBean> datas) {
        super(context, R.layout.adapter_notice_send_official_document, datas);

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

        tv1.setText(s.getId() + "");
        tv2.setText(s.getTitle());
        tv3.setText(s.getName());
        tv4.setText(s.getCreatetime());

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v, position, null);
            }
        });
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v, position, null);
            }
        });

        tv6.setVisibility(isShowDelect?View.VISIBLE:View.GONE);
    }

}