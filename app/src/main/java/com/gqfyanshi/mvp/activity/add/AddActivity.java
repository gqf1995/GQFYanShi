package com.gqfyanshi.mvp.activity.add;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddBinder;
import com.gqfyanshi.mvp.delegate.AddDelegate;

public class AddActivity extends BaseDataBindActivity<AddDelegate, AddBinder> {

    @Override
    protected Class<AddDelegate> getDelegateClass() {
        return AddDelegate.class;
    }

    @Override
    public AddBinder getDataBinder(AddDelegate viewDelegate) {
        return new AddBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("添加"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
