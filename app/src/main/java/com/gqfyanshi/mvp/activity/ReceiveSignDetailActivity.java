package com.gqfyanshi.mvp.activity;

import android.app.Activity;
import android.content.Intent;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.activity.approval.ApprovalDetailActivity;
import com.gqfyanshi.mvp.databinder.ReceiveSignBinder;
import com.gqfyanshi.mvp.delegate.ReceiveSignDelegate;

public class ReceiveSignDetailActivity extends BaseDataBindActivity<ReceiveSignDelegate, ReceiveSignBinder> {

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
                .setmRightImg1(isMy ? "修改" : "签批"));
        viewDelegate.viewHolder.et_attributes2.setEnabled(false);
        viewDelegate.viewHolder.et_bangongshilingdaopishi.setEnabled(false);
        viewDelegate.viewHolder.et_beizhu.setEnabled(false);
        viewDelegate.viewHolder.et_bianhao.setEnabled(false);
        viewDelegate.viewHolder.et_biaoti.setEnabled(false);
        viewDelegate.viewHolder.et_dianhua.setEnabled(false);
        viewDelegate.viewHolder.et_fenshu.setEnabled(false);
        viewDelegate.viewHolder.et_keshi.setEnabled(false);
        viewDelegate.viewHolder.et_laiwendanwei.setEnabled(false);
        viewDelegate.viewHolder.et_miji.setEnabled(false);
        viewDelegate.viewHolder.et_nibanyijian.setEnabled(false);
        viewDelegate.viewHolder.et_shiweilingdaopishi.setEnabled(false);
        viewDelegate.viewHolder.et_yuanwenhao.setEnabled(false);
        viewDelegate.viewHolder.et_zhaiyaotishi.setEnabled(false);
    }

    public static void startAct(Activity activity,
                                String id,
                                boolean isMy) {
        Intent intent = new Intent(activity, ReceiveSignDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isMy", isMy);
        activity.startActivity(intent);
    }

    private String id;
    private boolean isMy;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        isMy = intent.getBooleanExtra("isMy", false);
    }
    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
