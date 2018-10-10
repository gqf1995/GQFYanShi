package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.DocumentInfoDelegate;
import com.gqfyanshi.server.HttpUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class DocumentInfoBinder extends BaseDataBind<DocumentInfoDelegate> {

    public DocumentInfoBinder(DocumentInfoDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable document_detailDocumnet(
            String id,
            String url,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        baseMap.put("url", url);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().document_detailDocumnet)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("公文详情")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable document_postil(
            String id,
            String img,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("img", new File(img));
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().document_postil)
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

    public Disposable email_emailInfo(
            String id,
            String url,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().email_emailInfo + "/" + url + "-" + id)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("邮件详情")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}