package com.gqfyanshi.mvp.activity.file;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.FileCupboardBinder;
import com.gqfyanshi.mvp.delegate.FileCupboardDelegate;

public class FileCupboardActivity extends BaseDataBindActivity<FileCupboardDelegate, FileCupboardBinder> {

    @Override
    protected Class<FileCupboardDelegate> getDelegateClass() {
        return FileCupboardDelegate.class;
    }

    @Override
    public FileCupboardBinder getDataBinder(FileCupboardDelegate viewDelegate) {
        return new FileCupboardBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("文件柜"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
