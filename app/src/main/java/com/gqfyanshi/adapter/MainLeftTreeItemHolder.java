package com.gqfyanshi.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.MainLeftBean;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by 郭青枫 on 2018/9/21 0021.
 */

public class MainLeftTreeItemHolder extends TreeNode.BaseNodeViewHolder<MainLeftBean> {

    private ImageView iv_module1;
    private TextView tv_module1;
    private LinearLayout lin_module1;

    public MainLeftTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, final MainLeftBean value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View rootView = inflater.inflate(R.layout.layout_main_left_node, null, false);

        this.iv_module1 = (ImageView) rootView.findViewById(R.id.iv_module1);
        this.tv_module1 = (TextView) rootView.findViewById(R.id.tv_module1);
        this.lin_module1 = (LinearLayout) rootView.findViewById(R.id.lin_module1);

        tv_module1.setText(value.getTitle());

        if (value.getPid() == 0) {
            tv_module1.setTextColor(CommonUtils.getColor(R.color.color_font1));
            tv_module1.setTextSize(TypedValue.COMPLEX_UNIT_PX, CommonUtils.getDimensionPixelSize(R.dimen.text_trans_18px));

        } else {
            tv_module1.setTextColor(CommonUtils.getColor(R.color.color_font2));
            tv_module1.setTextSize(TypedValue.COMPLEX_UNIT_PX, CommonUtils.getDimensionPixelSize(R.dimen.text_trans_16px));

        }

        if(ListUtils.isEmpty(node.getChildren())){
            iv_module1.setImageResource(R.drawable.wenjain);
        }else {
            iv_module1.setImageResource(R.drawable.xiangqing);
        }

        return rootView;
    }


    @Override
    public void toggle(boolean active) {
        if (mNode.isLeaf()) {//如果是叶节点，隐藏展开或收起图片
        } else {
            if (active) {
                iv_module1.setRotation(90);
            } else {
                iv_module1.setRotation(0);
            }
        }
    }

    @Override
    public int getContainerStyle() {
        return R.style.MyTreeNodeStyle;
    }

}