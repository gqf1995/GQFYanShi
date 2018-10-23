package com.gqfyanshi.mvp.activity.askleave;

import android.text.TextUtils;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.entity.bean.LeaveTypeBean;
import com.gqfyanshi.entity.bean.TreeBean;
import com.gqfyanshi.mvp.databinder.AskLeaveBinder;
import com.gqfyanshi.mvp.delegate.AskLeaveDelegate;

import java.util.ArrayList;
import java.util.List;

public class AskLeaveActivity extends BaseDataBindActivity<AskLeaveDelegate, AskLeaveBinder> {

    @Override
    protected Class<AskLeaveDelegate> getDelegateClass() {
        return AskLeaveDelegate.class;
    }

    @Override
    public AskLeaveBinder getDataBinder(AskLeaveDelegate viewDelegate) {
        return new AskLeaveBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("请假"));
        addRequest(binder.leave_getLeaveType(this));
        addRequest(binder.leave_getUserTree(this));
        viewDelegate.viewHolder.recycler_view.setVisibility(View.GONE);
        viewDelegate.viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(viewDelegate.viewHolder.selectTimeLayout1.getSelectTime())) {
                    ToastUtil.show("请选择起始时间");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.selectTimeLayout2.getSelectTime())) {
                    ToastUtil.show("请选择终止时间");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes3.getText().toString())) {
                    ToastUtil.show("请输入所在单位");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_input1.getText().toString())) {
                    ToastUtil.show("请输入请假内容");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes4.getText().toString())) {
                    ToastUtil.show("请输入外出人姓名");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes5.getText().toString())) {
                    ToastUtil.show("请输入外出人职务");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes6.getText().toString())) {
                    ToastUtil.show("请输入外出人电话");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes7.getText().toString())) {
                    ToastUtil.show("请输入日常工作领导姓名");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes8.getText().toString())) {
                    ToastUtil.show("请输入日常工作领导职务");
                    return;
                }
                if (TextUtils.isEmpty(viewDelegate.viewHolder.et_attributes9.getText().toString())) {
                    ToastUtil.show("请输入日常工作领导电话");
                    return;
                }
                addRequest(binder.leave_saveLeave(
                        viewDelegate.viewHolder.et_attributes3.getText().toString(),
                        viewDelegate.viewHolder.selectTimeLayout1.getSelectTime().toString(),
                        viewDelegate.viewHolder.selectTimeLayout2.getSelectTime().toString(),
                        viewDelegate.viewHolder.et_attributes4.getText().toString(),
                        viewDelegate.viewHolder.et_attributes5.getText().toString(),
                        viewDelegate.viewHolder.et_attributes6.getText().toString(),
                        viewDelegate.viewHolder.et_attributes7.getText().toString(),
                        viewDelegate.viewHolder.et_attributes8.getText().toString(),
                        viewDelegate.viewHolder.et_attributes9.getText().toString(),
                        viewDelegate.viewHolder.et_input1.getText().toString(),
                        "",
                        viewDelegate.viewHolder.selectPeopleLayout.getSelectId(),
                        list.get(selectTypePosition).getDict_value(),
                        AskLeaveActivity.this));
            }
        });
    }

    List<LeaveTypeBean> list;
    int selectTypePosition = 0;

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x125:
                list = GsonUtil.getInstance().toList(data, LeaveTypeBean.class);
                List<String> datas = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    datas.add(list.get(i).getRemark());
                }
                viewDelegate.viewHolder.selectAttrLayout1.setDatas(datas);
                viewDelegate.viewHolder.selectAttrLayout1.setDefaultClickLinsener(new DefaultClickLinsener() {
                    @Override
                    public void onClick(View view, int position, Object item) {
                        selectTypePosition = position;
                    }
                });
                break;
            case 0x130:
                List<TreeBean> treeBean = GsonUtil.getInstance().toList(data, TreeBean.class);
                viewDelegate.viewHolder.selectPeopleLayout.setTreeBean(treeBean);
                break;
        }
    }

}
