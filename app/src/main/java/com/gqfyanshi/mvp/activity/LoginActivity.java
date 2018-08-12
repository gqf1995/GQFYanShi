package com.gqfyanshi.mvp.activity;

import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.gqfyanshi.R;
import com.gqfyanshi.mvp.databinder.LoginBinder;
import com.gqfyanshi.mvp.delegate.LoginDelegate;

public class LoginActivity extends BaseDataBindActivity<LoginDelegate, LoginBinder> {

    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    boolean isSelect=false;

    @Override
    public LoginBinder getDataBinder(LoginDelegate viewDelegate) {
        return new LoginBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.viewHolder.lin_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelect){
                    isSelect=false;
                    viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.weigouxuan);
                }else{
                    isSelect=true;
                    viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.gouxuan);
                }
            }
        });
        viewDelegate.viewHolder.tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewDelegate.getActivity(),MainActivity.class));
            }
        });
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
