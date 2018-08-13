package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeInboxInspectorBinder;
import com.gqfyanshi.mvp.delegate.NoticeInboxInspectorDelegate;

public class NoticeInboxInspectorActivity extends BaseDataBindActivity<NoticeInboxInspectorDelegate, NoticeInboxInspectorBinder> {

    @Override
    protected Class<NoticeInboxInspectorDelegate> getDelegateClass() {
        return NoticeInboxInspectorDelegate.class;
    }

    @Override
    public NoticeInboxInspectorBinder getDataBinder(NoticeInboxInspectorDelegate viewDelegate) {
        return new NoticeInboxInspectorBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("督查通知接收"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
