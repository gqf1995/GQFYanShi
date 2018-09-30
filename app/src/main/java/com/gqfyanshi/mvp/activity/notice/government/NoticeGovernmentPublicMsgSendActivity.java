package com.gqfyanshi.mvp.activity.notice.government;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticePublicMsgSendAdapter;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.add.AddPublicInformationActivity;
import com.gqfyanshi.mvp.databinder.NoticePublicMsgSendBinder;
import com.gqfyanshi.mvp.delegate.NoticePublicMsgSendDelegate;

import java.util.List;

public class NoticeGovernmentPublicMsgSendActivity extends BaseDataBindActivity<NoticePublicMsgSendDelegate, NoticePublicMsgSendBinder> {

    @Override
    protected Class<NoticePublicMsgSendDelegate> getDelegateClass() {
        return NoticePublicMsgSendDelegate.class;
    }

    @Override
    public NoticePublicMsgSendBinder getDataBinder(NoticePublicMsgSendDelegate viewDelegate) {
        return new NoticePublicMsgSendBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.viewHolder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewDelegate.getActivity(), AddPublicInformationActivity.class));
            }
        });
        initToolbar(new ToolbarBuilder().setTitle("公开信息发送"));
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
        QueryJsonBean queryJsonBean=new QueryJsonBean();
        queryJsonBean.setModelId("32");
        queryJsonBean.setTitle(viewDelegate.viewHolder.et_attributes.getText().toString());
        addRequest(binder.workInfo_getWorkInfoSendList(queryJsonBean,pageNumber, this));
    }

    NoticePublicMsgSendAdapter adapter;

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
            adapter = new NoticePublicMsgSendAdapter(this, list);
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
