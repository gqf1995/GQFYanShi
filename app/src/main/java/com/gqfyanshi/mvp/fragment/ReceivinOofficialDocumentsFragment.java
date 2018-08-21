package com.gqfyanshi.mvp.fragment;

import com.fivefivelike.mybaselibrary.base.BaseDataBindFragment;
import com.gqfyanshi.mvp.databinder.ReceivinOofficialDocumentsFragmentBinder;
import com.gqfyanshi.mvp.delegate.ReceivinOofficialDocumentsFragmentDelegate;

public class ReceivinOofficialDocumentsFragment extends BaseDataBindFragment<ReceivinOofficialDocumentsFragmentDelegate, ReceivinOofficialDocumentsFragmentBinder> {

    @Override
    protected Class<ReceivinOofficialDocumentsFragmentDelegate> getDelegateClass() {
        return ReceivinOofficialDocumentsFragmentDelegate.class;
    }

    @Override
    public ReceivinOofficialDocumentsFragmentBinder getDataBinder(ReceivinOofficialDocumentsFragmentDelegate viewDelegate) {
        return new ReceivinOofficialDocumentsFragmentBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
