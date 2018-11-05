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

    public Disposable leave_postil(
            String id,
            String img,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        Map<String, Object> fileMap = new HashMap<>();
        fileMap.put("img", new File(img));
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().leave_postil)
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
    public Disposable leave_detailLeave(
            String id,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("id", id);
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(HttpUrl.getIntance().leave_detailLeave)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("请假详情")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable leave_getLeaveType(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().leave_getLeaveType)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("请假类型")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
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
            String type,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("department", department);
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
        baseMap.put("type", type);
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