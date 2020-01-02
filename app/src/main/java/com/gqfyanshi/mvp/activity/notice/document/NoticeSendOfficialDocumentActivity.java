package com.gqfyanshi.mvp.activity.notice.document;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.NoticeSendOfficialDocumentAdapter;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.add.AddAttrDocumentActivity;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeSendOfficialDocumentBinder;
import com.gqfyanshi.mvp.delegate.NoticeSendOfficialDocumentDelegate;

import java.util.ArrayList;
import java.util.List;

public class NoticeSendOfficialDocumentActivity extends BaseDataBindActivity<NoticeSendOfficialDocumentDelegate, NoticeSendOfficialDocumentBinder> {

    @Override
    protected Class<NoticeSendOfficialDocumentDelegate> getDelegateClass() {
        return NoticeSendOfficialDocumentDelegate.class;
    }


    @Override
    public NoticeSendOfficialDocumentBinder getDataBinder(NoticeSendOfficialDocumentDelegate viewDelegate) {
        return new NoticeSendOfficialDocumentBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.viewHolder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAttrDocumentActivity.startAct(viewDelegate.getActivity(),
                        "5");
            }
        });
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("公文发送"));
        viewDelegate.viewHolder.lin_add.setVisibility(View.GONE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        onRefush(viewDelegate.viewHolder.pageChangeView.getNowPage());
    }
    //        文件字号： name
    //        文件名称：title
    //        发布时间：createtime/updatetime
    //        文件属性：type （01 党委 02 党群口 03 政府文件 04 政府部门文件）
    public static void startAct(Activity activity,
                                String type) {
        Intent intent = new Intent(activity, NoticeSendOfficialDocumentActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    public static void startAct(Activity activity,
                                String name,
                                String title,
                                String createtime,
                                String updatetime,
                                String type) {
        Intent intent = new Intent(activity, NoticeSendOfficialDocumentActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("title", title);
        intent.putExtra("createtime", createtime);
        intent.putExtra("updatetime", updatetime);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }


    private String name = "";
    private String title = "";
    private String createtime = "";
    private String updatetime = "";
    private String type = "";

    private void getIntentData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        title = intent.getStringExtra("title");
        createtime = intent.getStringExtra("createtime");
        updatetime = intent.getStringExtra("updatetime");
        type = intent.getStringExtra("type");
        List<String> datas = new ArrayList<>();
        datas.add("全部");
        datas.add("党委");
        datas.add("党委文件");
        datas.add("政府文件");
        datas.add("政府部门文件");
        viewDelegate.viewHolder.selectPeopleLayout1.setDatas(datas,
                TextUtils.isEmpty(type) ? 0 : (Integer.parseInt(type.replace("0", "")))
        );
        viewDelegate.viewHolder.selectPeopleLayout1.setDefaultClickLinsener(new DefaultClickLinsener() {
            @Override
            public void onClick(View view, int position, Object item) {
                if (position == 0) {
                    type = "";
                } else {
                    type = "0" + position;
                }
            }
        });


        viewDelegate.viewHolder.tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = viewDelegate.viewHolder.et_attributes.getText().toString();
                title = viewDelegate.viewHolder.et_attributes2.getText().toString();
                createtime = viewDelegate.viewHolder.selectTimeLayout1.getSelectTime();
                updatetime = viewDelegate.viewHolder.selectTimeLayout2.getSelectTime();
                onRefush(1);
            }
        });
    }


    Class zlass = DocumentBean.class;

    private void onRefush(int pageNumber) {
        QueryJsonBean queryJsonBean = new QueryJsonBean();
        queryJsonBean.setModelId("5");
        queryJsonBean.setName(name);
        queryJsonBean.setTitle(title);
        queryJsonBean.setType(type);
        queryJsonBean.setCreatetime(createtime);
        queryJsonBean.setUpdatetime(updatetime);
        addRequest(binder.document_sendList(queryJsonBean, pageNumber, this));
        viewDelegate.viewHolder.pageChangeView.setNowPage(pageNumber);
    }

    NoticeSendOfficialDocumentAdapter adapter;

    private void initList(List list) {
        if (adapter == null) {
            viewDelegate.viewHolder.recycler_view.setLayoutManager(new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            viewDelegate.viewHolder.pageChangeView.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    onRefush(position);
                }
            });
            adapter = new NoticeSendOfficialDocumentAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    if (view.getId() == R.id.tv5) {
                        //详情
                        DocumentInfoActivity.startAct(
                                viewDelegate.getActivity(),
                                adapter.getDatas().get(position).getId(),
                                ""
                        );
                    } else {
                        //删除
                        addRequest(binder.document_delDocument(
                                adapter.getDatas().get(position).getId(),
                                NoticeSendOfficialDocumentActivity.this
                        ));
                    }
                }
            });
            viewDelegate.viewHolder.recycler_view.setAdapter(adapter);
        } else {
            adapter.setData(list);
        }
        viewDelegate.viewHolder.recycler_view.setVisibility(ListUtils.isEmpty(list) ? View.GONE : View.VISIBLE);
        viewDelegate.viewHolder.tv_nodata.setVisibility(!ListUtils.isEmpty(list) ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                List list = GsonUtil.getInstance().toList(data, "rows", zlass);
                initList(list);
                int total = Integer.parseInt(GsonUtil.getInstance().getValue(data, "total"));
                viewDelegate.viewHolder.pageChangeView.setMaxPage(total);
                break;
            case 0x124:
                onRefush(viewDelegate.viewHolder.pageChangeView.getNowPage());
                break;
        }
    }

}
