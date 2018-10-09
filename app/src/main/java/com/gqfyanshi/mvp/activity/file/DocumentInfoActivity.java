package com.gqfyanshi.mvp.activity.file;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.mvp.databinder.DocumentInfoBinder;
import com.gqfyanshi.mvp.delegate.DocumentInfoDelegate;

import java.util.ArrayList;
import java.util.List;

public class DocumentInfoActivity extends BaseDataBindActivity<DocumentInfoDelegate, DocumentInfoBinder> {

    boolean isEdit = false;

    @Override
    protected Class<DocumentInfoDelegate> getDelegateClass() {
        return DocumentInfoDelegate.class;
    }

    @Override
    public DocumentInfoBinder getDataBinder(DocumentInfoDelegate viewDelegate) {
        return new DocumentInfoBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("文件详情")
                .setmRightImg1("签批"));
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
                                String id,
                                String type
    ) {
        Intent intent = new Intent(activity, DocumentInfoActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    public static void startAct(Activity activity,
                                String id
    ) {
        Intent intent = new Intent(activity, DocumentInfoActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    private String id;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        addRequest(binder.document_detailDocumnet(id, "", this));
    }

    DocumentBean documentInfoBean;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                documentInfoBean = GsonUtil.getInstance().toObj(data, "doc", DocumentBean.class);

                String departName = GsonUtil.getInstance().getValue(data, "departName");
                viewDelegate.viewHolder.lin_content.setVisibility(TextUtils.isEmpty(departName) ? View.GONE : View.VISIBLE);
                viewDelegate.viewHolder.tv_content.setText(departName + "");

                viewDelegate.viewHolder.tv_title.setText(documentInfoBean.getTitle() + "");
                viewDelegate.viewHolder.tv_time.setText("发文时间：" + documentInfoBean.getCreatetime());

                viewDelegate.viewHolder.lin_file.setVisibility(TextUtils.isEmpty(documentInfoBean.getFile_name()) ? View.GONE : View.VISIBLE);
                viewDelegate.viewHolder.tv_file.setText(documentInfoBean.getFile_name() + "");
                viewDelegate.viewHolder.tv_content_info.setText(documentInfoBean.getContent() + "");
                viewDelegate.viewHolder.tv_file.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TBSActivity.startAct(
                                viewDelegate.getActivity(),
                                documentInfoBean.getFile_name(),
                                documentInfoBean.getFile_address()
                        );
                    }
                });
                break;
            case 0x124:
                initToolbar(new ToolbarBuilder().setTitle("文件详情"));
                viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                break;
        }
    }

}
