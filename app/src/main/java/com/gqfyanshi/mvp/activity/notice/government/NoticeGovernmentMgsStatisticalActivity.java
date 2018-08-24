package com.gqfyanshi.mvp.activity.notice.government;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeMgsStatisticalBinder;
import com.gqfyanshi.mvp.delegate.NoticeMgsStatisticalDelegate;

public class NoticeGovernmentMgsStatisticalActivity extends BaseDataBindActivity<NoticeMgsStatisticalDelegate, NoticeMgsStatisticalBinder> {

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

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
