package com.gqfyanshi.mvp.activity.notice.government;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticeCityManuscriptsSendAdapter;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.add.AddDocumentActivity;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeEmergencyBinder;
import com.gqfyanshi.mvp.delegate.NoticeEmergencyDelegate;

import java.util.List;

public class NoticeGovernmentSendActivity extends BaseDataBindActivity<NoticeEmergencyDelegate, NoticeEmergencyBinder> {

    @Override
    protected Class<NoticeEmergencyDelegate> getDelegateClass() {
        return NoticeEmergencyDelegate.class;
    }

    @Override
    public NoticeEmergencyBinder getDataBinder(NoticeEmergencyDelegate viewDelegate) {
        return new NoticeEmergencyBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.viewHolder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDocumentActivity.startAct(viewDelegate.getActivity(),
                        "8","01"
                );
            }
        });
        initToolbar(new ToolbarBuilder().setTitle("约稿性发送"));
        onRefush(1);
        viewDelegate.viewHolder.tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefush(1);
            }
        });
    }

    Class zlass = String.class;

    private void onRefush(int pageNumber) {
        QueryJsonBean queryJsonBean = new QueryJsonBean();
        queryJsonBean.setModelId("8");
        queryJsonBean.setType("01");
        queryJsonBean.setTitle(viewDelegate.viewHolder.et_attributes.getText().toString());
        queryJsonBean.setCreatetime(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime());
        queryJsonBean.setUpdatetime(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime());
        addRequest(binder.conventional_sendList(queryJsonBean, pageNumber, this));
        viewDelegate.viewHolder.pageChangeView.setNowPage(pageNumber);
    }

    NoticeCityManuscriptsSendAdapter adapter;

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
            adapter = new NoticeCityManuscriptsSendAdapter(this, list);
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
