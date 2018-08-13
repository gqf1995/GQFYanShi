package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticePublicMsgSendDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticePublicMsgSendBinder extends BaseDataBind<NoticePublicMsgSendDelegate> {

    public NoticePublicMsgSendBinder(NoticePublicMsgSendDelegate viewDelegate) {
        super(viewDelegate);
    }


}