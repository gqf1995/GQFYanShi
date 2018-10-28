package com.gqfyanshi.mvp.activity.approval;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.gqfyanshi.entity.bean.ApprovalBean;
import com.gqfyanshi.mvp.databinder.ApprovalBinder;
import com.gqfyanshi.mvp.delegate.ApprovalDelegate;

import java.util.ArrayList;
import java.util.List;

public class ApprovalDetailActivity extends BaseDataBindActivity<ApprovalDelegate, ApprovalBinder> {
    boolean isEdit = false;

    @Override
    protected Class<ApprovalDelegate> getDelegateClass() {
        return ApprovalDelegate.class;
    }

    @Override
    public ApprovalBinder getDataBinder(ApprovalDelegate viewDelegate) {
        return new ApprovalBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("中共偃师市委发文签")
                .setmRightImg1(isMy ? "修改" : "签批"));
        viewDelegate.viewHolder.et_chaosong.setEnabled(false);
        viewDelegate.viewHolder.et_danwei.setEnabled(false);
        viewDelegate.viewHolder.et_fanwei.setEnabled(false);
        viewDelegate.viewHolder.et_hefa.setEnabled(false);
        viewDelegate.viewHolder.et_hegao.setEnabled(false);
        viewDelegate.viewHolder.et_huiqian.setEnabled(false);
        viewDelegate.viewHolder.et_jinji.setEnabled(false);
        viewDelegate.viewHolder.et_niban.setEnabled(false);
        viewDelegate.viewHolder.et_nigao.setEnabled(false);
        viewDelegate.viewHolder.et_num.setEnabled(false);
        viewDelegate.viewHolder.et_qianfa.setEnabled(false);
        viewDelegate.viewHolder.et_shenhe.setEnabled(false);
        viewDelegate.viewHolder.et_title.setEnabled(false);
        viewDelegate.viewHolder.et_yin.setEnabled(false);
        viewDelegate.viewHolder.et_zhuguan.setEnabled(false);
        addRequest(binder.fileSign_saveFileSign(id, this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x123) {
                onBackPressed();
            }
        }
    }

    @Override
    protected void clickRightIv() {
        super.clickRightIv();
        if (isMy) {
            ApprovalActivity.startAct(this, json, 0x123);
        } else {
            if (isEdit) {
                if (documentInfoBean != null) {
                    List<String> names = new ArrayList<>();
                    List<Bitmap> bitmaps = new ArrayList<>();
                    Bitmap drawingCache = UiHeplUtils.convertViewToBitmap(viewDelegate.viewHolder.fl_root);
                    bitmaps.add(drawingCache);
                    for (int i = 0; i < 1; i++) {
                        names.add("/sdcard/" + "file" + System.currentTimeMillis() + ".png");
                    }
                    UiHeplUtils.downBitmapToFile(this, bitmaps, names, false);
                    addRequest(binder.fileSign_postil(documentInfoBean.getId(), names.get(0), this));
                }
            } else {
                isEdit = true;
                viewDelegate.viewHolder.ink.setVisibility(View.VISIBLE);
                viewDelegate.viewHolder.lin_edit.setVisibility(View.VISIBLE);
                initToolbar(new ToolbarBuilder().setTitle("请假详情")
                        .setmRightImg1("完成").setmRightImg2("取消"));
                mHandler.post(new Runnable() {
                    public void run() {
                        int offset = viewDelegate.viewHolder.lin_nestedScrollView.getMeasuredHeight()
                                - viewDelegate.viewHolder.nestedScrollView.getHeight();
                        if (offset < 0) {
                            offset = 0;
                        }
                        viewDelegate.viewHolder.nestedScrollView.scrollTo(0, offset);
                    }
                });
            }
        }
    }

    Handler mHandler = new Handler();

    @Override
    protected void clickRightIv1() {
        super.clickRightIv1();
        isEdit = false;
        viewDelegate.viewHolder.ink.setVisibility(View.GONE);
        initToolbar(new ToolbarBuilder().setTitle("中共偃师市委发文签")
                .setmRightImg1("签批"));
        viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
    }

    public static void startAct(Activity activity,
                                String id,
                                boolean isMy) {
        Intent intent = new Intent(activity, ApprovalDetailActivity.class);
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

    String json;
    ApprovalBean documentInfoBean;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                json = data;
                documentInfoBean = GsonUtil.getInstance().toObj(data, ApprovalBean.class);
                viewDelegate.viewHolder.et_num.setText(documentInfoBean.getName());
                viewDelegate.viewHolder.et_title.setText(documentInfoBean.getTitle());
                viewDelegate.viewHolder.et_jinji.setText(documentInfoBean.getPriority());
                viewDelegate.viewHolder.et_miji.setText(documentInfoBean.getSecurity());
                viewDelegate.viewHolder.et_fanwei.setText(documentInfoBean.getScope());
                viewDelegate.viewHolder.et_danwei.setText(documentInfoBean.getSponsor());
                viewDelegate.viewHolder.et_nigao.setText(documentInfoBean.getDraftDoc());
                viewDelegate.viewHolder.et_hegao.setText(documentInfoBean.getVerifyDoc());
                viewDelegate.viewHolder.et_niban.setText(documentInfoBean.getOpinion());
                viewDelegate.viewHolder.et_hefa.setText(documentInfoBean.getVerifySend());
                viewDelegate.viewHolder.et_huiqian.setText(documentInfoBean.getCounterSign());
                viewDelegate.viewHolder.selectPeopleLayout.setShowEdit(documentInfoBean.getDepartmentName());
                break;
            case 0x124:
                initToolbar(new ToolbarBuilder().setTitle("请假详情"));
                viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                isEdit = false;
                viewDelegate.viewHolder.ink.setVisibility(View.GONE);
                viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
                ToastUtil.show("签批成功");
                addRequest(binder.fileSign_saveFileSign(id, this));
                break;
        }
    }

}
