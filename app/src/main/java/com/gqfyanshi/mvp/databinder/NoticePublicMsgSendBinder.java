package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.delegate.NoticePublicMsgSendDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticePublicMsgSendBinder extends BaseDataBind<NoticePublicMsgSendDelegate> {

    public NoticePublicMsgSendBinder(NoticePublicMsgSendDelegate viewDelegate) {
        super(viewDelegate);
    }
    public Disposable workInfo_getWorkInfoSendList(
            QueryJsonBean queryJsonBean,
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("queryJson", queryJsonBean);
        baseMap.put("pageSize", 10);
        baseMap.put("pageNumber", pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().workInfo_getWorkInfoSendList)
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