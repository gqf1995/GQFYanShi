package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticePublicMsgReceiveDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticePublicMsgReceiveBinder extends BaseDataBind<NoticePublicMsgReceiveDelegate> {

    public NoticePublicMsgReceiveBinder(NoticePublicMsgReceiveDelegate viewDelegate) {
        super(viewDelegate);
    }


}