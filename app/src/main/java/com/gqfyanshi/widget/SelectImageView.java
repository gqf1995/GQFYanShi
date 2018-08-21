package com.gqfyanshi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 郭青枫 on 2018/8/21 0021.
 */

public class SelectImageView extends android.support.v7.widget.AppCompatImageView {
    public SelectImageView(Context context) {
        super(context);
    }

    public SelectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }
}
