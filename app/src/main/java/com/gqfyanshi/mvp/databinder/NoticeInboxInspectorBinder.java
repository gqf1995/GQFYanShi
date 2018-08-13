package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeInboxInspectorDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeInboxInspectorBinder extends BaseDataBind<NoticeInboxInspectorDelegate> {

    public NoticeInboxInspectorBinder(NoticeInboxInspectorDelegate viewDelegate) {
        super(viewDelegate);
    }


}