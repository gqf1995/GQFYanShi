package com.gqfyanshi.mvp.activity;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.ReceiveSignBinder;
import com.gqfyanshi.mvp.delegate.ReceiveSignDelegate;

public class ReceiveSignActivity extends BaseDataBindActivity<ReceiveSignDelegate, ReceiveSignBinder> {

    @Override
    protected Class<ReceiveSignDelegate> getDelegateClass() {
        return ReceiveSignDelegate.class;
    }

    @Override
    public ReceiveSignBinder getDataBinder(ReceiveSignDelegate viewDelegate) {
        return new ReceiveSignBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("市委办公室收文处理签")
                .setmRightImg1("保存发送"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
