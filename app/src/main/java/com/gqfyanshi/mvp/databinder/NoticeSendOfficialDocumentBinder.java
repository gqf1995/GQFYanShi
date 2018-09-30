package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.delegate.NoticeSendOfficialDocumentDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeSendOfficialDocumentBinder extends BaseDataBind<NoticeSendOfficialDocumentDelegate> {

    public NoticeSendOfficialDocumentBinder(NoticeSendOfficialDocumentDelegate viewDelegate) {
        super(viewDelegate);
    }

    //        文件字号： name
    //        文件名称：title
    //        发布时间：createtime/updatetime
    //        文件属性：type （01 党委 02 党群口 03 政府文件 04 政府部门文件）
    public Disposable document_sendList(
            QueryJsonBean queryJson,
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("queryJson", queryJson);
        baseMap.put("pageSize", 10);
        baseMap.put("pageNumber", pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().document_sendList)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("公文发送")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable document_receiveList(
            QueryJsonBean queryJson,
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("queryJson", queryJson);
        baseMap.put("pageSize", 10);
        baseMap.put("pageNumber", pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().document_receiveList)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("公文接收")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

    public Disposable document_delDocument(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().document_delDocument + "/" + id)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("删除公文")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

}