package com.gqfyanshi.server;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.gqfyanshi.base.AppConst;

/**
 * Created by 郭青枫 on 2018/1/10 0010.
 */

public class HttpUrl {

    static HttpUrl httpUrl = new HttpUrl();

    public static HttpUrl getIntance() {
        if (httpUrl == null) {
            httpUrl = new HttpUrl();
        }
        return httpUrl;
    }

    public String getUid() {
        return SaveUtil.getInstance().getString("uid");
    }


    /**
     * 登录
     */
    public String doLogin = AppConst.app2BaseUrl + "/doLogin";
    /**
     * 获取验证码
     */
    public String pictureCheckCode = AppConst.app2BaseUrl + "/pictureCheckCode";
    /**
     * 发送手机短信验证码
     */
    public String sendPhoneCode = AppConst.app2BaseUrl + "/sendPhoneCode";
    /**
     * 登陆后获取菜单
     */
    public String getLoginedUserInfo = AppConst.app2BaseUrl + "/getLoginedUserInfo";
    /**
     * 约稿发送列表
     */
    public String conventional_sendList = AppConst.app2BaseUrl + "/conventional/sendList";


}
