package com.gqfyanshi.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.view.dialog.BirthdayDialog;
import com.gqfyanshi.R;

/**
 * Created by 郭青枫 on 2018/8/21 0021.
 */

public class SelectTimeLayout extends FrameLayout {
    Context mContext;

    public SelectTimeLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SelectTimeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SelectTimeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public TextView et_time1;
    public LinearLayout lin_select_time1;
    String selectTime = "";

    public String getSelectTime() {
        return TextUtils.isEmpty(selectTime) ? "" : selectTime + " 00:00:00";
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.layout_select_time, null);
        this.et_time1 = (TextView) rootView.findViewById(R.id.et_time1);
        this.lin_select_time1 = (LinearLayout) rootView.findViewById(R.id.lin_select_time1);

        lin_select_time1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new BirthdayDialog(mContext, new BirthdayDialog.OnTimeChooseListener() {
                    @Override
                    public void setOnTimeChooseListener(String time) {
                        selectTime = time;
                        et_time1.setText(selectTime);
                    }
                }).show();
            }
        });
        this.addView(rootView);
    }

    public void setSelectTime(String selectTime) {
        selectTime = selectTime.replace(" 00:00:00", "");
        this.selectTime = selectTime;
        et_time1.setText(selectTime);
    }
}
