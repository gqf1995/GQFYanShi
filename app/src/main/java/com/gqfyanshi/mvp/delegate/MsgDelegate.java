package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;

public class MsgDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg;
    }


    public static class ViewHolder {
        public View rootView;
        public TextView tv_title1;
        public RoundButton tv_num1;
        public LinearLayout lin_msg1;
        public TextView tv_title2;
        public RoundButton tv_num2;
        public LinearLayout lin_msg2;
        public TextView tv_title3;
        public RoundButton tv_num3;
        public LinearLayout lin_msg3;
        public TextView tv_title4;
        public RoundButton tv_num4;
        public LinearLayout lin_msg4;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title1 = (TextView) rootView.findViewById(R.id.tv_title1);
            this.tv_num1 = (RoundButton) rootView.findViewById(R.id.tv_num1);
            this.lin_msg1 = (LinearLayout) rootView.findViewById(R.id.lin_msg1);
            this.tv_title2 = (TextView) rootView.findViewById(R.id.tv_title2);
            this.tv_num2 = (RoundButton) rootView.findViewById(R.id.tv_num2);
            this.lin_msg2 = (LinearLayout) rootView.findViewById(R.id.lin_msg2);
            this.tv_title3 = (TextView) rootView.findViewById(R.id.tv_title3);
            this.tv_num3 = (RoundButton) rootView.findViewById(R.id.tv_num3);
            this.lin_msg3 = (LinearLayout) rootView.findViewById(R.id.lin_msg3);
            this.tv_title4 = (TextView) rootView.findViewById(R.id.tv_title4);
            this.tv_num4 = (RoundButton) rootView.findViewById(R.id.tv_num4);
            this.lin_msg4 = (LinearLayout) rootView.findViewById(R.id.lin_msg4);
        }

    }
}