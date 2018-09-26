package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.NoticeMgsStatisticalDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeMgsStatisticalBinder extends BaseDataBind<NoticeMgsStatisticalDelegate> {

    public NoticeMgsStatisticalBinder(NoticeMgsStatisticalDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable infoNotice_sendList(
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("pageSize", 10);
        baseMap.put("pageNumber", pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().infoNotice_sendList)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("信息统计")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}