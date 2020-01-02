package com.gqfyanshi.mvp.delegate;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.gqfyanshi.R;

public class UserDrawerDelegate extends BaseDelegate {
    public ViewHolder viewHolder;

    @Override
    public void initView() {
        viewHolder = new ViewHolder(getRootView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_drawer;
    }


    public static
    class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_division;
        public TextView tv_position;
        public TextView tv_phone;
        public RelativeLayout contentView;
        public ImageView iv_logout;
        public TextView tv_logout;
        public LinearLayout lin_logout;
        public ImageView iv_module1;
        public TextView tv_module1;
        public LinearLayout lin_module1;
        public LinearLayout lin_mod1_content1;
        public LinearLayout lin_mod1_content2;
        public LinearLayout lin_content1;
        public ImageView iv_module2;
        public TextView tv_module2;
        public LinearLayout lin_module2;
        public LinearLayout lin_mod2_content1;
        public LinearLayout lin_mod2_content2;
        public LinearLayout lin_mod2_content3;
        public LinearLayout lin_content2;
        public ImageView iv_module3;
        public TextView tv_module3;
        public LinearLayout lin_module3;
        public ImageView iv_module4;
        public TextView tv_module4;
        public LinearLayout lin_module4;
        public LinearLayout lin_mod4_content1;
        public LinearLayout lin_mod4_content2;
        public LinearLayout lin_mod4_content3;
        public LinearLayout lin_mod4_content4;
        public LinearLayout lin_mod4_content5;
        public LinearLayout lin_mod4_content6;
        public LinearLayout lin_mod4_content7;
        public LinearLayout lin_mod4_content8;
        public LinearLayout lin_content4;
        public ImageView iv_module5;
        public TextView tv_module5;
        public LinearLayout lin_module5;
        public LinearLayout lin_mod5_content1;
        public LinearLayout lin_mod5_content2;
        public LinearLayout lin_mod5_content3;
        public LinearLayout lin_mod5_content4;
        public LinearLayout lin_mod5_content5;
        public LinearLayout lin_mod5_content6;
        public LinearLayout lin_mod5_content7;
        public LinearLayout lin_content5;
        public ImageView iv_module6;
        public TextView tv_module6;
        public LinearLayout lin_module6;
        public LinearLayout lin_mod6_content1;
        public LinearLayout lin_mod6_content2;
        public LinearLayout lin_mod6_content3;
        public LinearLayout lin_mod6_content4;
        public LinearLayout lin_mod6_content5;
        public LinearLayout lin_mod6_content6;
        public LinearLayout lin_content6;
        public ImageView iv_module7;
        public TextView tv_module7;
        public LinearLayout lin_module7;
        public LinearLayout lin_mod7_content1;
        public LinearLayout lin_mod7_content2;
        public LinearLayout lin_mod7_content3;
        public LinearLayout lin_mod7_content4;
        public LinearLayout lin_mod7_content5;
        public LinearLayout lin_content7;
        public ImageView iv_module8;
        public TextView tv_module8;
        public LinearLayout lin_module8;
        public ImageView iv_module9;
        public TextView tv_module9;
        public LinearLayout lin_module9;
        public LinearLayout lin_mod9_content1;
        public LinearLayout lin_mod9_content2;
        public LinearLayout lin_mod9_content3;
        public LinearLayout lin_content9;
        public ImageView iv_module10;
        public TextView tv_module10;
        public LinearLayout lin_module10;
        public ImageView iv_module11;
        public TextView tv_module11;
        public LinearLayout lin_module11;
        public ImageView iv_module12;
        public TextView tv_module12;
        public LinearLayout lin_module12;
        public ImageView iv_module13;
        public TextView tv_module13;
        public LinearLayout lin_module13;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_division = (TextView) rootView.findViewById(R.id.tv_division);
            this.tv_position = (TextView) rootView.findViewById(R.id.tv_position);
            this.tv_phone = (TextView) rootView.findViewById(R.id.tv_phone);
            this.contentView = (RelativeLayout) rootView.findViewById(R.id.contentView);
            this.iv_logout = (ImageView) rootView.findViewById(R.id.iv_logout);
            this.tv_logout = (TextView) rootView.findViewById(R.id.tv_logout);
            this.lin_logout = (LinearLayout) rootView.findViewById(R.id.lin_logout);
            this.iv_module1 = (ImageView) rootView.findViewById(R.id.iv_module1);
            this.tv_module1 = (TextView) rootView.findViewById(R.id.tv_module1);
            this.lin_module1 = (LinearLayout) rootView.findViewById(R.id.lin_module1);
            this.lin_mod1_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod1_content1);
            this.lin_mod1_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod1_content2);
            this.lin_content1 = (LinearLayout) rootView.findViewById(R.id.lin_content1);
            this.iv_module2 = (ImageView) rootView.findViewById(R.id.iv_module2);
            this.tv_module2 = (TextView) rootView.findViewById(R.id.tv_module2);
            this.lin_module2 = (LinearLayout) rootView.findViewById(R.id.lin_module2);
            this.lin_mod2_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod2_content1);
            this.lin_mod2_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod2_content2);
            this.lin_mod2_content3 = (LinearLayout) rootView.findViewById(R.id.lin_mod2_content3);
            this.lin_content2 = (LinearLayout) rootView.findViewById(R.id.lin_content2);
            this.iv_module3 = (ImageView) rootView.findViewById(R.id.iv_module3);
            this.tv_module3 = (TextView) rootView.findViewById(R.id.tv_module3);
            this.lin_module3 = (LinearLayout) rootView.findViewById(R.id.lin_module3);
            this.iv_module4 = (ImageView) rootView.findViewById(R.id.iv_module4);
            this.tv_module4 = (TextView) rootView.findViewById(R.id.tv_module4);
            this.lin_module4 = (LinearLayout) rootView.findViewById(R.id.lin_module4);
            this.lin_mod4_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content1);
            this.lin_mod4_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content2);
            this.lin_mod4_content3 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content3);
            this.lin_mod4_content4 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content4);
            this.lin_mod4_content5 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content5);
            this.lin_mod4_content6 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content6);
            this.lin_mod4_content7 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content7);
            this.lin_mod4_content8 = (LinearLayout) rootView.findViewById(R.id.lin_mod4_content8);
            this.lin_content4 = (LinearLayout) rootView.findViewById(R.id.lin_content4);
            this.iv_module5 = (ImageView) rootView.findViewById(R.id.iv_module5);
            this.tv_module5 = (TextView) rootView.findViewById(R.id.tv_module5);
            this.lin_module5 = (LinearLayout) rootView.findViewById(R.id.lin_module5);
            this.lin_mod5_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content1);
            this.lin_mod5_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content2);
            this.lin_mod5_content3 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content3);
            this.lin_mod5_content4 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content4);
            this.lin_mod5_content5 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content5);
            this.lin_mod5_content6 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content6);
            this.lin_mod5_content7 = (LinearLayout) rootView.findViewById(R.id.lin_mod5_content7);
            this.lin_content5 = (LinearLayout) rootView.findViewById(R.id.lin_content5);
            this.iv_module6 = (ImageView) rootView.findViewById(R.id.iv_module6);
            this.tv_module6 = (TextView) rootView.findViewById(R.id.tv_module6);
            this.lin_module6 = (LinearLayout) rootView.findViewById(R.id.lin_module6);
            this.lin_mod6_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod6_content1);
            this.lin_mod6_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod6_content2);
            this.lin_mod6_content3 = (LinearLayout) rootView.findViewById(R.id.lin_mod6_content3);
            this.lin_mod6_content4 = (LinearLayout) rootView.findViewById(R.id.lin_mod6_content4);
            this.lin_mod6_content5 = (LinearLayout) rootView.findViewById(R.id.lin_mod6_content5);
            this.lin_mod6_content6 = (LinearLayout) rootView.findViewById(R.id.lin_mod6_content6);
            this.lin_content6 = (LinearLayout) rootView.findViewById(R.id.lin_content6);
            this.iv_module7 = (ImageView) rootView.findViewById(R.id.iv_module7);
            this.tv_module7 = (TextView) rootView.findViewById(R.id.tv_module7);
            this.lin_module7 = (LinearLayout) rootView.findViewById(R.id.lin_module7);
            this.lin_mod7_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod7_content1);
            this.lin_mod7_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod7_content2);
            this.lin_mod7_content3 = (LinearLayout) rootView.findViewById(R.id.lin_mod7_content3);
            this.lin_mod7_content4 = (LinearLayout) rootView.findViewById(R.id.lin_mod7_content4);
            this.lin_mod7_content5 = (LinearLayout) rootView.findViewById(R.id.lin_mod7_content5);
            this.lin_content7 = (LinearLayout) rootView.findViewById(R.id.lin_content7);
            this.iv_module8 = (ImageView) rootView.findViewById(R.id.iv_module8);
            this.tv_module8 = (TextView) rootView.findViewById(R.id.tv_module8);
            this.lin_module8 = (LinearLayout) rootView.findViewById(R.id.lin_module8);
            this.iv_module9 = (ImageView) rootView.findViewById(R.id.iv_module9);
            this.tv_module9 = (TextView) rootView.findViewById(R.id.tv_module9);
            this.lin_module9 = (LinearLayout) rootView.findViewById(R.id.lin_module9);
            this.lin_mod9_content1 = (LinearLayout) rootView.findViewById(R.id.lin_mod9_content1);
            this.lin_mod9_content2 = (LinearLayout) rootView.findViewById(R.id.lin_mod9_content2);
            this.lin_mod9_content3 = (LinearLayout) rootView.findViewById(R.id.lin_mod9_content3);
            this.lin_content9 = (LinearLayout) rootView.findViewById(R.id.lin_content9);
            this.iv_module10 = (ImageView) rootView.findViewById(R.id.iv_module10);
            this.tv_module10 = (TextView) rootView.findViewById(R.id.tv_module10);
            this.lin_module10 = (LinearLayout) rootView.findViewById(R.id.lin_module10);
            this.iv_module11 = (ImageView) rootView.findViewById(R.id.iv_module11);
            this.tv_module11 = (TextView) rootView.findViewById(R.id.tv_module11);
            this.lin_module11 = (LinearLayout) rootView.findViewById(R.id.lin_module11);
            this.iv_module12 = (ImageView) rootView.findViewById(R.id.iv_module12);
            this.tv_module12 = (TextView) rootView.findViewById(R.id.tv_module12);
            this.lin_module12 = (LinearLayout) rootView.findViewById(R.id.lin_module12);
            this.iv_module13 = (ImageView) rootView.findViewById(R.id.iv_module13);
            this.tv_module13 = (TextView) rootView.findViewById(R.id.tv_module13);
            this.lin_module13 = (LinearLayout) rootView.findViewById(R.id.lin_module13);
        }

    }
}