package com.gqfyanshi.mvp.activity.askleave;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.ShowPngAdapter;
import com.gqfyanshi.base.AppConst;
import com.gqfyanshi.entity.bean.AskleaveBean;
import com.gqfyanshi.entity.bean.LeaveTypeBean;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.databinder.AskLeaveBinder;
import com.gqfyanshi.mvp.delegate.AskLeaveDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

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
        if (!isMy) {
            initToolbar(new ToolbarBuilder().setTitle("请假详情")
                    .setmRightImg1("签批"));
        } else {
            initToolbar(new ToolbarBuilder().setTitle("请假详情"));
        }
        viewDelegate.viewHolder.tv_commit.setVisibility(View.GONE);
        viewDelegate.viewHolder.et_attributes3.setEnabled(false);
        viewDelegate.viewHolder.et_attributes4.setEnabled(false);
        viewDelegate.viewHolder.et_attributes5.setEnabled(false);
        viewDelegate.viewHolder.et_attributes6.setEnabled(false);
        viewDelegate.viewHolder.et_attributes7.setEnabled(false);
        viewDelegate.viewHolder.et_attributes8.setEnabled(false);
        viewDelegate.viewHolder.et_attributes9.setEnabled(false);
        viewDelegate.viewHolder.et_input1.setEnabled(false);
        viewDelegate.viewHolder.selectAttrLayout1.setShowEdit("");
        viewDelegate.viewHolder.selectPeopleLayout.setShowEdit("");
        viewDelegate.viewHolder.selectTimeLayout1.setShowTime("");
        viewDelegate.viewHolder.selectTimeLayout2.setShowTime("");
        viewDelegate.viewHolder.lin2.setVisibility(View.VISIBLE);
    }

    Handler mHandler = new Handler();

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
                addRequest(binder.leave_postil(documentInfoBean.getId(), names.get(0), this));
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


    @Override
    protected void clickRightIv1() {
        super.clickRightIv1();
        isEdit = false;
        viewDelegate.viewHolder.ink.setVisibility(View.GONE);
        viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
        initToolbar(new ToolbarBuilder().setTitle("请假详情")
                .setmRightImg1("签批"));
        viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
    }

    public static void startAct(Activity activity,
                                String id,
                                boolean isMy
    ) {
        Intent intent = new Intent(activity, AskLeaveResultActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isMy", isMy);
        activity.startActivity(intent);
    }

    private String id;
    private Boolean isMy;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        isMy = intent.getBooleanExtra("isMy", false);
        addRequest(binder.leave_detailLeave(id, this));
    }

    AskleaveBean documentInfoBean;
    ShowPngAdapter showPngAdapter;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                documentInfoBean = GsonUtil.getInstance().toObj(data, "doc", AskleaveBean.class);
                addRequest(binder.leave_getLeaveType(this));
                viewDelegate.viewHolder.et_attributes3.setText(documentInfoBean.getDepartment() + "");
                viewDelegate.viewHolder.et_attributes4.setText(documentInfoBean.getOutLName());
                viewDelegate.viewHolder.et_attributes5.setText(documentInfoBean.getOutLPosition());
                viewDelegate.viewHolder.et_attributes6.setText(documentInfoBean.getOutLPhoneNum());
                viewDelegate.viewHolder.et_attributes7.setText(documentInfoBean.getManagerName());
                viewDelegate.viewHolder.et_attributes8.setText(documentInfoBean.getManagerPosition());
                viewDelegate.viewHolder.et_attributes9.setText(documentInfoBean.getManagerPhoneNum());
                viewDelegate.viewHolder.et_input1.setText(documentInfoBean.getReason() + "");
                viewDelegate.viewHolder.selectAttrLayout1.setShowEdit(documentInfoBean.getType());
                viewDelegate.viewHolder.selectTimeLayout1.setShowTime(documentInfoBean.getStartTime());
                viewDelegate.viewHolder.selectTimeLayout2.setShowTime(documentInfoBean.getEndTime());
                String departName = GsonUtil.getInstance().getValue(data, "departName");
                viewDelegate.viewHolder.selectPeopleLayout.setShowEdit(departName);
                viewDelegate.viewHolder.tv_people.setText(departName + "");

                if (!ListUtils.isEmpty(documentInfoBean.getPostils())) {
                    viewDelegate.viewHolder.lin_rcv.setVisibility(View.VISIBLE);
                    showPngAdapter = new ShowPngAdapter(this, documentInfoBean.getPostils());
                    showPngAdapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                        @Override
                        public void onClick(View view, int position, Object item) {
                            if (UserLoginBean.getUserId().equals("" + documentInfoBean.getPostils().get(position).getUserId())) {
                                addRequest(binder.fileSign_delPostil(documentInfoBean.getPostils().get(position).getId() + "", AskLeaveResultActivity.this));
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
                        if (UserLoginBean.getUserId().equals("" + documentInfoBean.getPostils().get(i).getUserId())) {
                            viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                            viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                        }
                    }
                }else {
                    viewDelegate.viewHolder.lin_rcv.setVisibility(View.GONE);
                }
                break;
            case 0x124:
                initToolbar(new ToolbarBuilder().setTitle("请假详情"));
                viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                isEdit = false;
                viewDelegate.viewHolder.ink.setVisibility(View.GONE);
                viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
                ToastUtil.show("签批成功");
                addRequest(binder.leave_detailLeave(id, this));
                break;
            case 0x125:
                List<LeaveTypeBean> list = GsonUtil.getInstance().toList(data, LeaveTypeBean.class);
                if (!ListUtils.isEmpty(list)) {
                    for (int i = 0; i < list.size(); i++) {
                        if (ObjectUtils.equals(list.get(i).getDict_value(),
                                documentInfoBean.getType())) {
                            viewDelegate.viewHolder.selectAttrLayout1.setShowEdit(
                                    list.get(i).getRemark());
                        }
                    }
                }
                break;
            case 0x131:
                addRequest(binder.leave_detailLeave(id, this));
                break;
        }
    }

}
