package com.gqfyanshi.mvp.activity.approval;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.gqfyanshi.entity.bean.ApprovalBean;
import com.gqfyanshi.entity.bean.TreeBean;
import com.gqfyanshi.mvp.databinder.ApprovalBinder;
import com.gqfyanshi.mvp.delegate.ApprovalDelegate;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.util.List;

public class ApprovalActivity extends BaseDataBindActivity<ApprovalDelegate, ApprovalBinder> {

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
                .setmRightImg1("保存发送"));
        addRequest(binder.leave_getUserTree(this));

    }

    @Override
    protected void clickRightIv() {
        super.clickRightIv();
        if (TextUtils.isEmpty(viewDelegate.viewHolder.selectPeopleLayout.getSelectId())) {
            ToastUtil.show("请选择签批人");
            return;
        }
        if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes2.getText().toString())) {
            //上传文章
            saveDocument();
        } else {
            //上传文章 文件
            addRequest(binder.document_saveFile(filePath, ApprovalActivity.this));
        }


    }

    private void saveDocument() {
        addRequest(binder.fileSign_saveFileSign(
                viewDelegate.viewHolder.et_num.getText().toString(),
                viewDelegate.viewHolder.et_title.getText().toString(),
                viewDelegate.viewHolder.selectPeopleLayout.getSelectId(),
                viewDelegate.viewHolder.et_jinji.getText().toString(),
                viewDelegate.viewHolder.et_miji.getText().toString(),
                viewDelegate.viewHolder.et_fanwei.getText().toString(),
                viewDelegate.viewHolder.et_danwei.getText().toString(),
                viewDelegate.viewHolder.et_nigao.getText().toString(),
                viewDelegate.viewHolder.et_hegao.getText().toString(),
                viewDelegate.viewHolder.et_niban.getText().toString(),
                viewDelegate.viewHolder.et_hefa.getText().toString(),
                viewDelegate.viewHolder.et_huiqian.getText().toString(),
                "",
                viewDelegate.viewHolder.et_chaosong.getText().toString(),
                viewDelegate.viewHolder.et_yin.getText().toString(),
                viewDelegate.viewHolder.et_zhuguan.getText().toString(),
                viewDelegate.viewHolder.et_shenhe.getText().toString(),
                viewDelegate.viewHolder.et_qianfa.getText().toString(),
                viewDelegate.viewHolder.selectTimeLayout1.getSelectTime(),
                img,
                oldFileName,
                this
        ));
    }

    public static void startAct(Activity activity,
                                String json,
                                int requestCode) {
        Intent intent = new Intent(activity, ApprovalActivity.class);
        intent.putExtra("json", json);
        activity.startActivityForResult(intent, requestCode);
    }

    private String json;

    private void getIntentData() {
        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        if (!TextUtils.isEmpty(json)) {
            documentInfoBean = GsonUtil.getInstance().toObj(json, ApprovalBean.class);
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
            viewDelegate.viewHolder.et_yin.setText(documentInfoBean.getNum());
            viewDelegate.viewHolder.et_zhuguan.setText(documentInfoBean.getLeaderOpinion());
            viewDelegate.viewHolder.et_shenhe.setText(documentInfoBean.getAudit());
            viewDelegate.viewHolder.et_qianfa.setText(documentInfoBean.getIssue());
            viewDelegate.viewHolder.et_huiqian.setText(documentInfoBean.getCounterSign());
        }

        viewDelegate.viewHolder.et_attributes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void changeHeader(String path) {
        filePath = path;
        viewDelegate.viewHolder.et_attributes2.setText(filePath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                changeHeader(list.get(0));
            }
        }
    }


    ApprovalBean documentInfoBean;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                setResult(RESULT_OK);
                break;
            case 0x124:
                //附件上传成功
                img = GsonUtil.getInstance().getValue(data, "filePath");
                oldFileName = GsonUtil.getInstance().getValue(data, "oldFileName");
                saveDocument();
                break;
            case 0x130:
                List<TreeBean> treeBean = GsonUtil.getInstance().toList(data, TreeBean.class);
                viewDelegate.viewHolder.selectPeopleLayout.setTreeBean(treeBean);
                break;
        }
    }

}
