package com.gqfyanshi.mvp.activity.notice.city;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticeMsgStatisticalAdapter;
import com.gqfyanshi.mvp.databinder.NoticeMgsStatisticalBinder;
import com.gqfyanshi.mvp.delegate.NoticeMgsStatisticalDelegate;

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


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("信息统计"));
        onRefush(1);
    }

    Class zlass = String.class;

    private void onRefush(int pageNumber) {
        addRequest(binder.infoNotice_sendList(pageNumber, this));
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
