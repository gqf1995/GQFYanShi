package com.gqfyanshi.mvp.activity.approval;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.ShowPngAdapter;
import com.gqfyanshi.base.AppConst;
import com.gqfyanshi.entity.bean.ApprovalBean;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.activity.file.TBSActivity;
import com.gqfyanshi.mvp.databinder.ApprovalBinder;
import com.gqfyanshi.mvp.delegate.ApprovalDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

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
        viewDelegate.viewHolder.et_miji.setEnabled(false);
        viewDelegate.viewHolder.et_zhuguan.setEnabled(false);
        addRequest(binder.fileSign_detailFileSign(id, this));
        viewDelegate.viewHolder.lin2.setVisibility(View.VISIBLE);
        viewDelegate.viewHolder.lin_rcv.setVisibility(View.VISIBLE);
        viewDelegate.viewHolder.selectTimeLayout1.setShowTime("");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x123) {
                setResult(RESULT_OK);
                onBackPressed();
            }
        }
    }

    @Override
    protected void clickRightIv() {
        super.clickRightIv();
        if (isMy) {
            ApprovalActivity.startAct(this, json,departName, 0x123);
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
    ShowPngAdapter showPngAdapter;
    String departName;
    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                json = GsonUtil.getInstance().getValue(data, "doc");
                documentInfoBean = GsonUtil.getInstance().toObj(data, "doc", ApprovalBean.class);
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
                //                viewDelegate.viewHolder.selectPeopleLayout.setShowEdit(
                //                        documentInfoBean.getDepartmentName());
                viewDelegate.viewHolder.et_yin.setText(documentInfoBean.getNum());
                viewDelegate.viewHolder.et_zhuguan.setText(documentInfoBean.getLeaderOpinion());
                viewDelegate.viewHolder.et_shenhe.setText(documentInfoBean.getAudit());
                viewDelegate.viewHolder.et_qianfa.setText(documentInfoBean.getIssue());
                viewDelegate.viewHolder.et_chaosong.setText(documentInfoBean.getCopyTo());
                viewDelegate.viewHolder.selectTimeLayout1.setShowTime(documentInfoBean.getIssuedTime());


                departName = GsonUtil.getInstance().getValue(data, "departName");
                viewDelegate.viewHolder.selectPeopleLayout.setShowEdit(departName);
                viewDelegate.viewHolder.tv_people.setText(departName + "");


                if (!ListUtils.isEmpty(documentInfoBean.getPostils())) {
                    viewDelegate.viewHolder.lin_rcv.setVisibility(View.VISIBLE);
                    showPngAdapter = new ShowPngAdapter(this, documentInfoBean.getPostils());
                    showPngAdapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                        @Override
                        public void onClick(View view, int position, Object item) {
                            if (UserLoginBean.getUserId().equals("" + documentInfoBean.getPostils().get(position).getUserId())) {
                                addRequest(binder.fileSign_delPostil(documentInfoBean.getPostils().get(position).getId() + "", ApprovalDetailActivity.this));
                            } else {
                                ToastUtil.show("您没有权限删除他人签批");
                            }
                        }
                    });
                    showPngAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            List<String> data = new ArrayList<>();
                            for (int i = 0; i < documentInfoBean.getPostils().size(); i++) {
                                data.add(AppConst.app2BaseUrl + "/" + documentInfoBean.getPostils().get(i).getPostilAddress());
                            }
                            UiHeplUtils.showBigImg(
                                    viewDelegate.getActivity(), data, position
                            );
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                    viewDelegate.viewHolder.recycler_view.setVisibility(View.VISIBLE);
                    viewDelegate.viewHolder.recycler_view.setLayoutManager(new GridLayoutManager(this, 5) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    viewDelegate.viewHolder.recycler_view.setAdapter(showPngAdapter);
                    for (int i = 0; i < documentInfoBean.getPostils().size(); i++) {
                        if (!isMy && UserLoginBean.getUserId().equals("" + documentInfoBean.getPostils().get(i).getUserId())) {
                            viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                            viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                        }
                    }
                } else {
                    viewDelegate.viewHolder.lin_rcv.setVisibility(View.GONE);
                }
                viewDelegate.viewHolder.lin_file.setVisibility(
                        TextUtils.isEmpty(documentInfoBean.getFile_name())
                                ? View.GONE : View.VISIBLE);
                viewDelegate.viewHolder.et_attributes2.setText(documentInfoBean.getFile_name() + "(点击查看)");
                viewDelegate.viewHolder.lin_attributes2.setOnClickListener(new View.OnClickListener() {
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
                initToolbar(new ToolbarBuilder().setTitle("中共偃师市委发文签"));
                viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                isEdit = false;
                viewDelegate.viewHolder.ink.setVisibility(View.GONE);
                viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
                ToastUtil.show("签批成功");
                addRequest(binder.fileSign_detailFileSign(id, this));
                break;
            case 0x131:
                addRequest(binder.fileSign_detailFileSign(id, this));
                break;
        }
    }

}
