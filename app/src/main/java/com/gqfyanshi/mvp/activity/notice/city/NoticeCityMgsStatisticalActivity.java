package com.gqfyanshi.mvp.activity.notice.city;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticeMsgStatisticalAdapter;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.add.AddDocumentActivity;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeMgsStatisticalBinder;
import com.gqfyanshi.mvp.delegate.NoticeMgsStatisticalDelegate;

import java.util.ArrayList;
import java.util.List;

public class NoticeCityMgsStatisticalActivity extends BaseDataBindActivity<NoticeMgsStatisticalDelegate, NoticeMgsStatisticalBinder> {

    @Override
    protected Class<NoticeMgsStatisticalDelegate> getDelegateClass() {
        return NoticeMgsStatisticalDelegate.class;
    }

    @Override
    public NoticeMgsStatisticalBinder getDataBinder(NoticeMgsStatisticalDelegate viewDelegate) {
        return new NoticeMgsStatisticalBinder(viewDelegate);
    }

    private String type = "";

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();


        List<String> datas = new ArrayList<>();
        datas.add("全部");
        datas.add("约稿性发送");
        datas.add("约稿性接收");
        datas.add("信息统计");
        datas.add("信息发送");
        datas.add("工作信息发布");
        datas.add("工作信息接收");
        datas.add("要情汇报");
        datas.add("领导参阅");
        datas.add("紧急信息");
        datas.add("通知公告");
        viewDelegate.viewHolder.selectPeopleLayout1.setDatas(datas);
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


        viewDelegate.viewHolder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDocumentActivity.startAct(viewDelegate.getActivity(),
                        "11", "04"
                );
            }
        });
        initToolbar(new ToolbarBuilder().setTitle("信息统计"));
        onRefush(1);
        viewDelegate.viewHolder.tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefush(1);
            }
        });
    }

    Class zlass = DocumentBean.class;

    private void onRefush(int pageNumber) {
        QueryJsonBean queryJsonBean = new QueryJsonBean();
        queryJsonBean.setModelId("11");
        queryJsonBean.setType("04");
        // TODO: 2018/9/30 0030
        queryJsonBean.setCreatetime(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime());
        queryJsonBean.setUpdatetime(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime());
        addRequest(binder.infoNotice_sendList(queryJsonBean, pageNumber, this));
        viewDelegate.viewHolder.pageChangeView.setNowPage(pageNumber);
    }

    NoticeMsgStatisticalAdapter adapter;

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
            adapter = new NoticeMsgStatisticalAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    DocumentInfoActivity.startAct(viewDelegate.getActivity(),
                            adapter.getDatas().get(position).getId());
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
        }
    }

}
