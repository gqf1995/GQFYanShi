package com.gqfyanshi.mvp.activity.add;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddInspectorNoticeBinder;
import com.gqfyanshi.mvp.delegate.AddInspectorNoticeDelegate;

public class AddInspectorNoticeActivity extends BaseDataBindActivity<AddInspectorNoticeDelegate, AddInspectorNoticeBinder> {

    @Override
    protected Class<AddInspectorNoticeDelegate> getDelegateClass() {
        return AddInspectorNoticeDelegate.class;
    }

    @Override
    public AddInspectorNoticeBinder getDataBinder(AddInspectorNoticeDelegate viewDelegate) {
        return new AddInspectorNoticeBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("督查通知发布"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
