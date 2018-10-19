package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.AskLeaveDelegate;
import com.gqfyanshi.server.HttpUrl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class AskLeaveBinder extends BaseDataBind<AskLeaveDelegate> {

    public AskLeaveBinder(AskLeaveDelegate viewDelegate) {
        super(viewDelegate);
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

    /**
     * {
     * "department":"单位名称",
     * "startTime":"2018-10-16 00:00:00",
     * "endTime":"2018-10-18 00:00:00",
     * "outLName":"外出领导姓名",
     * "outLPosition":"外出领导职位",
     * "outLPhoneNum":"外出领导手机号",
     * "managerName":"主管领导姓名",
     * "managerPosition":"主管领导职位",
     * "managerPhoneNum":"主管领导手机号",
     * "reason":"请假缘由",
     * "remark":"备注"
     * }
     */
    public Disposable leave_saveLeave(
            String department,
            String startTime,
            String endTime,
            String outLName,
            String outLPosition,
            String outLPhoneNum,
            String managerName,
            String managerPosition,
            String managerPhoneNum,
            String reason,
            String remark,
            String sendeeGroupId,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", department);
        baseMap.put("startTime", startTime);
        baseMap.put("endTime", endTime);
        baseMap.put("outLName", outLName);
        baseMap.put("outLPosition", outLPosition);
        baseMap.put("outLPhoneNum", outLPhoneNum);
        baseMap.put("managerName", managerName);
        baseMap.put("managerPosition", managerPosition);
        baseMap.put("managerPhoneNum", managerPhoneNum);
        baseMap.put("reason", reason);
        baseMap.put("remark", remark);
        baseMap.put("sendeeGroupId", sendeeGroupId);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().leave_saveLeave)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("请假")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }

}