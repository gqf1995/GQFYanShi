package com.gqfyanshi.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ebensz.eink.api.PennableLayout;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.ShowPngAdapter;
import com.gqfyanshi.adapter.UploadFilesAdapter;
import com.gqfyanshi.base.AppConst;
import com.gqfyanshi.entity.bean.ReceiveSignDetailBean;
import com.gqfyanshi.entity.bean.UploadFile;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.activity.file.TBSActivity;
import com.gqfyanshi.mvp.databinder.ReceiveSignBinder;
import com.gqfyanshi.mvp.delegate.ReceiveSignDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReceiveSignDetailActivity extends BaseDataBindActivity<ReceiveSignDelegate, ReceiveSignBinder> {

    @Override
    protected Class<ReceiveSignDelegate> getDelegateClass() {
        return ReceiveSignDelegate.class;
    }

    @Override
    public ReceiveSignBinder getDataBinder(ReceiveSignDelegate viewDelegate) {
        return new ReceiveSignBinder(viewDelegate);
    }
    PennableLayout pennableLayout;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
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
        viewDelegate.viewHolder.et_shijian.setEnabled(false);
        viewDelegate.viewHolder.et_shouwenshijian.setEnabled(false);
        addRequest(binder.fileSign_detailFileSign(id, this));
    }

    public static void startAct(Activity activity,
                                String id,
                                boolean isMy) {
        Intent intent = new Intent(activity, ReceiveSignDetailActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("isMy", isMy);
        activity.startActivity(intent);
    }

    boolean isEdit = false;
    Handler mHandler = new Handler();

    @Override
    protected void clickRightIv() {
        super.clickRightIv();
        if (isMy) {
            //ApprovalActivity.startAct(this, json,departName, 0x123);
        } else {
            if (isEdit) {
                if (s != null) {
                    List<String> names = new ArrayList<>();
                    List<Bitmap> bitmaps = new ArrayList<>();
                    Bitmap drawingCache = UiHeplUtils.activityScreenShot(this);//UiHeplUtils.convertViewToBitmap(viewDelegate.viewHolder.fl_root);
                    bitmaps.add(drawingCache);
                    for (int i = 0; i < 1; i++) {
                        names.add("/sdcard/" + "file" + System.currentTimeMillis() + ".png");
                    }
                    UiHeplUtils.downBitmapToFile(this, bitmaps, names, false);
                    addRequest(binder.fileSign_postil(s.getId() + "", names.get(0), this));
                    viewDelegate.viewHolder.ink.clear();
                }
            } else {
                isEdit = true;
                viewDelegate.viewHolder.ink.setVisibility(View.VISIBLE);
                viewDelegate.viewHolder.lin_edit.setVisibility(View.VISIBLE);
                initToolbar(new ToolbarBuilder().setTitle("中共偃师市委发文签")
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

    @Override
    protected void clickRightIv1() {
        super.clickRightIv1();
        isEdit = false;
        viewDelegate.viewHolder.ink.setVisibility(View.GONE);
        initToolbar(new ToolbarBuilder().setTitle("中共偃师市委发文签")
                .setmRightImg1("签批"));
        viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
        viewDelegate.viewHolder.lin_edit.setVisibility(View.GONE);
    }

    private String id;
    private boolean isMy;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        isMy = intent.getBooleanExtra("isMy", false);
    }

    String json;
    ReceiveSignDetailBean s;
    String departName;
    String viewName;
    ShowPngAdapter showPngAdapter;
    UploadFilesAdapter uploadFilesAdapter;
    List<UploadFile> uploadFiles;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                json = GsonUtil.getInstance().getValue(data, "doc");
                uploadFiles = GsonUtil.getInstance().toList(data, "uploadFiles", UploadFile.class);
                s = GsonUtil.getInstance().toObj(json, ReceiveSignDetailBean.class);
                viewDelegate.viewHolder.et_bianhao.setText(s.getFile_no());
                viewDelegate.viewHolder.et_biaoti.setText(s.getTitle());
                viewDelegate.viewHolder.et_fenshu.setText(s.getNum() + "");
                viewDelegate.viewHolder.et_laiwendanwei.setText(s.getFromDepartment());
                viewDelegate.viewHolder.et_miji.setText(s.getSecurity());
                viewDelegate.viewHolder.et_nibanyijian.setText(s.getOpinion());
                viewDelegate.viewHolder.et_yuanwenhao.setText(s.getName());
                viewDelegate.viewHolder.et_zhaiyaotishi.setText(s.getPriview());
                viewDelegate.viewHolder.et_bangongshilingdaopishi.setText(s.getLeaderOpinion());
                viewDelegate.viewHolder.et_shiweilingdaopishi.setText(s.getMunicipalLeaderOpinion());
                viewDelegate.viewHolder.et_dianhua.setText(s.getPhone());
                viewDelegate.viewHolder.et_shijian.setText(s.getReleaseTime());
                viewDelegate.viewHolder.et_keshi.setText(s.getDepartment());
                viewDelegate.viewHolder.et_beizhu.setText(s.getRemark());
                viewDelegate.viewHolder.et_shouwenshijian.setText(s.getIssuedTime());
                viewDelegate.viewHolder.tv_edit_title.setText("手写签批区:  字号"+s.getFile_no()+" 签批文件《" + s.getTitle() + "》");

                departName = GsonUtil.getInstance().getValue(data, "departName");
                viewDelegate.viewHolder.tv_people.setText(departName + "");
                viewDelegate.viewHolder.lin2.setVisibility(View.VISIBLE);

                viewName = GsonUtil.getInstance().getValue(data, "viewName");
                viewDelegate.viewHolder.tv_look_people.setText(viewName + "");
                viewDelegate.viewHolder.lin3.setVisibility(View.VISIBLE);

                if (!ListUtils.isEmpty(uploadFiles)) {
                    viewDelegate.viewHolder.lin4.setVisibility(View.VISIBLE);
                    uploadFilesAdapter = new UploadFilesAdapter(this, uploadFiles);
                    uploadFilesAdapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                        @Override
                        public void onClick(View view, int position, Object item) {
                            TBSActivity.startAct(
                                    viewDelegate.getActivity(),
                                    uploadFiles.get(position).getFileName(),
                                    uploadFiles.get(position).getFileAddress()
                            );
                        }
                    });
                    viewDelegate.viewHolder.rv_file.setLayoutManager(new LinearLayoutManager(this) {
                        @Override
                        public boolean canScrollVertically() {
                            return false;
                        }
                    });
                    viewDelegate.viewHolder.rv_file.setAdapter(uploadFilesAdapter);
                } else {
                    viewDelegate.viewHolder.lin4.setVisibility(View.GONE);
                }

                if (!ListUtils.isEmpty(s.getPostils())) {
                    viewDelegate.viewHolder.lin_rcv.setVisibility(View.VISIBLE);
                    showPngAdapter = new ShowPngAdapter(this, s.getPostils());
                    showPngAdapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                        @Override
                        public void onClick(View view, int position, Object item) {
                            addRequest(binder.fileSign_delPostil(s.getPostils().get(position).getId() + "", ReceiveSignDetailActivity.this));
                        }
                    });
                    showPngAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            List<String> data = new ArrayList<>();
                            for (int i = 0; i < s.getPostils().size(); i++) {
                                data.add(AppConst.fileUrl + "/" + s.getPostils().get(i).getPostilAddress());
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
                    for (int i = 0; i < s.getPostils().size(); i++) {
                        if (!isMy && UserLoginBean.getUserId().equals("" + s.getPostils().get(i).getUserId())) {
                            viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
                            viewDelegate.getmToolbarRightImg2().setVisibility(View.GONE);
                        }
                    }
                } else {
                    viewDelegate.viewHolder.lin_rcv.setVisibility(View.GONE);
                }
                break;
            case 0x124:
                initToolbar(new ToolbarBuilder().setTitle("中共偃师市委发文签")
                        .setmRightImg1("签批"));
                //viewDelegate.getmToolbarRightImg1().setVisibility(View.GONE);
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
