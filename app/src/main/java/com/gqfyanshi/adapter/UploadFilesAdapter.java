package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.UploadFile;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class UploadFilesAdapter extends CommonAdapter<UploadFile> {


    private TextView tv_name;
    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public UploadFilesAdapter(Context context, List<UploadFile> datas) {
        super(context, R.layout.adapter_upload_file_item, datas);

    }

    public void setData(List<UploadFile> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, UploadFile s, final int position) {
        tv_name = holder.getView(R.id.tv_name);

        tv_name.setText(s.getFileName());
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultClickLinsener.onClick(v,position,null);
            }
        });

    }

}