package com.gqfyanshi.mvp.activity.main;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.WelcomeBinder;
import com.gqfyanshi.mvp.delegate.WelcomeDelegate;

public class WelcomeActivity extends BaseDataBindActivity<WelcomeDelegate, WelcomeBinder> {

    @Override
    protected Class<WelcomeDelegate> getDelegateClass() {
        return WelcomeDelegate.class;
    }

    @Override
    public WelcomeBinder getDataBinder(WelcomeDelegate viewDelegate) {
        return new WelcomeBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle(""));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
