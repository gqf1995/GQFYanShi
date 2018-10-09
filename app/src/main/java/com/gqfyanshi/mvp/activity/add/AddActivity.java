package com.gqfyanshi.mvp.activity.add;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.gqfyanshi.mvp.databinder.AddBinder;
import com.gqfyanshi.mvp.delegate.AddDelegate;

public class AddActivity extends BaseDataBindActivity<AddDelegate, AddBinder> {

    @Override
    protected Class<AddDelegate> getDelegateClass() {
        return AddDelegate.class;
    }

    @Override
    public AddBinder getDataBinder(AddDelegate viewDelegate) {
        return new AddBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("添加"));

    }


    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
        }
    }
    /**

     01 会议通知
     02 公文发送
     03 领导动态
     04 一般性公告


     01 党委
     02 党群口文件
     03 政府文件
     04 政府部门文件


     01 约稿性发送
     02 约稿性接收
     03 信息统计
     04 信息发送
     05 工作信息发布
     06 工作信息接收
     07 要情汇报
     08 领导参阅
     09 紧急信息
     10 通知公告

     1 通知公告
     4 公文流转
     7 市委信息工作
     16 政府信息工作
     26 市委公开信息
     32 政府公开信息
     40 邮件
     54 市委督察通知
     55 政府督察通知



     */
}
