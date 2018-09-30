package com.gqfyanshi.mvp.activity.notice;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.adapter.NoticeConferenceRoomReservationAdapter;
import com.gqfyanshi.entity.bean.DocumentBean;
import com.gqfyanshi.entity.bean.QueryJsonBean;
import com.gqfyanshi.mvp.activity.file.DocumentInfoActivity;
import com.gqfyanshi.mvp.databinder.NoticeConferenceRoomReservationBinder;
import com.gqfyanshi.mvp.delegate.NoticeConferenceRoomReservationDelegate;

import java.util.List;

public class NoticeConferenceRoomReservationActivity extends BaseDataBindActivity<NoticeConferenceRoomReservationDelegate, NoticeConferenceRoomReservationBinder> {

    @Override
    protected Class<NoticeConferenceRoomReservationDelegate> getDelegateClass() {
        return NoticeConferenceRoomReservationDelegate.class;
    }

    @Override
    public NoticeConferenceRoomReservationBinder getDataBinder(NoticeConferenceRoomReservationDelegate viewDelegate) {
        return new NoticeConferenceRoomReservationBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("会议室预约"));
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
        queryJsonBean.setPurpose(viewDelegate.viewHolder.et_attributes.getText().toString());
        queryJsonBean.setBeginTime(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime());
        queryJsonBean.setEndTime(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime());
        addRequest(binder.conference_getAppointmentInfoList(pageNumber, this));
    }

    NoticeConferenceRoomReservationAdapter adapter;

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
            adapter = new NoticeConferenceRoomReservationAdapter(this, list);
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
