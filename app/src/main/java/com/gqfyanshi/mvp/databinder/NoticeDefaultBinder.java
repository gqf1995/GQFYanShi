package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.delegate.NoticeDefaultDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class NoticeDefaultBinder extends BaseDataBind<NoticeDefaultDelegate> {

    public NoticeDefaultBinder(NoticeDefaultDelegate viewDelegate) {
        super(viewDelegate);
    }

    /**
     * {
     "pageSize":10,
     "pageNumber":1,
     "queryJson":{
     "title":"",
     "createtime":"",
     "updatetime":""
     }
     }
     * @param pageNumber
     * @param requestCallback
     * @return
     */
    public Disposable notice_sendList(
            QueryJsonBean queryJsonBean,
            int pageNumber,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("queryJson",queryJsonBean);
        baseMap.put("pageSize",10);
        baseMap.put("pageNumber",pageNumber);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().notice_sendList)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("会议通知信息列表")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}