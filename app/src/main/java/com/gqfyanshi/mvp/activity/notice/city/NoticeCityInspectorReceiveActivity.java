package com.gqfyanshi.mvp.activity.notice.city;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.NoticeInspectorReceiveAdapter;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeInspectorReceiveBinder;
import com.gqfyanshi.mvp.delegate.NoticeInspectorReceiveDelegate;

import java.util.List;

public class NoticeCityInspectorReceiveActivity extends BaseDataBindActivity<NoticeInspectorReceiveDelegate, NoticeInspectorReceiveBinder> {

    @Override
    protected Class<NoticeInspectorReceiveDelegate> getDelegateClass() {
        return NoticeInspectorReceiveDelegate.class;
    }

    @Override
    public NoticeInspectorReceiveBinder getDataBinder(NoticeInspectorReceiveDelegate viewDelegate) {
        return new NoticeInspectorReceiveBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

        initToolbar(new ToolbarBuilder().setTitle("督查通知接收"));
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
        QueryJsonBean queryJsonBean=new QueryJsonBean();
        queryJsonBean.setModelId("26");
        queryJsonBean.setTitle(viewDelegate.viewHolder.et_attributes.getText().toString());
        addRequest(binder.overSeer_getOverSeerReceiveList(queryJsonBean,pageNumber, this));
        viewDelegate.viewHolder.pageChangeView.setNowPage(pageNumber);
    }

    NoticeInspectorReceiveAdapter adapter;

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
            adapter = new NoticeInspectorReceiveAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    if (view.getId() == R.id.tv5) {
                        DocumentInfoActivity.startAct(viewDelegate.getActivity(),
                                adapter.getDatas().get(position).getId());
                    } else {
                        addRequest(binder.overSeer_overSeerDel(
                                adapter.getDatas().get(position).getId(),
                                NoticeCityInspectorReceiveActivity.this
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
