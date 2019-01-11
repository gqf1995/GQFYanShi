package com.gqfyanshi.mvp.activity.main;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.utils.glide.GlideCacheUtil;
import com.fivefivelike.mybaselibrary.view.dialog.NetWorkDialog;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.AppVersion;
import com.gqfyanshi.mvp.activity.notice.approval.NoticeApprovalActivity;
import com.gqfyanshi.mvp.databinder.MainBinder;
import com.gqfyanshi.mvp.delegate.MainDelegate;
import com.gqfyanshi.mvp.dialog.UpdateDialog;
import com.gqfyanshi.mvp.fragment.ReceivinOofficialDocumentsFragment;
import com.gqfyanshi.mvp.fragment.UserDrawerFragment;
import com.gqfyanshi.server.UpdateService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

public class MainActivity extends BaseDataBindActivity<MainDelegate, MainBinder> implements MainLinsener {

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public MainBinder getDataBinder(MainDelegate viewDelegate) {
        return new MainBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("偃师市党政办公平台")
                .setmRightImg1(CommonUtils.getString(R.string.ic_tixing)));
        viewDelegate.getmToolbarBack().setText(CommonUtils.getString(R.string.ic_daohang));
        viewDelegate.getmToolbarBackLin().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDelegate.viewHolder.main_drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        addRequest(binder.getAppVersion(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        addRequest(binder.getLoginedUserInfo(this));
    }

    ReceivinOofficialDocumentsFragment receivinOofficialDocumentsFragment;

    private void initFragment(boolean isInit) {
        viewDelegate.initAddFragment(R.id.fl_root, getSupportFragmentManager());
        if (isInit && ListUtils.isEmpty(getSupportFragmentManager().getFragments())) {
            viewDelegate.addFragment(receivinOofficialDocumentsFragment = new ReceivinOofficialDocumentsFragment(), "ReceivinOofficialDocumentsFragment", 0);
        } else {
            receivinOofficialDocumentsFragment = (ReceivinOofficialDocumentsFragment) viewDelegate.getFragmentByTag("ReceivinOofficialDocumentsFragment", 0);
        }
        viewDelegate.showFragment(0);
        setDoubleClickExit(true);
    }

    @Override
    protected void bindEvenListenerBuyState(Bundle savedInstanceState) {
        super.bindEvenListenerBuyState(savedInstanceState);
        loadDrawerLayout(savedInstanceState == null && ListUtils.isEmpty(getSupportFragmentManager().getFragments()));
        initFragment(savedInstanceState == null && ListUtils.isEmpty(getSupportFragmentManager().getFragments()));
    }

    UserDrawerFragment userFragment;

    private void loadDrawerLayout(boolean isFirst) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isFirst) {
            userFragment = new UserDrawerFragment();
            transaction.add(R.id.fl_left, userFragment, "UserDrawerFragment");
        } else {
            userFragment = (UserDrawerFragment) getSupportFragmentManager().findFragmentByTag("UserDrawerFragment");
            transaction.show(userFragment);
        }
        transaction.commitAllowingStateLoss();
        viewDelegate.viewHolder.main_drawer_layout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void clickRightIv() {
        super.clickRightIv();
        startActivity(new Intent(viewDelegate.getActivity(), MsgActivity.class));
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                // "meetMsg": 0,
                // "docMsg": 3,
                //"normalMsg": 1,
                // "leaverMsg": 13
                String meetMsg = GsonUtil.getInstance().getValue(data, "meetMsg");
                String docMsg = GsonUtil.getInstance().getValue(data, "docMsg");
                String normalMsg = GsonUtil.getInstance().getValue(data, "normalMsg");
                String leaverMsg = GsonUtil.getInstance().getValue(data, "leaverMsg");
                String fileSignMsg = GsonUtil.getInstance().getValue(data, "fileSignMsg");
                if (
                        !ObjectUtils.equals("0", meetMsg) ||
                                !ObjectUtils.equals("0", docMsg) ||
                                !ObjectUtils.equals("0", normalMsg) ||
                                !ObjectUtils.equals("0", fileSignMsg) ||
                                !ObjectUtils.equals("0", leaverMsg)
                        ) {
                    viewDelegate.getViewImgPoint().setVisibility(View.VISIBLE);
                } else {
                    viewDelegate.getViewImgPoint().setVisibility(View.GONE);
                }
                break;
            case 0x124:
                if (
                        !ObjectUtils.equals("0", GsonUtil.getInstance().getValue(data, "fileSignMsg"))
                        ) {
                    CircleDialogHelper.initDefaultDialog(viewDelegate.getActivity(), "您有新的待签批文件", null)
                            .setPositive("去查看", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(viewDelegate.getActivity(), NoticeApprovalActivity.class));
                                }
                            })
                            .show();
                }
                break;
            case 0x125:
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
            new UpdateDialog(MainActivity.this)
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
        }else {
            addRequest(binder.getInfo(this));
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
                                .build(MainActivity.this);
                        if (appVersion.isMustUpdate()) {
                            NetWorkDialog netConnectDialog = viewDelegate.getNetConnectDialog("正在更新。。。", false);
                            netConnectDialog.showDialog(true);
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        CircleDialogHelper.initDefaultToastDialog(MainActivity.this, CommonUtils.getString(R.string.str_permission_read_write), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityUtils.finishAllActivities();
                            }
                        });
                    }
                }).start();
    }

    @Override
    public void showLeft() {
        viewDelegate.viewHolder.main_drawer_layout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void closeLeft() {
        viewDelegate.viewHolder.main_drawer_layout.closeDrawer(Gravity.LEFT);
    }
}
