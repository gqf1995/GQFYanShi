package com.gqfyanshi.mvp.activity.notice.approval;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.NoticeApprovalAdapter;
import com.gqfyanshi.entity.bean.ApprovalBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.approval.ApprovalDetailActivity;
import com.gqfyanshi.mvp.databinder.NoticeApprovalBinder;
import com.gqfyanshi.mvp.delegate.NoticeApprovalDelegate;

import java.util.List;

public class NoticeApprovalActivity extends BaseDataBindActivity<NoticeApprovalDelegate, NoticeApprovalBinder> {

    @Override
    protected Class<NoticeApprovalDelegate> getDelegateClass() {
        return NoticeApprovalDelegate.class;
    }

    @Override
    public NoticeApprovalBinder getDataBinder(NoticeApprovalDelegate viewDelegate) {
        return new NoticeApprovalBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("我的审批"));
        onRefush(1);
        viewDelegate.viewHolder.tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefush(1);
            }
        });
    }

    Class zlass = ApprovalBean.class;

    private void onRefush(int pageNumber) {
        QueryJsonBean queryJsonBean = new QueryJsonBean();
        queryJsonBean.setName(viewDelegate.viewHolder.et_attributes.getText().toString());
        queryJsonBean.setTitle(viewDelegate.viewHolder.et_attributes2.getText().toString());
        queryJsonBean.setCreatetime(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime());
        queryJsonBean.setUpdatetime(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime());
        addRequest(binder.fileSign_getFileSignList(queryJsonBean, pageNumber, this));
        viewDelegate.viewHolder.pageChangeView.setNowPage(pageNumber);
    }

    NoticeApprovalAdapter adapter;

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
            adapter = new NoticeApprovalAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    if(view.getId()== R.id.tv6){
                        ApprovalDetailActivity.startAct(viewDelegate.getActivity(), adapter.getDatas().get(position).getId(), false);
                    }else {
                        addRequest(binder.fileSign_delFileSign(adapter.getDatas().get(position).getId(),NoticeApprovalActivity.this));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            onRefush( viewDelegate.viewHolder.pageChangeView.getNowPage());
        }
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
                onRefush( viewDelegate.viewHolder.pageChangeView.getNowPage());
                break;
        }
    }

}
