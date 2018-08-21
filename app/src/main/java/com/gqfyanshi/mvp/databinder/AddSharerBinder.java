package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AddSharerDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AddSharerBinder extends BaseDataBind<AddSharerDelegate> {

    public AddSharerBinder(AddSharerDelegate viewDelegate) {
        super(viewDelegate);
    }


}