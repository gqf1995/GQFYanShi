package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.SendSignDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class SendSignBinder extends BaseDataBind<SendSignDelegate> {

    public SendSignBinder(SendSignDelegate viewDelegate) {
        super(viewDelegate);
    }


}