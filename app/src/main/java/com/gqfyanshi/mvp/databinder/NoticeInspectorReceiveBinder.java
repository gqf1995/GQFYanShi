package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeInspectorReceiveDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeInspectorReceiveBinder extends BaseDataBind<NoticeInspectorReceiveDelegate> {

    public NoticeInspectorReceiveBinder(NoticeInspectorReceiveDelegate viewDelegate) {
        super(viewDelegate);
    }


}