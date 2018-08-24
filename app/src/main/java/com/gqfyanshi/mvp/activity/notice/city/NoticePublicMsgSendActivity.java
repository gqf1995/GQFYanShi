package com.gqfyanshi.mvp.activity.notice.city;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticePublicMsgSendBinder;
import com.gqfyanshi.mvp.delegate.NoticePublicMsgSendDelegate;

public class NoticePublicMsgSendActivity extends BaseDataBindActivity<NoticePublicMsgSendDelegate, NoticePublicMsgSendBinder> {

    @Override
    protected Class<NoticePublicMsgSendDelegate> getDelegateClass() {
        return NoticePublicMsgSendDelegate.class;
    }

    @Override
    public NoticePublicMsgSendBinder getDataBinder(NoticePublicMsgSendDelegate viewDelegate) {
        return new NoticePublicMsgSendBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("公开信息发送"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
