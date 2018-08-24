package com.gqfyanshi.mvp.activity.notice.city;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeInspectorReceiveBinder;
import com.gqfyanshi.mvp.delegate.NoticeInspectorReceiveDelegate;

public class NoticeCityInspectorReceiveActivity extends BaseDataBindActivity<NoticeInspectorReceiveDelegate, NoticeInspectorReceiveBinder> {

    @Override
    protected Class<NoticeInspectorReceiveDelegate> getDelegateClass() {
        return NoticeInspectorReceiveDelegate.class;
    }

    @Override
    public NoticeInspectorReceiveBinder getDataBinder(NoticeInspectorReceiveDelegate viewDelegate) {
        return new NoticeInspectorReceiveBinder(viewDelegate);
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
