package com.gqfyanshi.mvp.activity;

import android.os.Bundle;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.mvp.databinder.ReceivinOofficialDocumentsBinder;
import com.gqfyanshi.mvp.delegate.ReceivinOofficialDocumentsDelegate;
import com.gqfyanshi.mvp.fragment.ReceivinOofficialDocumentsFragment;

public class ReceivinOofficialDocumentsActivity extends BaseDataBindActivity<ReceivinOofficialDocumentsDelegate, ReceivinOofficialDocumentsBinder> {

    @Override
    protected Class<ReceivinOofficialDocumentsDelegate> getDelegateClass() {
        return ReceivinOofficialDocumentsDelegate.class;
    }

    @Override
    public ReceivinOofficialDocumentsBinder getDataBinder(ReceivinOofficialDocumentsDelegate viewDelegate) {
        return new ReceivinOofficialDocumentsBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("公文接收"));

    }

    @Override
    protected void bindEvenListenerBuyState(Bundle savedInstanceState) {
        super.bindEvenListenerBuyState(savedInstanceState);
        initFragment(savedInstanceState == null && ListUtils.isEmpty(getSupportFragmentManager().getFragments()));

    }

    ReceivinOofficialDocumentsFragment receivinOofficialDocumentsFragment;
    private void initFragment(boolean isInit){
        viewDelegate.initAddFragment(R.id.fl_root, getSupportFragmentManager());
        if (isInit && ListUtils.isEmpty(getSupportFragmentManager().getFragments())) {
            viewDelegate.addFragment(receivinOofficialDocumentsFragment = new ReceivinOofficialDocumentsFragment(), "ReceivinOofficialDocumentsFragment", 0);
        } else {
            receivinOofficialDocumentsFragment = (ReceivinOofficialDocumentsFragment) viewDelegate.getFragmentByTag("ReceivinOofficialDocumentsFragment", 0);
        }
        viewDelegate.showFragment(0);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
