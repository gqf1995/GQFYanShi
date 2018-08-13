package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeAddressBookBinder;
import com.gqfyanshi.mvp.delegate.NoticeAddressBookDelegate;

public class NoticeAddressBookActivity extends BaseDataBindActivity<NoticeAddressBookDelegate, NoticeAddressBookBinder> {

    @Override
    protected Class<NoticeAddressBookDelegate> getDelegateClass() {
        return NoticeAddressBookDelegate.class;
    }

    @Override
    public NoticeAddressBookBinder getDataBinder(NoticeAddressBookDelegate viewDelegate) {
        return new NoticeAddressBookBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("电子通讯录"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
