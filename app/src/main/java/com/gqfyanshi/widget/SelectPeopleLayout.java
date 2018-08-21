package com.gqfyanshi.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gqfyanshi.R;

/**
 * Created by 郭青枫 on 2018/8/21 0021.
 */

public class SelectPeopleLayout extends FrameLayout {
    Context mContext;
    SelectPeoplePopu selectPeoplePopu;

    public SelectPeopleLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SelectPeopleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SelectPeopleLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public EditText et_attributes1;
    public LinearLayout lin_attributes1;

    private void initView(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.layout_select_people, null);
        this.et_attributes1 = (EditText) rootView.findViewById(R.id.et_attributes1);
        this.lin_attributes1 = (LinearLayout) rootView.findViewById(R.id.lin_attributes1);
        lin_attributes1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectPeoplePopu == null) {
                    selectPeoplePopu = new SelectPeoplePopu(mContext);
                }
                selectPeoplePopu.show(v);
            }
        });
        this.addView(rootView);
    }

}
