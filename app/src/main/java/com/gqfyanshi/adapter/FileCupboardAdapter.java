package com.gqfyanshi.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.gqfyanshi.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class FileCupboardAdapter extends CommonAdapter<String> {


    private ImageView iv_piv;
    private TextView tv_name;
    private TextView tv_time;

    public FileCupboardAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_file_cupboard, datas);

    }

    public void setData(List<String> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        tv_time = holder.getView(R.id.tv_time);
        iv_piv = holder.getView(R.id.iv_piv);
        tv_name = holder.getView(R.id.tv_name);

        tv_name.setText(s);


    }

}