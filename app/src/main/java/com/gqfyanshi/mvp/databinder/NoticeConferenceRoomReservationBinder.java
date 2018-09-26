package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.NoticeConferenceRoomReservationDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeConferenceRoomReservationBinder extends BaseDataBind<NoticeConferenceRoomReservationDelegate> {

    public NoticeConferenceRoomReservationBinder(NoticeConferenceRoomReservationDelegate viewDelegate) {
        super(viewDelegate);
    }
    public Disposable conference_getAppointmentInfoList(
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("pageSize",10);
        baseMap.put("pageNumber",pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().conference_getAppointmentInfoList)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("会议室预约")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

}