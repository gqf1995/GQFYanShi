package com.gqfyanshi.mvp.activity.file;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.circledialog.CircleDialogHelper;
import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.UiHeplUtils;
import com.fivefivelike.mybaselibrary.view.dialog.NetWorkDialog;
import com.gqfyanshi.base.AppConst;
import com.gqfyanshi.mvp.databinder.TBSBinder;
import com.gqfyanshi.mvp.delegate.TBSDelegate;
import com.gqfyanshi.utils.OpenFileUtil;
import com.tencent.smtt.sdk.TbsReaderView;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TBSActivity extends BaseDataBindActivity<TBSDelegate, TBSBinder> implements TbsReaderView.ReaderCallback {

    @Override
    protected Class<TBSDelegate> getDelegateClass() {
        return TBSDelegate.class;
    }

    @Override
    public TBSBinder getDataBinder(TBSDelegate viewDelegate) {
        return new TBSBinder(viewDelegate);
    }

    TbsReaderView mTbsReaderView;
    DownloadQueue queue;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle(fileName));
        mTbsReaderView = new TbsReaderView(this, this);
        viewDelegate.viewHolder.lin_root.addView(mTbsReaderView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .rationale(new Rationale<List<String>>() {
                    @Override
                    public void showRationale(Context context, List<String> data, RequestExecutor executor) {
                        executor.execute();
                    }
                })
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        download();
                    }

                }).onDenied(new Action<List<String>>() {
            @Override
            public void onAction(List<String> permissions) {
                // TODO what to do
                if (ActivityCompat.checkSelfPermission(TBSActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
                    if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) TBSActivity.this, Manifest.permission
                            .WRITE_EXTERNAL_STORAGE)) {
                        ToastUtil.show(CommonUtils.getString(R.string.str_permission_read_write));
                    }
                    //申请权限
                    ActivityCompat.requestPermissions((Activity) TBSActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x123);

                } else {
                    download();
                }
            }
        }).start();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0x123) {
                download();
            }
        }
    }

    public static void startAct(Context activity,
                                String fileName,
                                String url) {
        Intent intent = new Intent(activity, TBSActivity.class);
        intent.putExtra("fileName", fileName);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    private String fileName;
    private String url;

    private void getIntentData() {
        Intent intent = getIntent();
        fileName = intent.getStringExtra("fileName");
        url = intent.getStringExtra("url");
        if (!url.contains("http")) {
            url = AppConst.app2BaseUrl + url;
        }
    }

    NetWorkDialog netWorkDialog;
    public static final String fileFolder = "/sdcard/yanshiWork";
    long time;

    private void download() {
        time = System.currentTimeMillis();
        netWorkDialog = viewDelegate.getNetConnectDialog("正在加载");
        DownloadRequest req = new DownloadRequest(url
                , RequestMethod.GET, fileFolder,
                fileName, false, true);
        queue = NoHttp.newDownloadQueue();
        queue.add(1, req, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                exception.printStackTrace();
                ToastUtil.show("下载失败");
                netWorkDialog.dimessDialog(true);
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                netWorkDialog.showDialog(true);
            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                if (time + 1000 < System.currentTimeMillis()) {
                    time = System.currentTimeMillis();
                    ToastUtil.show("下载进度：" + progress + "%");
                }
            }

            @Override
            public void onFinish(int what, String filePath) {
                ToastUtil.show("下载完成");
                displayFile(filePath, fileName);
                netWorkDialog.dimessDialog(true);
                showType();
            }

            @Override
            public void onCancel(int what) {
                netWorkDialog.dimessDialog(true);
            }
        });
        queue.start();
    }

    private void showType() {
        if (url.contains(".jpg") || url.contains(".png")) {
            List<String> data = new ArrayList<>();
            data.add(url);
            UiHeplUtils.showBigImg(
                    this, data, 0
            );
        } else {
            CircleDialogHelper.initDefaultDialog(this,
                    "是否跳转系统工具打开?",
                    "文件地址:" + fileFolder + "/" + fileName,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OpenFileUtil.openFile(TBSActivity.this, fileFolder + "/" + fileName);
                        }
                    }).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (queue != null) {
            queue.stop();
        }
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
        }
        super.onDestroy();
    }

    private String tbsReaderTemp = Environment.getExternalStorageDirectory() + "/TbsReaderTemp";

    private void displayFile(String filePath, String fileName) {
        //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
        String bsReaderTemp = tbsReaderTemp;
        File bsReaderTempFile = new File(bsReaderTemp);
        if (!bsReaderTempFile.exists()) {
            boolean mkdir = bsReaderTempFile.mkdir();
            if (!mkdir) {
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("filePath", filePath);
        bundle.putString("tempPath", tbsReaderTemp);
        boolean result = mTbsReaderView.preOpen(getFileType(fileName), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
        } else {

        }
    }

    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            return str;
        }
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            return str;
        }

        str = paramString.substring(i + 1);
        return str;
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
