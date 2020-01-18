package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.RoundButton;
import com.gqfyanshi.R;

public class LoginDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    public static class ViewHolder {
        public View rootView;
        public EditText tv_phone;
        public EditText tv_code;
        public RoundButton tv_send_code;
        public EditText tv_img_code;
        public ImageView iv_img_code;
        public TextView tv_change_img;
        public TextView tv_no;
        public LinearLayout lin_change;
        public ImageView iv_select;
        public LinearLayout lin_select;
        public RoundButton tv_login;
        public LinearLayout lin_login;
        public ImageView iv_fly;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_phone = (EditText) rootView.findViewById(R.id.tv_phone);
            this.tv_code = (EditText) rootView.findViewById(R.id.tv_code);
            this.tv_send_code = (RoundButton) rootView.findViewById(R.id.tv_send_code);
            this.tv_img_code = (EditText) rootView.findViewById(R.id.tv_img_code);
            this.iv_img_code = (ImageView) rootView.findViewById(R.id.iv_img_code);
            this.tv_change_img = (TextView) rootView.findViewById(R.id.tv_change_img);
            this.tv_no = (TextView) rootView.findViewById(R.id.tv_no);
            this.lin_change = (LinearLayout) rootView.findViewById(R.id.lin_change);
            this.iv_select = (ImageView) rootView.findViewById(R.id.iv_select);
            this.lin_select = (LinearLayout) rootView.findViewById(R.id.lin_select);
            this.tv_login = (RoundButton) rootView.findViewById(R.id.tv_login);
            this.lin_login = (LinearLayout) rootView.findViewById(R.id.lin_login);
            this.iv_fly = (ImageView) rootView.findViewById(R.id.iv_fly);
        }

    }
}