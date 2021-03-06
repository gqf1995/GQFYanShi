package com.fivefivelike.mybaselibrary.http;

import android.os.Handler;
import android.os.Message;

import com.dhh.websocket.RxWebSocketUtil;
import com.dhh.websocket.WebSocketInfo;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.yanzhenjie.nohttp.rest.CacheMode;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

/**
 * Created by 郭青枫 on 2018/1/8 0008.
 */

public class WebSocketRequest {
    private TickerWebsocket client;
    private Disposable mDisposable;
    private WebSocket mWebSocket;
    private String mUrl;
    private String REQUEST_TAG = "TickerWebsocket";
    private ConcurrentHashMap<String, WebSocketCallBack> webSocketCallBacks;

    private String oldSend = "";
    private String uid;
    boolean isOpen = false;

    String registerUrl;
    String unregisterUrl;
    Disposable disposable;


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (((String) msg.obj).equals(REQUEST_TAG)) {
                //startSocket();
            }
        }
    };


    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public void setUnregisterUrl(String unregisterUrl) {
        this.unregisterUrl = unregisterUrl;
    }

    public interface WebSocketCallBack {
        void onDataSuccess(String name, String data, String info, int status);

        void onDataError(String name, String data, String info, int status);
    }

    private WebSocketRequest() {
    }

    private static class Helper {
        private static WebSocketRequest webSocketRequest = new WebSocketRequest();
    }

    public static WebSocketRequest getInstance() {
        return Helper.webSocketRequest;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void sendData(List<String> keys) {
        if (keys != null) {
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < keys.size(); i++) {
                stringBuffer.append(",").append(keys.get(i));
            }
            //unregister(oldSend);
            if (disposable != null) {
                disposable.dispose();
            }
            oldSend = stringBuffer.toString();
            register(oldSend);
        }
    }



    private void register(String json) {
        LinkedHashMap baseMap = new LinkedHashMap<>();
        baseMap.put("uid", uid);
        baseMap.put("keys", "" + json + "");
        KLog.i(REQUEST_TAG, "register  " + json);
        disposable = new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(registerUrl)
                .setShowDialog(false)
                .setRequestName("注册web")
                .setCacheMode(CacheMode.ONLY_REQUEST_NETWORK)
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(new RequestCallback() {
                    @Override
                    public void success(int requestCode, String data) {
                        KLog.i(REQUEST_TAG, "success  " + "register");
                        Message message = new Message();
                        message.obj = REQUEST_TAG;
                        handler.removeCallbacksAndMessages(null);//清空消息方便gc回收
                        handler.sendMessageDelayed(message, 3000);
                    }

                    @Override
                    public void error(int requestCode, Throwable exThrowable) {

                    }
                })
                .build()
                .RxSendRequest();
    }


    public void unregister(String json) {
        LinkedHashMap baseMap = new LinkedHashMap<>();
        baseMap.put("uid", uid);
        baseMap.put("keys", "");
        KLog.i(REQUEST_TAG, "unregister  " + uid);
        disposable = new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(unregisterUrl)
                .setShowDialog(false)
                .setRequestName("取消注册web")
                .setRequestMode(HttpRequest.RequestMode.POST)
                .setParameterMode(HttpRequest.ParameterMode.Json)
                .setRequestObj(baseMap)
                .setRequestCallback(new RequestCallback() {
                    @Override
                    public void success(int requestCode, String data) {
                        //取消订阅后 重新订阅新的
                        KLog.i(REQUEST_TAG, "success  " + "unregister");
                        register(oldSend);
                    }

                    @Override
                    public void error(int requestCode, Throwable exThrowable) {

                    }
                })
                .build()
                .RxSendRequest();
    }

    public void addCallBack(String clss, WebSocketCallBack webSocketCallBack) {
        if (webSocketCallBacks != null && !webSocketCallBacks.containsKey(clss)) {
            webSocketCallBacks.put(clss, webSocketCallBack);
        }
    }

    public void remoceCallBack(String clss) {
        if (webSocketCallBacks != null && webSocketCallBacks.containsKey(clss)) {
            if (webSocketCallBacks != null) {
                webSocketCallBacks.remove(clss);
            }
        }
    }

    public void initRxWebsocket(String url, Class clss, WebSocketCallBack webSocketCallBack) {
        //if you want to use your okhttpClient
        OkHttpClient yourClient = new OkHttpClient();
        RxWebSocketUtil.getInstance().setClient(yourClient);
        // show log,default false
        RxWebSocketUtil.getInstance().setShowLog(true);

        webSocketCallBacks = new ConcurrentHashMap<>();
        webSocketCallBacks.put(clss.getName(), webSocketCallBack);
        mUrl = url;
        mDisposable = RxWebSocketUtil.getInstance().getWebSocketInfo(url)
                //bind on life
                .subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在主线程更显UI
                .subscribe(new Consumer<WebSocketInfo>() {
                    @Override
                    public void accept(WebSocketInfo webSocketInfo) throws Exception {
                        mWebSocket = webSocketInfo.getWebSocket();
                        if (webSocketInfo.isOnOpen()) {
                        } else {
                            String string = webSocketInfo.getString();
                            if (string != null) {
                                serviceSuccess(string);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {//onError()
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        //调用失败
                        throwable.printStackTrace();
                        serviceError(throwable);
                    }
                });
    }

    public void intiWebSocket(String url, String name, WebSocketCallBack webSocketCallBack) {
        webSocketCallBacks = new ConcurrentHashMap<>();
        webSocketCallBacks.put(name, webSocketCallBack);
        mUrl = url;
        startSocket();
    }

    private void sendWebsocket(String txt) {
        if (isOpen) {
            if (client != null) {
                if (client.isOpen()) {
                    KLog.i(REQUEST_TAG, "send  " + txt);
                    client.send(txt);
                }
            }
        }
    }

    private void startSocket() {
        isOpen = false;
        //        KLog.i(REQUEST_TAG, "startSocket  " + mUrl);
        client = new TickerWebsocket(mUrl) {
            @Override
            public void onMessage(String message) {
                KLog.i(REQUEST_TAG, "success  " + message);
                isOpen = true;
                if (message.equals("pong")) {

                } else {
                    serviceSuccess(message);
                }
            }

            @Override
            protected void onSubscribe() {

            }

            @Override
            protected void onSchedule(ScheduledExecutorService scheduler) {

            }

            @Override
            protected void onReconnect() {
                isOpen = false;
                Message message = new Message();
                message.obj = REQUEST_TAG;
                handler.sendMessageDelayed(message, 3000);

            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                client.start();
            }
        });
        thread.start();
    }

    private void serviceError(Throwable ex) {
        //websocket链接失败
        KLog.i(REQUEST_TAG, "error  " + ex.getMessage());
        error(ex.getMessage());
    }

    private void serviceSuccess(String msg) {
        //服务器获取成功
        KLog.i(REQUEST_TAG, "success  " + msg);
        success(msg);
    }

    private void success(String msg) {
        //服务器数据 成功
        Iterator iter = webSocketCallBacks.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            KLog.i(REQUEST_TAG, "success 接受名称: " + key + "数据: " + msg);
            WebSocketRequest.WebSocketCallBack webSocketRequest = (WebSocketRequest.WebSocketCallBack) webSocketCallBacks.get(key);
            webSocketRequest.onDataSuccess(key, msg, msg, 0);
        }
    }

    private void error(String msg) {
        //服务器数据 失败
        //KLog.json(RESPONSE_TAG, msg);
        Iterator iter = webSocketCallBacks.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            KLog.i(REQUEST_TAG, "error 接受名称: " + key + "数据: " + msg);
            WebSocketRequest.WebSocketCallBack webSocketRequest = (WebSocketRequest.WebSocketCallBack) webSocketCallBacks.get(key);
            webSocketRequest.onDataError(key, msg, msg, 0);
        }
    }


    public void onDestory() {
        if (mDisposable != null) {
            if (!mDisposable.isDisposed()) {
                mDisposable.dispose();
            }
        } else {
            try {
                if (null != client) {
                    client.closeBlocking();
                }
                KLog.i(REQUEST_TAG, "closeBlocking : ");
            } catch (Exception e) {
                e.printStackTrace();
                KLog.i(REQUEST_TAG, "closeBlocking error: ");
            } finally {
                client = null;
            }
        }
    }

}
