package com.gqfyanshi.mvp.activity.add;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddSendMailBinder;
import com.gqfyanshi.mvp.delegate.AddSendMailDelegate;

public class AddSendMailActivity extends BaseDataBindActivity<AddSendMailDelegate, AddSendMailBinder> {

    @Override
    protected Class<AddSendMailDelegate> getDelegateClass() {
        return AddSendMailDelegate.class;
    }

    @Override
    public AddSendMailBinder getDataBinder(AddSendMailDelegate viewDelegate) {
        return new AddSendMailBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("邮件发送"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
