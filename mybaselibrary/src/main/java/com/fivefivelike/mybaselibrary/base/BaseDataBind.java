package com.fivefivelike.mybaselibrary.base;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.entity.ResultDialogEntity;
import com.fivefivelike.mybaselibrary.http.ServiceDataCallback;
import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.mvp.view.IDelegate;
import com.fivefivelike.mybaselibrary.utils.ActUtil;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.fivefivelike.mybaselibrary.view.dialog.ResultDialog;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 郭青枫 on 2017/7/3.
 */

public abstract class BaseDataBind<T extends IDelegate> implements IDataBind<T> {
    protected T viewDelegate;
    protected Map<String, Object> baseMap;
    protected CompositeDisposable compositeDisposable;
    public static final int JsonErrorStatu = 110;
    public static final int viewErrorStatu = 120;


    public enum StopNetMode {
        /**
         * 成功请求并加载
         */
        NET_SUCCESS,
        /**
         * 服务端报告错误
         */
        NET_ERROR,
        /**
         * 404 或者 服务端传值 json不规范
         */
        JSON_ERROR,
        /**
         * 请求错误
         */
        ERROR,
        /**
         * 显示错误
         */
        VIEW_ERROR

    }

    public Map<String, Object> getMap() {
        return baseMap;
    }

    public BaseDataBind(T viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    /**
     * 添加订阅
     *
     * @param disposable
     */
    public void addRequest(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 取消订阅
     */
    public void cancelpost() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public boolean isMissToken(int status) {
        //需要重新登录的 错误码
        return status == 4444 || status == 4445;
    }

    public void loginAgain(FragmentActivity activity) {
        //重新登录
        Intent intent = new Intent(activity, BaseApp.getInstance().getLoginActivityClass());
        ActUtil.getInstance().killAllActivity(activity);
        activity.startActivity(intent);
        activity.finish();
    }

    public void showError(Throwable exThrowable) {
        // 提示异常信息。
        if (exThrowable instanceof NetworkError) {// 网络不好
            //ToastUtil.show("网络不好");
        } else if (exThrowable instanceof TimeoutError) {// 请求超时
            ToastUtil.show("请求超时");
        } else if (exThrowable instanceof UnKnownHostError) {// 找不到服务器
            //ToastUtil.show("找不到服务器");
        } else if (exThrowable instanceof URLError) {// 找不到服务器
            // ToastUtil.show("找不到服务器");
        } else if (exThrowable instanceof NotFoundCacheError) {
            //ToastUtil.show("没有缓存");
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
        } else if (exThrowable instanceof ProtocolException) {
            //ToastUtil.show("系统不支持");
        } else if (exThrowable instanceof JSONException) {
            //ToastUtil.show("返回数据格式错误");
        } else {
            //ToastUtil.show("未知错误" + exThrowable.getMessage());
        }
    }

    public void onDialogBtnClick(FragmentActivity activity, View view, int position, Object item) {
        if (position == ResultDialog.CONFIRM_POSITION) {
            EventBus.getDefault().post(((ResultDialogEntity) item));
        } else if (position == ResultDialog.CANNEL_POSITION) {
            if (((ResultDialogEntity) item).isCancelAndClose()) {
                activity.onBackPressed();
            }
        }
    }

    public void success(FragmentActivity activity, ServiceDataCallback serviceDataCallback, DefaultClickLinsener defaultClickLinsener, int requestCode, String jsonData) {
        String info = "";
        int status = -1;
        String data = "";
        KLog.i(this.getClass().getName(), "请求数据: " + jsonData);
        try {
            JSONObject object = new JSONObject(jsonData);
            info = object.getString(ResultDialog.DIALOG_KEY);
            status = object.getInt("code");
            data = object.getString("data");

        } catch (JSONException e) {
            e.printStackTrace();
            showError(e);
            serviceDataCallback.onDataError(e.toString(), "", JsonErrorStatu, requestCode);
            return;
        }
        try {
            if (status == 0) {
                serviceDataCallback.onDataSuccess(data, info, status, requestCode);
            } else {
                serviceDataCallback.onDataError(data, info, status, requestCode);
            }
            if (!TextUtils.isEmpty(info)) {
                ResultDialogEntity resultDialogEntity = ResultDialog.getInstence().ShowResultDialog(activity, info, defaultClickLinsener);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError(e);
            if (e instanceof JSONException) {
                serviceDataCallback.onDataError(e.toString(), info, JsonErrorStatu, requestCode);
            } else {
                serviceDataCallback.onDataError(e.toString(), info, viewErrorStatu, requestCode);
            }
        }
    }


    protected Map<String, Object> getBaseMap() {
        baseMap = new LinkedHashMap<>();
        return baseMap;
    }

    public Map<String, Object> getBaseMapWithUid() {
        getBaseMap();
        String token = SaveUtil.getInstance().getString("uid");
        baseMap.put("userId", token);
        KLog.i("getBaseMapWithUid", "userId" + token);

        //        String language = SaveUtil.getInstance().getString("language");
        //        if (TextUtils.isEmpty(language)) {
        //            language = "zh-cn";
        //        }
        //        baseMap.put("language", language);
        return baseMap;
    }

    public void put(String key, Object val) {
        if (baseMap == null) {
            getBaseMapWithUid();
        }
        baseMap.put(key, val);
    }

}
