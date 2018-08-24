package com.gqfyanshi.mvp.activity.file;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

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
        viewDelegate.viewHolder.et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                    String content = viewDelegate.viewHolder.et_search.getText().toString();
                    if (TextUtils.isEmpty(content)) {
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });


    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }

}
