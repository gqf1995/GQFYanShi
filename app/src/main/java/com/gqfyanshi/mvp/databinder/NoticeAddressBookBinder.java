package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeAddressBookDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeAddressBookBinder extends BaseDataBind<NoticeAddressBookDelegate> {

    public NoticeAddressBookBinder(NoticeAddressBookDelegate viewDelegate) {
        super(viewDelegate);
    }


}