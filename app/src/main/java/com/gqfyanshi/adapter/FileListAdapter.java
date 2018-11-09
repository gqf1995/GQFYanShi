package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.FileItemBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class FileListAdapter extends CommonAdapter<FileItemBean> {


    private TextView tv_name;
    private TextView tv_look;
    private TextView tv_delect;

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public FileListAdapter(Context context, List<FileItemBean> datas) {
        super(context, R.layout.adapter_file_list_item, datas);

    }

    public void setData(List<FileItemBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, FileItemBean s, final int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_look = holder.getView(R.id.tv_look);
        tv_delect = holder.getView(R.id.tv_delect);

        tv_name.setText(s.getName());

        tv_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v, position, null);
            }
        });
        tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v, position, null);
            }
        });
    }

}