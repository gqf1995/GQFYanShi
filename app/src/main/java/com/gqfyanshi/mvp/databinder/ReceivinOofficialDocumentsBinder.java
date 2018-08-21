package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.ReceivinOofficialDocumentsDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class ReceivinOofficialDocumentsBinder extends BaseDataBind<ReceivinOofficialDocumentsDelegate> {

    public ReceivinOofficialDocumentsBinder(ReceivinOofficialDocumentsDelegate viewDelegate) {
        super(viewDelegate);
    }


}