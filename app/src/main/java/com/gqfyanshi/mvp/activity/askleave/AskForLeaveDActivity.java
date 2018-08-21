package com.gqfyanshi.mvp.activity.askleave;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AskForLeaveBinder;
import com.gqfyanshi.mvp.delegate.AskForLeaveDelegate;

public class AskForLeaveDActivity extends BaseDataBindActivity<AskForLeaveDelegate, AskForLeaveBinder> {

    @Override
    protected Class<AskForLeaveDelegate> getDelegateClass() {
        return AskForLeaveDelegate.class;
    }

    @Override
    public AskForLeaveBinder getDataBinder(AskForLeaveDelegate viewDelegate) {
        return new AskForLeaveBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("请假"));
        viewDelegate.viewHolder.lin2.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin3.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin4.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin5.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin6.setVisibility(View.GONE);
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
