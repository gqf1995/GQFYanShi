package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeMgsStatisticalDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeMgsStatisticalBinder extends BaseDataBind<NoticeMgsStatisticalDelegate> {

    public NoticeMgsStatisticalBinder(NoticeMgsStatisticalDelegate viewDelegate) {
        super(viewDelegate);
    }


}