package com.gqfyanshi.mvp.activity.notice.announcement;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticeMeetingAdapter;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeBinder;
import com.gqfyanshi.mvp.delegate.NoticeDelegate;

import java.util.List;

public class NoticeMeetingActivity extends BaseDataBindActivity<NoticeDelegate, NoticeBinder> {

    @Override
    protected Class<NoticeDelegate> getDelegateClass() {
        return NoticeDelegate.class;
    }

    @Override
    public NoticeBinder getDataBinder(NoticeDelegate viewDelegate) {
        return new NoticeBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("会议通知"));
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
        queryJsonBean.setModelId("3");
        queryJsonBean.setType("02");
        queryJsonBean.setTitle(viewDelegate.viewHolder.et_attributes.getText().toString());
        queryJsonBean.setCreatetime(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime());
        queryJsonBean.setUpdatetime(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime());
        addRequest(binder.notice_sendList(queryJsonBean, pageNumber, this));
    }

    NoticeMeetingAdapter adapter;

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
            adapter = new NoticeMeetingAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    DocumentInfoActivity.startAct(
                            viewDelegate.getActivity(),
                            adapter.getDatas().get(position).getId()
                    );
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
