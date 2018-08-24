package com.gqfyanshi.mvp.activity.notice.mail;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeInboxBinder;
import com.gqfyanshi.mvp.delegate.NoticeInboxDelegate;

public class NoticeInboxActivity extends BaseDataBindActivity<NoticeInboxDelegate, NoticeInboxBinder> {

    @Override
    protected Class<NoticeInboxDelegate> getDelegateClass() {
        return NoticeInboxDelegate.class;
    }

    @Override
    public NoticeInboxBinder getDataBinder(NoticeInboxDelegate viewDelegate) {
        return new NoticeInboxBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("收件箱"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
