package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.ApprovalDelegate;
import com.gqfyanshi.server.HttpUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class ApprovalBinder extends BaseDataBind<ApprovalDelegate> {

    public ApprovalBinder(ApprovalDelegate viewDelegate) {
        super(viewDelegate);
    }

    /*
    {
	"name":"文件字号",
	"title":"文件标题",
	"sendeeId":"1,2,3,4,5,6",
	"priority":"紧急情况",
	"security":"密级",
	"scope":"发送范围 ",
	"sponsor":"主办单位",
	"draftDoc":"拟稿",
	"verifyDoc":"核稿",
	"opinion":"拟办意见",
	"verifySend":"核发",
	"counterSign":"会签",
	"status":"02"
}
     */
    public Disposable fileSign_saveFileSign(
            String name,
            String title,
            String sendeeId,
            String priority,
            String security,
            String scope,
            String sponsor,
            String draftDoc,
            String verifyDoc,
            String opinion,
            String verifySend,
            String counterSign,
            String status,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("name", name);
        baseMap.put("title", title);
        baseMap.put("sendeeId", sendeeId);
        baseMap.put("priority", priority);
        baseMap.put("security", security);
        baseMap.put("scope", scope);
        baseMap.put("sponsor", sponsor);
        baseMap.put("draftDoc", draftDoc);
        baseMap.put("verifyDoc", verifyDoc);
        baseMap.put("opinion", opinion);
        baseMap.put("verifySend", verifySend);
        baseMap.put("counterSign", counterSign);
        baseMap.put("status", status);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().fileSign_saveFileSign)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("文件签批保存")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable fileSign_saveFileSign(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().fileSign_saveFileSign)
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