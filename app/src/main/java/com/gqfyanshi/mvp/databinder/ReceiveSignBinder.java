package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.ReceiveSignDelegate;
import com.gqfyanshi.server.HttpUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class ReceiveSignBinder extends BaseDataBind<ReceiveSignDelegate> {

    public ReceiveSignBinder(ReceiveSignDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable fileSign_detailFileSign(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().fileSign_detailFileSign)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("文件签批详情")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable document_saveFile(
            String file,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("file", new File(file));
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().document_saveFile)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("上传文件")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setFileMap(fileMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable fileSign_delPostil(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x131)
                .setRequestUrl(HttpUrl.getIntance().fileSign_delPostil)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("删除签批信息")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable fileSign_postil(
            String id,
            String img,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("img", new File(img));
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().fileSign_postil)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("签批")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setFileMap(fileMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}