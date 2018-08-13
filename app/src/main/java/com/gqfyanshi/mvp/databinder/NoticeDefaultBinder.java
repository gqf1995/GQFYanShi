package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeDefaultDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeDefaultBinder extends BaseDataBind<NoticeDefaultDelegate> {

    public NoticeDefaultBinder(NoticeDefaultDelegate viewDelegate) {
        super(viewDelegate);
    }


}