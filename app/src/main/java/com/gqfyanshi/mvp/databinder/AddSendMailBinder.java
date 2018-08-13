package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AddSendMailDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AddSendMailBinder extends BaseDataBind<AddSendMailDelegate> {

    public AddSendMailBinder(AddSendMailDelegate viewDelegate) {
        super(viewDelegate);
    }


}