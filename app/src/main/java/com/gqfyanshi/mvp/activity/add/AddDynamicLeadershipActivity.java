package com.gqfyanshi.mvp.activity.add;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddDynamicLeadershipBinder;
import com.gqfyanshi.mvp.delegate.AddDynamicLeadershipDelegate;

public class AddDynamicLeadershipActivity extends BaseDataBindActivity<AddDynamicLeadershipDelegate, AddDynamicLeadershipBinder> {

    @Override
    protected Class<AddDynamicLeadershipDelegate> getDelegateClass() {
        return AddDynamicLeadershipDelegate.class;
    }

    @Override
    public AddDynamicLeadershipBinder getDataBinder(AddDynamicLeadershipDelegate viewDelegate) {
        return new AddDynamicLeadershipBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("领导动态"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
