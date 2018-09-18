package com.gqfyanshi.entity.bean;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.gqfyanshi.base.Application;

/**
 * Created by 郭青枫 on 2018/9/18 0018.
 */

public class UserLoginBean {
    /**
     * user_division : 大总管
     * image : null
     * createtime : 2017-07-20 10:20:34
     * department_id : 10
     * user_position : 超级管理员
     * sort : 2
     * isAdmin : true
     * password : 123456789
     * name : 人大办
     * imageAddress : null
     * phone_num : 15896559159
     * login_num : null
     * id : 10
     * user_rank : 01
     * updatetime : 2018-09-04 16:57:54
     */


    public static boolean isLogin() {
        boolean isLogin = isLoginNoToast();
        if (isLogin) {
            return true;
        } else {
            ToastUtil.show("登陆后才能使用此功能");
            return false;
        }
    }

    public static boolean isLoginAndToLogin(Context context) {
        boolean isLogin = isLoginNoToast();
        if (isLogin) {
            return true;
        } else {
            context.startActivity(new Intent(context, Application.getInstance().getLoginActivityClass()));
            return false;
        }
    }

    public static boolean isLoginNoToast() {
        boolean isLogin = SaveUtil.getInstance().getBoolean("UserLoginInfo_isLogin");
        if (!isLogin) {
            logout();
        } else {
            SaveUtil.getInstance().saveString("UserLoginInfo_no_login_id", "");
        }
        return isLogin;
    }

    private String user_division;
    private String image;
    private String createtime;
    private int department_id;
    private String user_position;
    private int sort;
    private boolean isAdmin;
    private String password;
    private String name;
    private String imageAddress;
    private String phone_num;
    private String login_num;
    private long id;
    private String user_rank;
    private String updatetime;

    public static void addNewLoginInfo(UserLoginBean userLoginInfo) {
        SaveUtil.getInstance().saveString("UserLoginInfo_id", userLoginInfo.getId() + "");
        SaveUtil.getInstance().saveString("uid", userLoginInfo.getId() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_user_division", userLoginInfo.getUser_division() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_image", userLoginInfo.getImage() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_createtime", userLoginInfo.getCreatetime() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_user_position", userLoginInfo.getUser_position() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_sort", userLoginInfo.getSort() + "");
        SaveUtil.getInstance().saveBoolean("UserLoginInfo_isAdmin", userLoginInfo.isAdmin());
        SaveUtil.getInstance().saveString("UserLoginInfo_password", userLoginInfo.getPassword() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_name", userLoginInfo.getName() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_imageAddress", userLoginInfo.getImageAddress() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_phone_num", userLoginInfo.getPhone_num() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_login_num", userLoginInfo.getLogin_num() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_user_rank", userLoginInfo.getUser_rank() + "");
        SaveUtil.getInstance().saveString("UserLoginInfo_updatetime", userLoginInfo.getUpdatetime() + "");
        SaveUtil.getInstance().saveBoolean("UserLoginInfo_isLogin", !TextUtils.isEmpty(userLoginInfo.getPhone_num()));
    }

    public static void logout() {
        if (!TextUtils.isEmpty(getUserId())) {
            SaveUtil.getInstance().saveString("UserLoginInfo_no_login_id", "");
        }
        SaveUtil.getInstance().saveString("UserLoginInfo_id", "");
        SaveUtil.getInstance().saveString("uid", "");
        addNewLoginInfo(new UserLoginBean());
        SaveUtil.getInstance().saveBoolean("UserLoginInfo_isLogin", false);
    }

    public static UserLoginBean getLoginInfo() {
        if (!isLoginNoToast()) {
            return null;
        }
        String userLoginInfo_id = SaveUtil.getInstance().getString("UserLoginInfo_id");
        String UserLoginInfo_user_division = SaveUtil.getInstance().getString("UserLoginInfo_user_division");
        String UserLoginInfo_image = SaveUtil.getInstance().getString("UserLoginInfo_image");
        String UserLoginInfo_createtime = SaveUtil.getInstance().getString("UserLoginInfo_createtime");
        String UserLoginInfo_user_position = SaveUtil.getInstance().getString("UserLoginInfo_user_position");
        String UserLoginInfo_sort = SaveUtil.getInstance().getString("UserLoginInfo_sort");
        Boolean UserLoginInfo_isAdmin = SaveUtil.getInstance().getBoolean("UserLoginInfo_isAdmin");
        String UserLoginInfo_password = SaveUtil.getInstance().getString("UserLoginInfo_password");
        String UserLoginInfo_name = SaveUtil.getInstance().getString("UserLoginInfo_name");
        String UserLoginInfo_imageAddress = SaveUtil.getInstance().getString("UserLoginInfo_imageAddress");
        String UserLoginInfo_phone_num = SaveUtil.getInstance().getString("UserLoginInfo_phone_num");
        String UserLoginInfo_login_num = SaveUtil.getInstance().getString("UserLoginInfo_login_num");
        String UserLoginInfo_user_rank = SaveUtil.getInstance().getString("UserLoginInfo_user_rank");
        String UserLoginInfo_updatetime = SaveUtil.getInstance().getString("UserLoginInfo_updatetime");


        UserLoginBean userLoginInfo = new UserLoginBean();
        userLoginInfo.setId(Long.parseLong(userLoginInfo_id));
        userLoginInfo.setUser_division(UserLoginInfo_user_division);
        userLoginInfo.setImage(UserLoginInfo_image);
        userLoginInfo.setCreatetime(UserLoginInfo_createtime);
        userLoginInfo.setUser_position(UserLoginInfo_user_position);
        userLoginInfo.setSort(TextUtils.isEmpty(UserLoginInfo_sort) ? 0 : (UiHeplUtils.isDouble(UserLoginInfo_sort) ? Integer.parseInt(UserLoginInfo_sort) : 0));
        userLoginInfo.setAdmin(UserLoginInfo_isAdmin);
        userLoginInfo.setPassword(UserLoginInfo_password);
        userLoginInfo.setName(UserLoginInfo_name);
        userLoginInfo.setImageAddress(UserLoginInfo_imageAddress);
        userLoginInfo.setPhone_num(UserLoginInfo_phone_num);
        userLoginInfo.setLogin_num(UserLoginInfo_login_num);
        userLoginInfo.setUser_rank(UserLoginInfo_user_rank);
        userLoginInfo.setUpdatetime(UserLoginInfo_updatetime);
        return userLoginInfo;
    }

    public static String getUserId() {
        String userLoginInfo_id = SaveUtil.getInstance().getString("UserLoginInfo_id");
        return userLoginInfo_id;
    }

    public String getUser_division() {
        return user_division;
    }

    public void setUser_division(String user_division) {
        this.user_division = user_division;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getLogin_num() {
        return login_num;
    }

    public void setLogin_num(String login_num) {
        this.login_num = login_num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_rank() {
        return user_rank;
    }

    public void setUser_rank(String user_rank) {
        this.user_rank = user_rank;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
