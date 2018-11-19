package com.gqfyanshi.mvp.databinder;

import android.text.TextUtils;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.FileCupboardDelegate;
import com.gqfyanshi.server.HttpUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class FileCupboardBinder extends BaseDataBind<FileCupboardDelegate> {

    public FileCupboardBinder(FileCupboardDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable fileCabinet_getFileList(
            String lastFold,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        if (!TextUtils.isEmpty(lastFold)) {
            lastFold = lastFold.replace("/upload/fileCabinet/", "/");
        }
        baseMap.put("lastFold", lastFold);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().fileCabinet_getFileList)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取文件或文件夹列表")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }


    public Disposable fileCabinet_createFold(
            String lastFold,
            String foldName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        if (!TextUtils.isEmpty(lastFold)) {
            lastFold = lastFold.replace("/upload/fileCabinet/", "/");
        }
        baseMap.put("lastFold", lastFold);
        baseMap.put("foldName", "/" + foldName);
        baseMap.put("sendeeId", "10");
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().fileCabinet_createFold)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存文件夹")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable fileCabinet_delFile(
            String lastFold,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        if (!TextUtils.isEmpty(lastFold)) {
            lastFold = lastFold.replace("/upload/fileCabinet/", "/");
        }
        baseMap.put("lastFold", lastFold);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().fileCabinet_delFile)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("删除文件")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable fileCabinet_upFile(
            String file,
            String lastFold,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        if (!TextUtils.isEmpty(lastFold)) {
            lastFold = lastFold.replace("/upload/fileCabinet/", "/");
        }
        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("file", new File(file));
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().fileCabinet_upFile + "?" + "lastFold" + "=" + lastFold)
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
}