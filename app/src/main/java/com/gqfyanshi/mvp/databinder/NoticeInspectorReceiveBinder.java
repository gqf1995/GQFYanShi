package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.delegate.NoticeInspectorReceiveDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeInspectorReceiveBinder extends BaseDataBind<NoticeInspectorReceiveDelegate> {

    public NoticeInspectorReceiveBinder(NoticeInspectorReceiveDelegate viewDelegate) {
        super(viewDelegate);
    }

    public Disposable overSeer_getOverSeerReceiveList(
            QueryJsonBean queryJsonBean,
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("pageSize", 10);
        baseMap.put("pageNumber", pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().overSeer_getOverSeerReceiveList)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("获取督查通知接收的列表数据")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable overSeer_overSeerDel(
            String pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().overSeer_overSeerDel + "/" + pageNumber)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("督查通知删除")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}