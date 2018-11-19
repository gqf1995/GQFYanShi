package com.gqfyanshi.mvp.activity.file;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.circledialog.CircleDialogHelper;
import com.circledialog.view.listener.OnInputClickListener;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.FileListAdapter;
import com.gqfyanshi.entity.bean.FileItemBean;
import com.gqfyanshi.mvp.databinder.FileCupboardBinder;
import com.gqfyanshi.mvp.delegate.FileCupboardDelegate;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class FileCupboardActivity extends BaseDataBindActivity<FileCupboardDelegate, FileCupboardBinder> {


    @Override
    protected Class<FileCupboardDelegate> getDelegateClass() {
        return FileCupboardDelegate.class;
    }

    @Override
    public FileCupboardBinder getDataBinder(FileCupboardDelegate viewDelegate) {
        return new FileCupboardBinder(viewDelegate);
    }

    String content;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle(TextUtils.isEmpty(fileItemBean == null ? "" : fileItemBean.getName()) ? "文件柜" : fileItemBean == null ? "" : fileItemBean.getName()));
        viewDelegate.viewHolder.et_search.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        viewDelegate.viewHolder.et_search.setHint("搜索文件或文件夹");
        viewDelegate.viewHolder.et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                    if (!TextUtils.isEmpty(viewDelegate.viewHolder.et_search.getText().toString())) {
                        content = viewDelegate.viewHolder.et_search.getText().toString();
                        onRefush();
                    }
                }
                return false;
            }
        });
        initlist(new ArrayList<FileItemBean>());
    }

    FileListAdapter adapter;

    private void initlist(List<FileItemBean> datas) {
        if (adapter == null) {
            adapter = new FileListAdapter(this, datas);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, final int position, Object item) {
                    if (view.getId() == R.id.tv_look) {
                        if (ObjectUtils.equals("01", adapter.getDatas().get(position).getType())) {
                            FileCupboardActivity.startAct(viewDelegate.getActivity(),
                                    adapter.getDatas().get(position));
                        } else if (ObjectUtils.equals("02", adapter.getDatas().get(position).getType())) {
                            TBSActivity.startAct(viewDelegate.getActivity(),
                                    adapter.getDatas().get(position).getName(),
                                    adapter.getDatas().get(position).getPath()
                            );
                        }
                    } else {
                        CircleDialogHelper.initDefaultDialog(viewDelegate.getActivity(),
                                "确定删除" + adapter.getDatas().get(position).getName() +
                                        "?", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addRequest(binder.fileCabinet_delFile(
                                                adapter.getDatas().get(position).getPath(),
                                                FileCupboardActivity.this
                                        ));
                                    }
                                }).show();
                    }
                }
            });
            viewDelegate.viewHolder.recycler_view.setLayoutManager(new LinearLayoutManager(this));
            viewDelegate.viewHolder.recycler_view.setAdapter(adapter);
        } else {
            adapter.setData(datas);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefush();
    }

    private void onRefush() {
        addRequest(binder.fileCabinet_getFileList(fileItemBean == null ? "" : fileItemBean.getPath(), this));
    }

    public static void startAct(Activity activity,
                                FileItemBean fileItemBean
    ) {
        Intent intent = new Intent(activity, FileCupboardActivity.class);
        intent.putExtra("fileItemBean", fileItemBean);
        activity.startActivity(intent);
    }

    FileItemBean fileItemBean;

    private void getIntentData() {
        Intent intent = getIntent();
        fileItemBean = intent.getParcelableExtra("fileItemBean");
        if (fileItemBean == null) {
            viewDelegate.viewHolder.iv_add_people.setVisibility(View.GONE);
            viewDelegate.viewHolder.iv_file.setVisibility(View.GONE);
        }
        viewDelegate.viewHolder.iv_add_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSharerActivity.startAct(viewDelegate.getActivity(),
                        fileItemBean == null ? "" : fileItemBean.getSendeeId(),
                        fileItemBean.getPath(), 0x123);
            }
        });
        viewDelegate.viewHolder.iv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleDialogHelper.initDefaultInputDialog(viewDelegate.getActivity(),
                        "新建文件夹", "请输入文件夹名称", "新建", new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)) {
                                    ToastUtil.show("请输入文件夹名称");
                                    return;
                                }
                                if (text.contains("/")) {
                                    ToastUtil.show("不能带有非法字符 / ");
                                    return;
                                }
                                addRequest(binder.fileCabinet_createFold(fileItemBean == null ? "" : fileItemBean.getPath(), text, FileCupboardActivity.this));
                            }
                        }).show();
            }
        });
        viewDelegate.viewHolder.iv_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("选择上传文件");
                new LFilePicker()
                        .withActivity(viewDelegate.getActivity())
                        .withRequestCode(1000)
                        .withMutilyMode(false)
                        .withChooseMode(true)
                        .start();
            }
        });
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                Flowable.fromIterable(GsonUtil.getInstance().toList(data, "fileVos", FileItemBean.class))
                        .filter(new Predicate<FileItemBean>() {
                            @Override
                            public boolean test(FileItemBean s) throws Exception {
                                if (TextUtils.isEmpty(content)) {
                                    return true;
                                }
                                return s.getName().contains(content);
                            }
                        })
                        .toList()
                        .subscribe(new Consumer<List<FileItemBean>>() {
                            @Override
                            public void accept(List<FileItemBean> marketQuotaResultBeans) throws Exception {
                                initlist(marketQuotaResultBeans);
                            }
                        });
                if (fileItemBean != null) {
                    fileItemBean.setSendeeId(GsonUtil.getInstance().getValue(data, "sendeeId"));
                }
                break;
            case 0x124:
                ToastUtil.show("操作成功");
                onRefush();
                break;
        }
    }

    String filePath;

    private void changeHeader(String path) {
        filePath = path;
        CircleDialogHelper.initDefaultDialog(
                viewDelegate.getActivity(),
                "是否上传文件?", filePath, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addRequest(binder.fileCabinet_upFile(filePath,fileItemBean == null ? "" : fileItemBean.getPath(),  FileCupboardActivity.this));
                    }
                }
        ).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                changeHeader(list.get(0));
            } else if (requestCode == 0x123) {
                if (!TextUtils.isEmpty(data.getStringExtra("selectId"))) {
                    fileItemBean.setSendeeId(data.getStringExtra("selectId"));
                }
            }
        }
    }
}
