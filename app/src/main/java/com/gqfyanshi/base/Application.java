package com.gqfyanshi.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;

import com.fivefivelike.mybaselibrary.base.BaseApp;
import com.fivefivelike.mybaselibrary.http.WebSocketRequest;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.utils.glide.GlideAlbumLoader;
import com.fivefivelike.mybaselibrary.utils.logger.KLog;
import com.gqfyanshi.entity.event.StartPageEventBus;
import com.gqfyanshi.utils.https.SSLContextUtil;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import javax.net.ssl.SSLContext;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;



/**
 * Created by 郭青枫 on 2017/9/25.
 */

public class Application extends BaseApp  {

    boolean isInitUm = false;

    @Override
    public void onCreate() {
        super.onCreate();
        //融云初始化
        if (isMainProcess()) {
            initClient();
        }
    }



    private void initSkin() {
        SkinCompatManager.withoutActivity(GlobleContext.getInstance().getApplicationContext())                         // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(true)                  // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
    }



    private void initClient() {
        //客户端进程中初始化操作
        if (isMainProcess()) {
            EventBus.getDefault().register(this);
            //initNohttp();
            //初始化数据库

            initNohttp();
            //开启log日志
            KLog.init(AppConst.isLog);
            //英文切换
            Configuration configuration = getResources().getConfiguration();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (!AppConst.isChinese) {
                configuration.locale = Locale.ENGLISH;
            }
            configuration.fontScale = 1;
            //更新配置
            getResources().updateConfiguration(configuration, displayMetrics);

            //初始化融云
            //initRongCloud();
            //初始化换肤
            initSkin();

        }
    }

    private void initNohttp() {
        SSLContext sslContext = SSLContextUtil.getSSLContext();
        //SSLSocketFactory socketFactory = sslContext.getSocketFactory();
        //修复在Android4.x系统中不支持TLSv1.1、TLSv1.2协议的问题
        //socketFactory = SSLUtils.fixSSLLowerThanLollipop(socketFactory);
        InitializationConfig config = InitializationConfig.newBuilder(this)
                // 全局连接服务器超时时间，单位毫秒，默认10s。
                .connectionTimeout(30 * 1000)
                // 全局等待服务器响应超时时间，单位毫秒，默认10s。
                .readTimeout(30 * 1000)
                // 配置缓存，默认保存数据库DBCacheStore，保存到SD卡使用DiskCacheStore。
                .cacheStore(
                        // 如果不使用缓存，setEnable(false)禁用。
                        new DBCacheStore(this).setEnable(false)
                )
                // 配置Cookie，默认保存数据库DBCookieStore，开发者可以自己实现CookieStore接口。
                .cookieStore(
                        // 如果不维护cookie，setEnable(false)禁用。
                        new DBCookieStore(this).setEnable(false)
                )
                // 配置网络层，默认URLConnectionNetworkExecutor，如果想用OkHttp：OkHttpNetworkExecutor。
                .networkExecutor(new OkHttpNetworkExecutor())
                //.sslSocketFactory(AppConst.isSSL ? socketFactory : null)
                .retry(1) // 全局重试次数，配置后每个请求失败都会重试x次。
                .build();
        Logger.setDebug(false);
        Logger.setTag("NoHttpSample");// 打印Log的tag。
        NoHttp.initialize(config);
    }


    public static final String START_APP = "startApp";
    public static final String INITED_APP = "initedApp";

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(StartPageEventBus event) {
        if (event.getMsg() != null && START_APP.equals(event.getMsg())) {
            Log.i("eventlog", "进行初始化操作");


            //设置配置画廊可以加载网络图片
            Album.initialize(
                    AlbumConfig.newBuilder(this)
                            .setAlbumLoader(new GlideAlbumLoader()) // 设置Album加载器。
                            .setLocale(Locale.CHINA) // 比如强制设置在任何语言下都用中文显示。
                            .build()
            );
            //初始化完成以后发出消息在启动页接收，通知启动页可以进入主页面了，然后进行eventbus解绑
            EventBus.getDefault().post(new StartPageEventBus(INITED_APP));
            EventBus.getDefault().unregister(this);
        }
    }


    //客户服务
    public void startCustomerService(Activity activity) {
        //        if (SingSettingDBUtil.getUserLogin() != null) {
        //            CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        //            CSCustomServiceInfo csInfo = csBuilder.nickName(SingSettingDBUtil.getUserLogin().getNickName()).build();
        //            RongIM.getInstance().startCustomerServiceChat(activity, "KEFU151728371459995", "客服中心", csInfo);
        //        } else {
        //            ToastUtil.show(CommonUtils.getString(R.string.str_toast_need_login));
        //        }
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        WebSocketRequest.getInstance().onDestory();
        super.onTerminate();
    }

    public Class getLoginActivityClass() {
        return null;
    }



}
