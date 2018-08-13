package com.gqfyanshi.mvp.activity.add;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddPublicInformationBinder;
import com.gqfyanshi.mvp.delegate.AddPublicInformationDelegate;

public class AddPublicInformationActivity extends BaseDataBindActivity<AddPublicInformationDelegate, AddPublicInformationBinder> {

    @Override
    protected Class<AddPublicInformationDelegate> getDelegateClass() {
        return AddPublicInformationDelegate.class;
    }

    @Override
    public AddPublicInformationBinder getDataBinder(AddPublicInformationDelegate viewDelegate) {
        return new AddPublicInformationBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("公开信息发布"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
