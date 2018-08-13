package com.gqfyanshi.mvp.activity.notice;

import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.mvp.databinder.NoticeBinder;
import com.gqfyanshi.mvp.delegate.NoticeDelegate;

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
        viewDelegate.viewHolder.pageChangeView.setDefaultClickLinsener(new DefaultClickLinsener() {
            @Override
            public void onClick(View view, int position, Object item) {

            }
        });
        viewDelegate.viewHolder.pageChangeView.setMaxPage(120);
    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
