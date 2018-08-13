package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.MsgDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class MsgBinder extends BaseDataBind<MsgDelegate> {

    public MsgBinder(MsgDelegate viewDelegate) {
        super(viewDelegate);
    }


}