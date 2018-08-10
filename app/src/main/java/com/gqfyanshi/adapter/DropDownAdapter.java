package com.gqfyanshi.adapter;

import android.content.Context;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.gqfyanshi.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/16 0016.
 */

public class DropDownAdapter extends CommonAdapter<String> {


    public DropDownAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_drop_down_item, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, final int position) {
        TextView tv_item;
        tv_item = holder.getView(R.id.tv_item);
        int left = tv_item.getLeft();
        int right = tv_item.getRight();
        //tv_item.setLayoutParams(new ConstraintLayout.LayoutParams(itemWidth - left - right, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv_item.setText(s);
        tv_item.setBackground(null);
        tv_item.setTextColor(CommonUtils.getColor(R.color.color_font2));
    }
}