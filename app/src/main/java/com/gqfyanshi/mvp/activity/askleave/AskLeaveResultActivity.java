package com.gqfyanshi.mvp.activity.askleave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.mvp.databinder.AskLeaveBinder;
import com.gqfyanshi.mvp.delegate.AskLeaveDelegate;

import java.util.ArrayList;
import java.util.List;

public class AskLeaveResultActivity extends BaseDataBindActivity<AskLeaveDelegate, AskLeaveBinder> {
    boolean isEdit = false;

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
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("请假详情")
                .setmRightImg1("签批"));
        viewDelegate.viewHolder.tv_commit.setVisibility(View.GONE);
    }


    @Override
    protected void clickRightIv() {
        super.clickRightIv();
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
                addRequest(binder.document_postil(documentInfoBean.getId(), names.get(0), this));
            }
        } else {
            isEdit = true;
            viewDelegate.viewHolder.ink.setVisibility(View.VISIBLE);
            viewDelegate.viewHolder.lin_edit.setVisibility(View.VISIBLE);
            initToolbar(new ToolbarBuilder().setTitle("文件详情")
                    .setmRightImg1("完成").setmRightImg2("取消"));
        }
    }
    @Override
    protected void clickRightIv1() {
        super.clickRightIv1();
        isEdit = false;
        viewDelegate.viewHolder.ink.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
        initToolbar(new ToolbarBuilder().setTitle("文件详情")
                .setmRightImg1("签批"));
        viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
    }
    public static void startAct(Activity activity,
                                String id
    ) {
        Intent intent = new Intent(activity, AskLeaveResultActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    private String id;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
       // addRequest(binder.document_detailDocumnet(id, "", this));
    }
    DocumentBean documentInfoBean;
    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {

            case 0x124:
                initToolbar(new ToolbarBuilder().setTitle("文件详情"));
                viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                isEdit = false;
                viewDelegate.viewHolder.ink.setVisibility(View.GONE);
                viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
                ToastUtil.show("签批成功");
               // addRequest(binder.document_detailDocumnet(id, "", this));
                break;
        }
    }

}
