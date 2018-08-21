package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AskForLeaveDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AskForLeaveBinder extends BaseDataBind<AskForLeaveDelegate> {

    public AskForLeaveBinder(AskForLeaveDelegate viewDelegate) {
        super(viewDelegate);
    }


}