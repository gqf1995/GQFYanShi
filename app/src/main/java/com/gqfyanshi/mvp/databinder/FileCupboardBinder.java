package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.FileCupboardDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class FileCupboardBinder extends BaseDataBind<FileCupboardDelegate> {

    public FileCupboardBinder(FileCupboardDelegate viewDelegate) {
        super(viewDelegate);
    }


}