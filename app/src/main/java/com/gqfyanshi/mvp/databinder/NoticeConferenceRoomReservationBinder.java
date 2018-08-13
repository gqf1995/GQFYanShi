package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeConferenceRoomReservationDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeConferenceRoomReservationBinder extends BaseDataBind<NoticeConferenceRoomReservationDelegate> {

    public NoticeConferenceRoomReservationBinder(NoticeConferenceRoomReservationDelegate viewDelegate) {
        super(viewDelegate);
    }


}