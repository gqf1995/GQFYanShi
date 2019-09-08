package com.gqfyanshi.mvp.activity;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.SendSignBinder;
import com.gqfyanshi.mvp.delegate.SendSignDelegate;

public class SendSignDetailActivity extends BaseDataBindActivity<SendSignDelegate, SendSignBinder> {

    @Override
    protected Class<SendSignDelegate> getDelegateClass() {
        return SendSignDelegate.class;
    }

    @Override
    public SendSignBinder getDataBinder(SendSignDelegate viewDelegate) {
        return new SendSignBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("市委办公室发文处理签"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
