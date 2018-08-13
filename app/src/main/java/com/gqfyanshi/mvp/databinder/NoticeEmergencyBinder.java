package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeEmergencyDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeEmergencyBinder extends BaseDataBind<NoticeEmergencyDelegate> {

    public NoticeEmergencyBinder(NoticeEmergencyDelegate viewDelegate) {
        super(viewDelegate);
    }


}