package com.gqfyanshi.mvp.activity.notice;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.NoticeSendOfficialDocumentBinder;
import com.gqfyanshi.mvp.delegate.NoticeSendOfficialDocumentDelegate;

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

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
