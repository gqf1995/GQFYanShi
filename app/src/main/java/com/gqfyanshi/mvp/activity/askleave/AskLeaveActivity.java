package com.gqfyanshi.mvp.activity.askleave;

import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfyanshi.mvp.databinder.AskLeaveBinder;
import com.gqfyanshi.mvp.delegate.AskLeaveDelegate;

public class AskLeaveActivity extends BaseDataBindActivity<AskLeaveDelegate, AskLeaveBinder> {

    @Override
    protected Class<AskLeaveDelegate> getDelegateClass() {
        return AskLeaveDelegate.class;
    }

    @Override
    public AskLeaveBinder getDataBinder(AskLeaveDelegate viewDelegate) {
        return new AskLeaveBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("请假"));
        viewDelegate.viewHolder.recycler_view.setVisibility(View.GONE);
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime())) {
                    ToastUtil.show("请选择起始时间");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime())) {
                    ToastUtil.show("请选择终止时间");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes3.getText().toString())) {
                    ToastUtil.show("请输入所在单位");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_input1.getText().toString())) {
                    ToastUtil.show("请输入请假内容");
                    return;
                }


            }
        });
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
