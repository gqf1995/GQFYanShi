package com.gqfyanshi.mvp.activity.main;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.utils.glide.GlideCacheUtil;
import com.fivefivelike.mybaselibrary.view.dialog.NetWorkDialog;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.AppVersion;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.databinder.LoginBinder;
import com.gqfyanshi.mvp.delegate.LoginDelegate;
import com.gqfyanshi.mvp.dialog.UpdateDialog;
import com.gqfyanshi.server.HttpUrl;
import com.gqfyanshi.server.UpdateService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

public class LoginActivity extends BaseDataBindActivity<LoginDelegate, LoginBinder> {

    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }

    boolean isSelect = false;

    @Override
    public LoginBinder getDataBinder(LoginDelegate viewDelegate) {
        return new LoginBinder(viewDelegate);
    }

    private void loadImg() {
        Glide.with(this)
                .asBitmap()
                .load(HttpUrl.getIntance().pictureCheckCode)
                .into(new Target<Bitmap>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        viewDelegate.viewHolder.iv_img_code.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }
                });
    }

    String ANDROID_ID;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        //viewDelegate.viewHolder.tv_phone.setText("15896559159");
        //viewDelegate.viewHolder.tv_code.setText("231231");
        //viewDelegate.viewHolder.tv_img_code.setText("21312");
        //viewDelegate.viewHolder.tv_phone.setText("17396360301");
        addRequest(binder.getAppVersion(this));
        ANDROID_ID = Settings.System.getString(getContentResolver(), Settings.System.ANDROID_ID);
        Log.i("ANDROID_ID", ANDROID_ID);

        isSelect = SaveUtil.getInstance().getBoolean("login_save");
        if (!isSelect) {
            viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.weigouxuan);
        } else {
            viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.gouxuan);
            viewDelegate.viewHolder.tv_phone.setText(SaveUtil.getInstance().getString("login_phone"));
        }
        //loadImg();
        addRequest(binder.pictureCheckCode(this));
        viewDelegate.viewHolder.tv_change_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImg();
            }
        });
        viewDelegate.viewHolder.lin_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelect) {
                    isSelect = false;
                    viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.weigouxuan);
                } else {
                    isSelect = true;
                    viewDelegate.viewHolder.iv_select.setImageResource(R.drawable.gouxuan);
                }
            }
        });
        viewDelegate.viewHolder.tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_phone.getText().toString())) {
                    ToastUtil.show("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_code.getText().toString())) {
                    ToastUtil.show("请输入验证码");
                    return;
                }
                //                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_img_code.getText().toString())) {
                //                    ToastUtil.show("请输入图形验证码");
                //                    return;
                //                }
                addRequest(binder.doLogin(
                        viewDelegate.viewHolder.tv_phone.getText().toString(),
                        viewDelegate.viewHolder.tv_img_code.getText().toString(),
                        viewDelegate.viewHolder.tv_code.getText().toString(),
                        ANDROID_ID,
                        LoginActivity.this
                ));
            }
        });
        viewDelegate.viewHolder.tv_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.tv_phone.getText().toString())) {
                    ToastUtil.show("请输入手机号");
                    return;
                }
                addRequest(binder.sendPhoneCode(viewDelegate.viewHolder.tv_phone.getText().toString(),
                        LoginActivity.this));
            }
        });
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x124:
                UserLoginBean userLoginBean = GsonUtil.getInstance().toObj(data, UserLoginBean.class);
                UserLoginBean.addNewLoginInfo(userLoginBean);
                startActivity(new Intent(this, MainActivity.class));
                if (isSelect) {
                    SaveUtil.getInstance().saveString("login_phone", viewDelegate.viewHolder.tv_phone.getText().toString());
                } else {
                    SaveUtil.getInstance().saveString("login_phone", "");
                }
                SaveUtil.getInstance().saveBoolean("login_save", isSelect);
                break;
            case 0x125:
                addRequest(UiHeplUtils.getCode(viewDelegate.viewHolder.tv_send_code, 60));
                break;
            case 0x126:
                appVersion = GsonUtil.getInstance().toObj(data, AppVersion.class);
                version();
                break;
        }
    }

    AppVersion appVersion;

    private void version() {
        if (UiHeplUtils.compareVersion(appVersion.getApp_version(),
                AppUtils.getAppVersionName()) == 1
                && !SaveUtil.getInstance().getBoolean("isUpdataCancel" + AppUtils.getAppVersionName())) {
            new UpdateDialog(LoginActivity.this)
                    .setAppVersion(appVersion)
                    .setDefaultClickLinsener(new DefaultClickLinsener() {
                        @Override
                        public void onClick(View view, int position, Object item) {
                            if (position == 0) {
                                //取消
                                ActivityUtils.finishAllActivities();
                            } else if (position == 1) {
                                //确认
                                updataApp();
                            }
                        }
                    }).showDialog();
            GlideCacheUtil.getInstance().clearImageAllCache();
        } else {

        }
    }

    private void updataApp() {
        AndPermission.with(this)
                .permission(Permission.WRITE_EXTERNAL_STORAGE,
                        Permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        UpdateService.
                                Builder.create(appVersion.getApp_version())
                                .setStoreDir("update")
                                .setIcoResId(R.drawable.add_file_upload)
                                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                                .setAppVersion(appVersion)
                                .build(LoginActivity.this);
                        if (appVersion.isMustUpdate()) {
                            NetWorkDialog netConnectDialog = viewDelegate.getNetConnectDialog("正在更新。。。", false);
                            netConnectDialog.showDialog(true);
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        CircleDialogHelper.initDefaultToastDialog(LoginActivity.this, CommonUtils.getString(R.string.str_permission_read_write), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityUtils.finishAllActivities();
                            }
                        });
                    }
                }).start();
    }


}
