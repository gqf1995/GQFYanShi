package com.gqfyanshi.widget;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.DropDownAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/7/27 0027.
 */

public class DropDownPopu {

    PopupWindow popupWindow;
    RecyclerView recyclerview;
    DropDownAdapter adapter;


    public DropDownAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DropDownAdapter adapter) {
        this.adapter = adapter;
    }

    public void dimess() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public void show(List<String> data, View anchor, Context context,
                     MultiItemTypeAdapter.OnItemClickListener onItemClickListener) {
        if (popupWindow == null) {
            recyclerview = new RecyclerView(context);
            recyclerview.setVerticalScrollBarEnabled(false);
            recyclerview.setHorizontalScrollBarEnabled(false);
            popupWindow = new PopupWindow(context);
            popupWindow.setContentView(recyclerview);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.setElevation(4);
            }
            popupWindow.setBackgroundDrawable(CommonUtils.getDrawable(R.drawable.shape_grey_border_radiu5));
            adapter = new DropDownAdapter(context, data);
            recyclerview.setLayoutManager(new LinearLayoutManager(context) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            recyclerview.setAdapter(adapter);
            adapter.setOnItemClickListener(onItemClickListener);
            popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        popupWindow.showAsDropDown(anchor);
    }


}
