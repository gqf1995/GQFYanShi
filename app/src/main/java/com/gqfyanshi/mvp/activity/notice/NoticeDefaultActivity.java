package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeDefaultBinder;
import com.gqfyanshi.mvp.delegate.NoticeDefaultDelegate;

public class NoticeDefaultActivity extends BaseDataBindActivity<NoticeDefaultDelegate, NoticeDefaultBinder> {

    @Override
    protected Class<NoticeDefaultDelegate> getDelegateClass() {
        return NoticeDefaultDelegate.class;
    }

    @Override
    public NoticeDefaultBinder getDataBinder(NoticeDefaultDelegate viewDelegate) {
        return new NoticeDefaultBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("一般性公告"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
