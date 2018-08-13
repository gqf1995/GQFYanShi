package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AddDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AddBinder extends BaseDataBind<AddDelegate> {

    public AddBinder(AddDelegate viewDelegate) {
        super(viewDelegate);
    }


}