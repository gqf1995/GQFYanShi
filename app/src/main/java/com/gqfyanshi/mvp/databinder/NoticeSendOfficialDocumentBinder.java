package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.NoticeSendOfficialDocumentDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeSendOfficialDocumentBinder extends BaseDataBind<NoticeSendOfficialDocumentDelegate> {

    public NoticeSendOfficialDocumentBinder(NoticeSendOfficialDocumentDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable document_sendList(
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("pageSize",10);
        baseMap.put("pageNumber",pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().document_sendList)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("公文发送")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}