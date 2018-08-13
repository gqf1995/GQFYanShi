package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AddPublicInformationDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AddPublicInformationBinder extends BaseDataBind<AddPublicInformationDelegate> {

    public AddPublicInformationBinder(AddPublicInformationDelegate viewDelegate) {
        super(viewDelegate);
    }


}