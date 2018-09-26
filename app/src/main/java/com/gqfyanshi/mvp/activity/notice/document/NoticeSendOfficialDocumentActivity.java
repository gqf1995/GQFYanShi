package com.gqfyanshi.mvp.activity.notice.document;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticeSendOfficialDocumentAdapter;
import com.gqfyanshi.entity.bean.NoticeSendOfficialDocumentBean;
import com.gqfyanshi.mvp.databinder.NoticeSendOfficialDocumentBinder;
import com.gqfyanshi.mvp.delegate.NoticeSendOfficialDocumentDelegate;

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
        initToolbar(new ToolbarBuilder().setTitle("公文发送"));
        onRefush(1);
    }

    Class zlass = NoticeSendOfficialDocumentBean.class;

    private void onRefush(int pageNumber) {
        addRequest(binder.document_sendList(pageNumber, this));
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
