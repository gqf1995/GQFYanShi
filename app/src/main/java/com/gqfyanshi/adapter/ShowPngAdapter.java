package com.gqfyanshi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.utils.glide.GlideUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.base.AppConst;
import com.gqfyanshi.entity.bean.PostilsBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class ShowPngAdapter extends CommonAdapter<PostilsBean> {


    private ImageView iv_piv;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_delect;

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public ShowPngAdapter(Context context, List<PostilsBean> datas) {
        super(context, R.layout.adapter_show_png, datas);

    }

    public void setData(List<PostilsBean> datas) {
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, PostilsBean s, final int position) {
        iv_piv = holder.getView(R.id.iv_piv);
        tv_name = holder.getView(R.id.tv_name);
        tv_time = holder.getView(R.id.tv_time);
        tv_delect = holder.getView(R.id.tv_delect);
        GlideUtils.loadImage(AppConst.app2BaseUrl + "/" + s.getPostilAddress(), iv_piv, new RequestOptions());
        tv_time.setText(s.getcTime());
        tv_name.setText(s.getName());

        tv_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (defaultClickLinsener != null) {
                    defaultClickLinsener.onClick(v, position, null);
                }
            }
        });
    }

}