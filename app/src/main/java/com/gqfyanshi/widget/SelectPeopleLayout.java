package com.gqfyanshi.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.TreeBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 郭青枫 on 2018/8/21 0021.
 */

public class SelectPeopleLayout extends FrameLayout {
    Context mContext;
    SelectPeoplePopu selectPeoplePopu;
    List<TreeBean> treeBean;
    StringBuffer selectPeople;

    public void setTreeBean(List<TreeBean> treeBean) {
        this.treeBean = treeBean;
    }

    int selectType = 0;//0 组选+单选 1 单选

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public SelectPeopleLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SelectPeopleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SelectPeopleLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public TextView et_attributes1;
    public LinearLayout lin_attributes1;

    private void initView(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = layoutInflater.inflate(R.layout.layout_select_people, null);
        this.et_attributes1 = (TextView) rootView.findViewById(R.id.et_attributes1);
        this.lin_attributes1 = (LinearLayout) rootView.findViewById(R.id.lin_attributes1);
        lin_attributes1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (treeBean != null) {
                    if (selectPeoplePopu == null) {
                        selectPeoplePopu = new SelectPeoplePopu(mContext);
                        selectPeoplePopu.setSelectType(selectType);
                        selectId = new StringBuffer();
                        if (!ListUtils.isEmpty(alreadySelectId)) {
                            selectPeoplePopu.setAlreadySelectId(alreadySelectId);
                        } else {
                            selectPeoplePopu.setAlreadySelectId(alreadySelectId = new ArrayList<>());
                        }
                        selectPeoplePopu.setTreeBean(treeBean);
                        selectPeoplePopu.setDefaultClickLinsener(new DefaultClickLinsener() {
                            @Override
                            public void onClick(View view, int position, Object item) {
                                showSelect();
                            }
                        });
                    }
                    selectPeoplePopu.show(v);
                }
            }
        });
        this.addView(rootView);
    }

    List<String> alreadySelectId;

    public void setAlreadySelectId(String id) {
        alreadySelectId = Arrays.asList(id.split(","));
        selectId = new StringBuffer(id);
    }

    public void setShowEdit(String text) {
        et_attributes1.setEnabled(false);
        et_attributes1.setText(text);
        lin_attributes1.setOnClickListener(null);
    }

    public void setShowEditEnabled(String text) {
        et_attributes1.setText(text);
    }

    private void showSelect() {
        selectPeople = new StringBuffer();
        for (int i = 0; i < treeBean.size(); i++) {
            getSelect(treeBean.get(i));
        }
        et_attributes1.setText(selectPeople.toString());
    }

    StringBuffer selectId;

    public boolean isSelectIdNull(){
        return selectId==null;
    }

    public String getSelectId() {

        if (!TextUtils.isEmpty(selectId.toString())) {
            return selectId.toString();
        }

        selectId = new StringBuffer();
        for (int i = 0; i < treeBean.size(); i++) {
            getId(treeBean.get(i));
        }
        if (!TextUtils.isEmpty(selectId.toString())) {
            selectId.deleteCharAt(selectId.length() - 1);
        }
        return selectId.toString();
    }

    private void getId(TreeBean treeBean) {
        if (treeBean.isSelect && ListUtils.isEmpty(treeBean.getChildNodes())) {
            selectId.append(treeBean.getId()).append(",");
        }
        for (int i = 0; i < treeBean.getChildNodes().size(); i++) {
            getId(treeBean.getChildNodes().get(i));
        }
    }


    private void getSelect(TreeBean treeBean) {
        if (treeBean.isSelect && ListUtils.isEmpty(treeBean.getChildNodes())) {
            selectPeople.append(treeBean.getText()).append(",");
        }
        for (int i = 0; i < treeBean.getChildNodes().size(); i++) {
            getSelect(treeBean.getChildNodes().get(i));
        }
    }

}
