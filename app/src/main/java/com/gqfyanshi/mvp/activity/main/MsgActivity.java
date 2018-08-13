package com.gqfyanshi.mvp.activity.main;

import android.content.Intent;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.activity.notice.NoticeDefaultActivity;
import com.gqfyanshi.mvp.activity.notice.NoticeMeetingActivity;
import com.gqfyanshi.mvp.databinder.MsgBinder;
import com.gqfyanshi.mvp.delegate.MsgDelegate;

public class MsgActivity extends BaseDataBindActivity<MsgDelegate, MsgBinder> {

    @Override
    protected Class<MsgDelegate> getDelegateClass() {
        return MsgDelegate.class;
    }

    @Override
    public MsgBinder getDataBinder(MsgDelegate viewDelegate) {
        return new MsgBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("消息"));
        viewDelegate.viewHolder.lin_msg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewDelegate.getActivity(), NoticeMeetingActivity.class));
            }
        });
        viewDelegate.viewHolder.lin_msg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewDelegate.getActivity(), NoticeDefaultActivity.class));
            }
        });
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
