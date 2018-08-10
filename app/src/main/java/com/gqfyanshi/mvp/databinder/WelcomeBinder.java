package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.WelcomeDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class WelcomeBinder extends BaseDataBind<WelcomeDelegate> {

    public WelcomeBinder(WelcomeDelegate viewDelegate) {
        super(viewDelegate);
    }


}