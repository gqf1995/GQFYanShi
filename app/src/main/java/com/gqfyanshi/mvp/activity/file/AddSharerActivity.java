package com.gqfyanshi.mvp.activity.file;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddSharerBinder;
import com.gqfyanshi.mvp.delegate.AddSharerDelegate;

public class AddSharerActivity extends BaseDataBindActivity<AddSharerDelegate, AddSharerBinder> {

    @Override
    protected Class<AddSharerDelegate> getDelegateClass() {
        return AddSharerDelegate.class;
    }

    @Override
    public AddSharerBinder getDataBinder(AddSharerDelegate viewDelegate) {
        return new AddSharerBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("添加共享成员"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
