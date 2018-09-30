package com.gqfyanshi.mvp.activity.notice.mail;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.adapter.NoticeOutBoxAdapter;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.mvp.activity.file.EmailInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeInboxBinder;
import com.gqfyanshi.mvp.delegate.NoticeInboxDelegate;

import java.util.List;

public class NoticeOutboxActivity extends BaseDataBindActivity<NoticeInboxDelegate, NoticeInboxBinder> {

    @Override
    protected Class<NoticeInboxDelegate> getDelegateClass() {
        return NoticeInboxDelegate.class;
    }

    @Override
    public NoticeInboxBinder getDataBinder(NoticeInboxDelegate viewDelegate) {
        return new NoticeInboxBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("发件箱"));
        onRefush(1);
    }

    Class zlass = DocumentBean.class;

    private void onRefush(int pageNumber) {
        addRequest(binder.email_getEmailSendList(pageNumber, this));
    }

    NoticeOutBoxAdapter adapter;

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
            adapter = new NoticeOutBoxAdapter(this, list);
            adapter.setDefaultClickLinsener(new DefaultClickLinsener() {
                @Override
                public void onClick(View view, int position, Object item) {
                    if (view.getId() == R.id.tv6) {
                        //详情
                        EmailInfoActivity.startAct(viewDelegate.getActivity(),
                                adapter.getDatas().get(position).getId());
                    } else {
                        //删除
                        addRequest(binder.email_emailDel(
                                adapter.getDatas().get(position).getId(),
                                NoticeOutboxActivity.this
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
