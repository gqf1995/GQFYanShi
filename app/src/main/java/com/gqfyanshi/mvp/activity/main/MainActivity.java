package com.gqfyanshi.mvp.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.mvp.activity.notice.approval.NoticeApprovalActivity;
import com.gqfyanshi.mvp.databinder.MainBinder;
import com.gqfyanshi.mvp.delegate.MainDelegate;
import com.gqfyanshi.mvp.fragment.ReceivinOofficialDocumentsFragment;
import com.gqfyanshi.mvp.fragment.UserDrawerFragment;

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
        addRequest(binder.getInfo(this));
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
        }
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
