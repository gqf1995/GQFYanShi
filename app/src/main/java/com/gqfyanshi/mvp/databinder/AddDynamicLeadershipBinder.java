package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AddDynamicLeadershipDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AddDynamicLeadershipBinder extends BaseDataBind<AddDynamicLeadershipDelegate> {

    public AddDynamicLeadershipBinder(AddDynamicLeadershipDelegate viewDelegate) {
        super(viewDelegate);
    }


}