package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticePublicMsgReceiveBinder;
import com.gqfyanshi.mvp.delegate.NoticePublicMsgReceiveDelegate;

public class NoticePublicMsgReceiveActivity extends BaseDataBindActivity<NoticePublicMsgReceiveDelegate, NoticePublicMsgReceiveBinder> {

    @Override
    protected Class<NoticePublicMsgReceiveDelegate> getDelegateClass() {
        return NoticePublicMsgReceiveDelegate.class;
    }

    @Override
    public NoticePublicMsgReceiveBinder getDataBinder(NoticePublicMsgReceiveDelegate viewDelegate) {
        return new NoticePublicMsgReceiveBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("公开信息接收"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
