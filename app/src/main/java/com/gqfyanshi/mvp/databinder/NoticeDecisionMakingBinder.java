package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeDecisionMakingDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeDecisionMakingBinder extends BaseDataBind<NoticeDecisionMakingDelegate> {

    public NoticeDecisionMakingBinder(NoticeDecisionMakingDelegate viewDelegate) {
        super(viewDelegate);
    }


}