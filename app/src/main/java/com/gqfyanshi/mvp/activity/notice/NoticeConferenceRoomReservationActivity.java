package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeConferenceRoomReservationBinder;
import com.gqfyanshi.mvp.delegate.NoticeConferenceRoomReservationDelegate;

public class NoticeConferenceRoomReservationActivity extends BaseDataBindActivity<NoticeConferenceRoomReservationDelegate, NoticeConferenceRoomReservationBinder> {

    @Override
    protected Class<NoticeConferenceRoomReservationDelegate> getDelegateClass() {
        return NoticeConferenceRoomReservationDelegate.class;
    }

    @Override
    public NoticeConferenceRoomReservationBinder getDataBinder(NoticeConferenceRoomReservationDelegate viewDelegate) {
        return new NoticeConferenceRoomReservationBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("会议室预约"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
