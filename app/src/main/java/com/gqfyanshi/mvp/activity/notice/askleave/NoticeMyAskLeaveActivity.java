package com.gqfyanshi.mvp.activity.notice.askleave;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.NoticeAskleaveAdapter;
import com.gqfyanshi.entity.bean.AskleaveBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.entity.bean.UserLoginBean;
import com.gqfyanshi.mvp.activity.askleave.AskLeaveResultActivity;
import com.gqfyanshi.mvp.databinder.NoticeAskLeaveBinder;
import com.gqfyanshi.mvp.delegate.NoticeAskLeaveDelegate;

import java.util.List;

public class NoticeMyAskLeaveActivity extends BaseDataBindActivity<NoticeAskLeaveDelegate, NoticeAskLeaveBinder> {

    @Override
    protected Class<NoticeAskLeaveDelegate> getDelegateClass() {
        return NoticeAskLeaveDelegate.class;
    }

    @Override
    public NoticeAskLeaveBinder getDataBinder(NoticeAskLeaveDelegate viewDelegate) {
        return new NoticeAskLeaveBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("我的请假"));
        onRefush(1);
        viewDelegate.viewHolder.tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefush(1);
            }
        });
    }

    Class zlass = AskleaveBean.class;

    private void onRefush(int pageNumber) {
        QueryJsonBean queryJsonBean = new QueryJsonBean();
        queryJsonBean.setUserId(UserLoginBean.getUserId());
        queryJsonBean.setCreatetime(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime());
        queryJsonBean.setUpdatetime(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime());
        addRequest(binder.leave_getLeaveList(queryJsonBean, pageNumber, this));
        viewDelegate.viewHolder.pageChangeView.setNowPage(pageNumber);
    }

    NoticeAskleaveAdapter adapter;

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
            adapter = new NoticeAskleaveAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    if (view.getId() == R.id.tv5) {
                        AskLeaveResultActivity.startAct(viewDelegate.getActivity(), adapter.getDatas().get(position).getId(), true);
                    } else {
                        addRequest(binder.leave_delLeave(adapter.getDatas().get(position).getId(), NoticeMyAskLeaveActivity.this));
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
