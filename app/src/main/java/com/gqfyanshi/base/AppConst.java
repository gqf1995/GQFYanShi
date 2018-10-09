package com.gqfyanshi.base;

/**
 * 全局配置
 */
public class AppConst {


    //http://47.98.56.206:1913/symbol/info?name=testCoin.
    public static final String app2BaseUrl = "http://47.92.164.4:8081/municipal";//"http://47.104.160.61";//BuildConfig.app2BaseUrl;// "http://tblz.bicoin.com.cn";
    public static final String serviceId = "";//BuildConfig.serviceId;// "KEFU151728371459995";
    public static final String wsAddress ="";// BuildConfig.wsAddress;// "ws:" + "//ws.blz.bicoin.com.cn/ws/";
    public static final String rongId = "";//BuildConfig.rongId;// "cpj2xarlc1xsn";
    public static final boolean isLog = true;//BuildConfig.isLog;
    public static final boolean isEditUrl = true;//BuildConfig.isEditUrl;
    public static final boolean isSSL = false;//BuildConfig.isSSL;


    public static final String pushId = "";//BuildConfig.pushId;// "5afcd70bf43e483b6900038c";
    public static final String pushSecret = "";//BuildConfig.pushSecret;//"5a7d1aef606a29e7fd89a2db8511a875";
    public static final String xiaoMiPushId = "";//BuildConfig.XiaoMiPushId;// "5afcd70bf43e483b6900038c";
    public static final String xiaoMiPushKey = "";//BuildConfig.XiaoMiPushKey;//"5a7d1aef606a29e7fd89a2db8511a875";
    public static final String meizuAppId = "114551";// BuildConfig.meizuAppId;//"5a7d1aef606a29e7fd89a2db8511a875";
    public static final String meizuAppKey = "43364598f1ba4abea7cfc376247e4862";// BuildConfig.meizuAppKey;//"5a7d1aef606a29e7fd89a2db8511a875";


    public static final String CACHE_EXCHANGENAME = "cache_exchangeName";//交易所 名称缓存
    public static final String CACHE_EXCHANGE_RATE = "cache_exchange_rate";//汇率缓存
    public static final String CACHE_KLINE = "cache_Kline";//
    public static final String CACHE_CHOOSE = "cache_choose";//用户自选 onlykey
    public static final String CACHE_SEARCH_HISTORY = "cache_search_history";//搜索历史
    public static final String CACHE_CUSTOM_RATE = "cache_custom_rate";//自定义汇率
    public static final String CACHE_COIN_INFO = "cache_coin_info";//币种资料页面
    public static final String rulesUrl = "http://rule.bicoin.com.cn";
    public static final String myRewardUrl = "http://rule.bicoin.com.cn/35/16/p501606933c9693";

    public static final String httpsCer = "blz.cer";
    public static final String umS1 = "Bradar";
    public static final String aliasS1 = "bradar";
    public static final String packageName = "com.temperaturecoin";
    public static final String apkName = "bradar.apk";
    public static final boolean isChinese = true;
    public static final String webAddress = isChinese ? "/resource" : "/i18n";

    private static final String webUrl = "http://116.62.232.175:3002";
    public static final String feedbackUrl = webUrl + "/modules/serviceMessage.html#/feedback";
    public static final String communityUrl = webUrl + "/modules/serviceMessage.html#/community";
    public static final String currencyUrl = webUrl + "/modules/serviceMessage.html#/currency";
    public static final String regUrl = webUrl + "/modules/notice.html#/reg";
    public static final String aboutvipUrl = "http://app.bicoin.com.cn/web/aboutvip.html";
    public static final String userruleUrl = "http://app.bicoin.com.cn/web/userrule.html";
    public static final String aboutprivacyUrl = "http://app.bicoin.com.cn/web/aboutprivacy.html";

}
