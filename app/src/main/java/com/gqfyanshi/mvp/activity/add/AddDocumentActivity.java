package com.gqfyanshi.mvp.activity.add;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfyanshi.entity.bean.TreeBean;
import com.gqfyanshi.mvp.databinder.AddDocumentBinder;
import com.gqfyanshi.mvp.delegate.AddDocumentDelegate;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.util.List;

public class AddDocumentActivity extends BaseDataBindActivity<AddDocumentDelegate, AddDocumentBinder> {

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
        initToolbar(new ToolbarBuilder().setTitle("添加"));
        viewDelegate.viewHolder.lin3.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin_selectAttr.setVisibility(View.GONE);

        getIntentData();
        addRequest(binder.leader_getModelTree(this));
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
                    addRequest(binder.document_saveFile(filePath, AddDocumentActivity.this));
                }
            }
        });

        viewDelegate.viewHolder.lin_attributes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //                intent.setType("*/*");//无类型限制
                //                intent.addCategory(Intent.CATEGORY_OPENABLE);
                //                startActivityForResult(intent, 1);
                new LFilePicker()
                        .withActivity(viewDelegate.getActivity())
                        .withRequestCode(1000)
                        .withMutilyMode(false)
                        .withChooseMode(true)
                        .start();
            }
        });
    }

    String img = "";
    String oldFileName = "";
    String filePath;

    private void saveDocument() {
        addRequest(binder.notice_sendList(
                TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes3.getText().toString()) ?
                        viewDelegate.viewHolder.et_attributes.getText().toString() : viewDelegate.viewHolder.et_attributes3.getText().toString(),
                viewDelegate.viewHolder.et_attributes.getText().toString(),
                type,
                viewDelegate.viewHolder.selectPeopleLayout1.getSelectId(),
                img,
                viewDelegate.viewHolder.et_input.getText().toString(),
                id,
                oldFileName,
                AddDocumentActivity.this
        ));
    }

    private void changeHeader(String path) {
        filePath = path;
        viewDelegate.viewHolder.et_attributes2.setText(filePath);
    }

    public static void startAct(Activity activity,
                                String id,
                                String type) {
        Intent intent = new Intent(activity, AddDocumentActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private String id;
    private String type;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                img = GsonUtil.getInstance().getValue(data, "filePath");
                oldFileName = GsonUtil.getInstance().getValue(data, "oldFileName");
                saveDocument();
                break;
            case 0x124:
                ToastUtil.show("上传成功");
                break;
            case 0x125:
                List<TreeBean> treeBean = GsonUtil.getInstance().toList(data, TreeBean.class);
                viewDelegate.viewHolder.selectPeopleLayout1.setTreeBean(treeBean);
                break;
        }
    }


    String path;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                changeHeader(list.get(0));
            } else {
                Uri uri = data.getData();
                if ("file".equalsIgnoreCase(uri.getScheme())) {//使用第三方应用打开
                    path = uri.getPath();
                    changeHeader(path);
                    return;
                }
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                    path = viewDelegate.getPath(this, uri);
                } else {//4.4以下下系统调用方法
                    path = viewDelegate.getRealPathFromURI(uri);
                }
                changeHeader(path);
            }
        }
    }


}
