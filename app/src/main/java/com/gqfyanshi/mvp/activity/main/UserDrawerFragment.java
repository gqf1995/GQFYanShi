package com.gqfyanshi.mvp.activity.main;

import android.app.Activity;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.gqfyanshi.mvp.databinder.UserDrawerBinder;
import com.gqfyanshi.mvp.delegate.UserDrawerDelegate;

public class UserDrawerFragment extends BaseDataBindFragment<UserDrawerDelegate, UserDrawerBinder> {

    @Override
    protected Class<UserDrawerDelegate> getDelegateClass() {
        return UserDrawerDelegate.class;
    }

    @Override
    public UserDrawerBinder getDataBinder(UserDrawerDelegate viewDelegate) {
        return new UserDrawerBinder(viewDelegate);
    }

    MainLinsener mainLinsener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainLinsener=(MainLinsener)activity;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
