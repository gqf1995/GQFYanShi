package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeInspectorBinder;
import com.gqfyanshi.mvp.delegate.NoticeInspectorDelegate;

public class NoticeInspectorActivity extends BaseDataBindActivity<NoticeInspectorDelegate, NoticeInspectorBinder> {

    @Override
    protected Class<NoticeInspectorDelegate> getDelegateClass() {
        return NoticeInspectorDelegate.class;
    }

    @Override
    public NoticeInspectorBinder getDataBinder(NoticeInspectorDelegate viewDelegate) {
        return new NoticeInspectorBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("督查通知发送"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
