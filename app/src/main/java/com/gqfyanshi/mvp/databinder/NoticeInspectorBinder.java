package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeInspectorDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeInspectorBinder extends BaseDataBind<NoticeInspectorDelegate> {

    public NoticeInspectorBinder(NoticeInspectorDelegate viewDelegate) {
        super(viewDelegate);
    }


}