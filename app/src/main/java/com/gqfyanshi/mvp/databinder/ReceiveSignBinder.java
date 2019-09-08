package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.ReceiveSignDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class ReceiveSignBinder extends BaseDataBind<ReceiveSignDelegate> {

    public ReceiveSignBinder(ReceiveSignDelegate viewDelegate) {
        super(viewDelegate);
    }


}