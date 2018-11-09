package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.AddSharerDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class AddSharerBinder extends BaseDataBind<AddSharerDelegate> {

    public AddSharerBinder(AddSharerDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable leave_getUserTree(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x130)
                .setRequestUrl(HttpUrl.getIntance().leave_getUserTree)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取部门人员树型对象")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable fileCabinet_editFoldSeedeeId(
            String foldPath,
            String sendeeId,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("foldPath",foldPath);
        baseMap.put("sendeeId",sendeeId);
        return new HttpRequest.Builder()
                .setRequestCode(0x131)
                .setRequestUrl(HttpUrl.getIntance().fileCabinet_editFoldSeedeeId)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存共享成员")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}