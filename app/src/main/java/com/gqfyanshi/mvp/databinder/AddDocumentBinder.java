package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.AddDocumentDelegate;
import com.gqfyanshi.server.HttpUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class AddDocumentBinder extends BaseDataBind<AddDocumentDelegate> {

    public AddDocumentBinder(AddDocumentDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable notice_sendList(
            String name,
            String title,
            String type,
            String sendeeGroupId,
            String img,
            String content,
            String modelId,
            String fileName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("name", name);
        baseMap.put("title", title);
        baseMap.put("type", type);
        baseMap.put("sendeeGroupId", sendeeGroupId);
        baseMap.put("img", img);
        baseMap.put("content", content);
        baseMap.put("modelId", modelId);
        baseMap.put("fileAddress", img);
        baseMap.put("fileName", fileName);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().document_saveDocument)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存文章")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable email_emailForm(
            String name,
            String title,
            String sendeeGroupId,
            String img,
            String content,
            String fileName,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("name", name);
        baseMap.put("title", title);
        baseMap.put("sendeeGroupId", sendeeGroupId);
        baseMap.put("img", img);
        baseMap.put("fileAddress", img);
        baseMap.put("content", content);
        baseMap.put("fileName", fileName);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().email_emailForm)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("保存文章")
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
                .setRequestCode(0x123)
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

    public Disposable leader_getModelTree(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().leader_getModelTree)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取部门信息树型对象")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}