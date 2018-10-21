package com.gqfyanshi.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/9/27 0027.
 */

public class SelectAttrLayout extends FrameLayout {
    Context mContext;
    DropDownPopu dropDownPopu;

    public SelectAttrLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SelectAttrLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SelectAttrLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    List<String> datas;

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public void setDatas(List<String> datas, int position) {
        this.datas = datas;
        et_attributes1.setText(datas.get(position));
    }

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    public TextView et_attributes1;
    public LinearLayout lin_attributes1;

    private void initView(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.layout_select_people, null);
        this.et_attributes1 = (TextView) rootView.findViewById(R.id.et_attributes1);
        this.lin_attributes1 = (LinearLayout) rootView.findViewById(R.id.lin_attributes1);
        lin_attributes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                dropDownPopu = new DropDownPopu();
                dropDownPopu.show(datas, v, mContext, new MultiItemTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                        et_attributes1.setText(datas.get(position));
                        defaultClickLinsener.onClick(v, position, null);
                        dropDownPopu.dimess();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
            }
        });
        this.addView(rootView);
    }

    public void setShowEdit(String text) {
        et_attributes1.setEnabled(false);
        et_attributes1.setText(text);
        lin_attributes1.setOnClickListener(null);
    }

}
