package com.gqfyanshi.mvp.activity.notice.city;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeEmergencyBinder;
import com.gqfyanshi.mvp.delegate.NoticeEmergencyDelegate;

public class NoticeCityManuscriptsSendActivity extends BaseDataBindActivity<NoticeEmergencyDelegate, NoticeEmergencyBinder> {

    @Override
    protected Class<NoticeEmergencyDelegate> getDelegateClass() {
        return NoticeEmergencyDelegate.class;
    }

    @Override
    public NoticeEmergencyBinder getDataBinder(NoticeEmergencyDelegate viewDelegate) {
        return new NoticeEmergencyBinder(viewDelegate);
    }

    int pageNumber = 1;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("约稿性发送"));
        onRefush();
    }


    private void onRefush() {
        addRequest(binder.conventional_sendList(pageNumber, this));
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
