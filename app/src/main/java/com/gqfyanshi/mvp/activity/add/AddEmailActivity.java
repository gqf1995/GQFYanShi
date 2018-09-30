package com.gqfyanshi.mvp.activity.add;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.gqfyanshi.mvp.databinder.AddDocumentBinder;
import com.gqfyanshi.mvp.delegate.AddDocumentDelegate;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.AlbumFile;

import java.util.ArrayList;

public class AddEmailActivity extends BaseDataBindActivity<AddDocumentDelegate, AddDocumentBinder> {

    @Override
    protected Class<AddDocumentDelegate> getDelegateClass() {
        return AddDocumentDelegate.class;
    }

    @Override
    public AddDocumentBinder getDataBinder(AddDocumentDelegate viewDelegate) {
        return new AddDocumentBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("邮件发送"));
        viewDelegate.viewHolder.tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes.getText().toString())) {
                    ToastUtil.show("请输入标题");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_input.getText().toString())) {
                    ToastUtil.show("请输入内容");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes2.getText().toString())) {
                    //上传文章
                    saveDocument();
                } else {
                    //上传文章 文件
                    addRequest(binder.document_saveFile(filePath, AddEmailActivity.this));
                }
            }
        });

        viewDelegate.viewHolder.lin_attributes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiHeplUtils.getPhoto(AddEmailActivity.this, new Action<String>() {
                    @Override
                    public void onAction(int requestCode, @NonNull String result) {
                        changeHeader(result);
                    }
                }, new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(int requestCode, @NonNull ArrayList<AlbumFile> result) {
                        changeHeader(result.get(0).getPath());
                    }
                }, 1);
            }
        });
    }

    String img = "";
    String filePath;

    private void saveDocument() {
        addRequest(binder.email_emailForm(
                viewDelegate.viewHolder.et_attributes.getText().toString(),
                viewDelegate.viewHolder.et_attributes.getText().toString(),
                "10",
                img,
                viewDelegate.viewHolder.et_input.getText().toString(),
                AddEmailActivity.this
        ));
    }

    private void changeHeader(String path) {
        filePath = path;
        viewDelegate.viewHolder.et_attributes2.setText(filePath);
    }

    public static void startAct(Activity activity,
                                String id,
                                String type) {
        Intent intent = new Intent(activity, AddEmailActivity.class);
        intent.putExtra("id", "id");
        intent.putExtra("type", "type");
        activity.startActivity(intent);
    }



    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                img = GsonUtil.getInstance().getValue(data, "filePath");
                saveDocument();
                break;
            case 0x124:
                ToastUtil.show("上传成功");
                break;
        }
    }

}
