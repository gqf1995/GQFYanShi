package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.NoticeAddressBookDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeAddressBookBinder extends BaseDataBind<NoticeAddressBookDelegate> {

    public NoticeAddressBookBinder(NoticeAddressBookDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable addressBook_getAddressBookList(
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("pageSize",10);
        baseMap.put("pageNumber",pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().addressBook_getAddressBookList)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取电子通讯录列表数据")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}