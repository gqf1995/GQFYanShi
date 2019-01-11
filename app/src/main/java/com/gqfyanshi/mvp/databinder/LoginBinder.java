package com.gqfyanshi.mvp.databinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.gqfyanshi.mvp.delegate.LoginDelegate;
import com.gqfyanshi.server.HttpUrl;

import io.reactivex.disposables.Disposable;

public class LoginBinder extends BaseDataBind<LoginDelegate> {

    public LoginBinder(LoginDelegate viewDelegate) {
        super(viewDelegate);
    }

//    public Disposable pictureCheckCode(
//            RequestCallback requestCallback) {
//        getBaseMapWithUid();
//        return new HttpRequest.Builder()
//                .setRequestCode(0x123)
//                .setRequestUrl(HttpUrl.getIntance().pictureCheckCode)
//                .setShowDialog(false)
//                .setDialog(viewDelegate.getNetConnectDialog())
//                .setRequestName("获取验证码")
//                .setRequestMode(HttpRequest.RequestMode.GET)
//                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
//                .setRequestObj(baseMap)
//                .setRequestCallback(requestCallback)
//                .build()
//                .RxSendRequest();
//    }

    public Disposable doLogin(
            String phoneNum,
            String randomCode,
            String loginNum,
            String uuIdkeys,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phoneNum", phoneNum);
        baseMap.put("randomCode", randomCode);
        baseMap.put("loginNum",loginNum);
        baseMap.put("uuIdkeys",uuIdkeys);
        return new HttpRequest.Builder()
                .setRequestCode(0x124)
                .setRequestUrl(HttpUrl.getIntance().doLogin)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("登录")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable pictureCheckCode(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().pictureCheckCode)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("登录")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable sendPhoneCode(
            String phoneNum,
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        baseMap.put("phoneNum", phoneNum);
        return new HttpRequest.Builder()
                .setRequestCode(0x125)
                .setRequestUrl(HttpUrl.getIntance().sendPhoneCode)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("发送手机短信验证码")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
    public Disposable getAppVersion(
            RequestCallback requestCallback) {
        getBaseMapWithUid();
        return new HttpRequest.Builder()
                .setRequestCode(0x126)
                .setRequestUrl(HttpUrl.getIntance().getAppVersion)
                .setShowDialog(false)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestName("版本更新")
                .setRequestMode(HttpRequest.RequestMode.GET)
                .setParameterMode(HttpRequest.ParameterMode.KeyValue)
                .setRequestObj(baseMap)
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}