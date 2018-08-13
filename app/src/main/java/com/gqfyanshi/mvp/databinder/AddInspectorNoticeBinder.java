package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.AddInspectorNoticeDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class AddInspectorNoticeBinder extends BaseDataBind<AddInspectorNoticeDelegate> {

    public AddInspectorNoticeBinder(AddInspectorNoticeDelegate viewDelegate) {
        super(viewDelegate);
    }


}