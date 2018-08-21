package com.gqfyanshi.mvp.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.gqfyanshi.R;
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
    }
    ReceivinOofficialDocumentsFragment receivinOofficialDocumentsFragment;
    private void initFragment(boolean isInit){
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
        startActivity(new Intent(viewDelegate.getActivity(),MsgActivity.class));
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
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
