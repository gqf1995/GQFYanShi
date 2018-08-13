package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeInboxDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeInboxBinder extends BaseDataBind<NoticeInboxDelegate> {

    public NoticeInboxBinder(NoticeInboxDelegate viewDelegate) {
        super(viewDelegate);
    }


}