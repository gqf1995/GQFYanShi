package com.gqfyanshi.mvp.databinder;

import com.gqfyanshi.mvp.delegate.NoticeSendOfficialDocumentDelegate;
import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;

import io.reactivex.disposables.Disposable;

public class NoticeSendOfficialDocumentBinder extends BaseDataBind<NoticeSendOfficialDocumentDelegate> {

    public NoticeSendOfficialDocumentBinder(NoticeSendOfficialDocumentDelegate viewDelegate) {
        super(viewDelegate);
    }


}