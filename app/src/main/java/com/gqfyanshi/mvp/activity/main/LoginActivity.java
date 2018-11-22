package com.gqfyanshi.mvp.activity.main;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.request.RequestOptions;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.glide.GlideUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.databinder.LoginBinder;
import com.gqfyanshi.mvp.delegate.LoginDelegate;
import com.gqfyanshi.server.HttpUrl;

public class LoginActivity extends BaseDataBindActivity<LoginDelegate, LoginBinder> {

    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    boolean isSelect = false;

    @Override
    public LoginBinder getDataBinder(LoginDelegate viewDelegate) {
        return new LoginBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //viewDelegate.viewHolder.tv_phone.setText("15896559159");
        //viewDelegate.viewHolder.tv_code.setText("231231");
        //viewDelegate.viewHolder.tv_img_code.setText("21312");
        //viewDelegate.viewHolder.tv_phone.setText("17396360301");
        isSelect = SaveUtil.getInstance().getBoolean("login_save");
        if (!isSelect) {
            viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.weigouxuan);
        } else {
            viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.gouxuan);
            viewDelegate.viewHolder.tv_phone.setText(SaveUtil.getInstance().getString("login_phone"));
        }
        GlideUtils.loadImage(
                HttpUrl.getIntance().pictureCheckCode,
                viewDelegate.viewHolder.iv_img_code,
                new RequestOptions()
        );
        viewDelegate.viewHolder.lin_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelect) {
                    isSelect = false;
                    viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.weigouxuan);
                } else {
                    isSelect = true;
                    viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.gouxuan);
                }
            }
        });
        viewDelegate.viewHolder.tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_phone.getText().toString())) {
                    ToastUtil.show("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_code.getText().toString())) {
                    ToastUtil.show("请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_img_code.getText().toString())) {
                    ToastUtil.show("请输入图形验证码");
                    return;
                }
                addRequest(binder.doLogin(
                        viewDelegate.viewHolder.tv_phone.getText().toString(),
                        viewDelegate.viewHolder.tv_img_code.getText().toString(),
                        viewDelegate.viewHolder.tv_code.getText().toString(),
                        LoginActivity.this
                ));
            }
        });
        viewDelegate.viewHolder.tv_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_phone.getText().toString())) {
                    ToastUtil.show("请输入手机号");
                    return;
                }
                addRequest(binder.sendPhoneCode(viewDelegate.viewHolder.tv_phone.getText().toString(),
                        LoginActivity.this));
            }
        });
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x124:
                UserLoginBean userLoginBean = GsonUtil.getInstance().toObj(data, UserLoginBean.class);
                UserLoginBean.addNewLoginInfo(userLoginBean);
                startActivity(new Intent(this, MainActivity.class));
                if (isSelect) {
                    SaveUtil.getInstance().saveString("login_phone", viewDelegate.viewHolder.tv_phone.getText().toString());
                } else {
                    SaveUtil.getInstance().saveString("login_phone", "");
                }
                SaveUtil.getInstance().saveBoolean("login_save", isSelect);
                break;
            case 0x125:
                addRequest(UiHeplUtils.getCode(viewDelegate.viewHolder.tv_send_code, 60));
                break;
        }
    }

}
