package com.gqfyanshi.adapter;

import android.content.Context;
import android.widget.TextView;

import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.DocumentListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class DocumentListAdapter extends CommonAdapter<DocumentListBean> {


    private TextView tv_title;
    private TextView tv_time;

    public DocumentListAdapter(Context context, List<DocumentListBean> datas) {
        super(context, R.layout.adapter_document_list, datas);

    }

    public void setData(List<DocumentListBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, DocumentListBean s, final int position) {
        tv_time = holder.getView(R.id.tv_time);
        tv_title = holder.getView(R.id.tv_title);
        tv_title.setText(s.getTitle());
        tv_time.setText(s.getCreatetime());

    }

}