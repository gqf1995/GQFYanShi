package com.gqfyanshi.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.fivefivelike.mybaselibrary.view.popupWindow.BasePopupWindow;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.TreeBean;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

/**
 * Created by 郭青枫 on 2017/8/17.
 */

public class SelectPeoplePopu extends BasePopupWindow {

    public RelativeLayout contentView;
    int selectType = 0;//0 组选+单选 1 单选

    public void setSelectType(int selectType) {
        this.selectType = selectType;
    }

    public SelectPeoplePopu(Context context) {
        super(context);
    }

    DefaultClickLinsener defaultClickLinsener;

    public void setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
        this.defaultClickLinsener = defaultClickLinsener;
    }

    List<TreeBean> treeBean;

    public void setTreeBean(List<TreeBean> treeBean) {
        this.treeBean = treeBean;
        TreeNode root = TreeNode.root();

        for (int i = 0, n = treeBean.size(); i < n; ++i) {
            TreeNode parent = new TreeNode(treeBean.get(i))
                    .setViewHolder(new BookMarkTreeItemHolder(context)
                            .setSelectType(selectType)
                            .setDefaultClickLinsener(new DefaultClickLinsener() {
                                @Override
                                public void onClick(View view, int position, Object item) {
                                    if (defaultClickLinsener != null) {
                                        defaultClickLinsener.onClick(view, position, item);
                                    }
                                }
                            }));
            getTree(treeBean.get(i).getChildNodes(), parent);//递归获取所有书签
            root.addChild(parent);
        }

        AndroidTreeView treeView = new AndroidTreeView(context, root);
        treeView.setDefaultAnimation(true);
        treeView.setDefaultViewHolder(BookMarkTreeItemHolder.class);
        contentView.addView(treeView.getView());
    }

    @Override
    public int getLayoutId() {
        return R.layout.popu_select_people;
    }

    public void show(View view) {
        contentView.setMinimumWidth(view.getMeasuredWidth());
        showAsDropDown(view);
    }

    @Override
    public void initView() {
        setHeight((int) CommonUtils.getDimensionPixelSize(R.dimen.trans_350px));
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.contentView = (RelativeLayout) findViewById(R.id.contentView);

    }


    //递归获取所有书签节点
    public void getTree(List<TreeBean> bookmarks, TreeNode parent) {
        for (int i = 0, n = bookmarks.size(); i < n; ++i) {
            TreeNode child = new TreeNode(bookmarks.get(i))
                    .setViewHolder(new BookMarkTreeItemHolder(context)
                            .setSelectType(selectType)
                            .setDefaultClickLinsener(new DefaultClickLinsener() {
                                @Override
                                public void onClick(View view, int position, Object item) {
                                    if (defaultClickLinsener != null) {
                                        defaultClickLinsener.onClick(view, position, item);
                                    }
                                }
                            }));
            if (!ListUtils.isEmpty(bookmarks.get(i).getChildNodes())) {
                getTree(bookmarks.get(i).getChildNodes(), child);
            }
            parent.addChild(child);
        }
    }

    public static class BookMarkTreeItemHolder extends TreeNode.BaseNodeViewHolder<TreeBean> {
        private TextView tvValue;
        private ImageView arrowView;

        int selectType = 0;//0 组选+单选 1 单选
        DefaultClickLinsener defaultClickLinsener;

        public BookMarkTreeItemHolder setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
            this.defaultClickLinsener = defaultClickLinsener;
            return this;
        }

        public BookMarkTreeItemHolder setSelectType(int selectType) {
            this.selectType = selectType;
            return this;
        }

        public BookMarkTreeItemHolder(Context context) {
            super(context);
        }

        @Override
        public View createNodeView(final TreeNode node, final TreeBean value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.layout_select_people_node, null, false);
            tvValue = (TextView) view.findViewById(R.id.node_value);
            tvValue.setText(value.text);

            final ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            iconView.setWillNotDraw(!value.isSelect);


            iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectType == 0) {
                        value.isSelect = !value.isSelect;
                        v.setWillNotDraw(!value.isSelect);
                        select(node, value.isSelect);
                        selectFather(node, value.isSelect);
                        if (defaultClickLinsener != null) {
                            defaultClickLinsener.onClick(v, 0, value);
                        }
                    } else if (selectType == 1) {
                        if (ListUtils.isEmpty(value.getChildNodes())) {
                            value.isSelect = !value.isSelect;
                            v.setWillNotDraw(!value.isSelect);
                            select(node, value.isSelect);
                            if (defaultClickLinsener != null) {
                                defaultClickLinsener.onClick(v, 0, value);
                            }
                        }
                    }
                }
            });
            iconView.getParent().requestDisallowInterceptTouchEvent(true);
            arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
            if (value.isLeaf) {
                arrowView.setVisibility(View.INVISIBLE);
            } else {
                arrowView.setVisibility(View.VISIBLE);
            }

            return view;
        }

        private void selectFather(TreeNode node, boolean isSelect) {
            TreeNode parent = node.getParent();
            if (parent != null) {
                boolean isSelectAll = true;
                for (int i = 0; i < parent.getChildren().size(); i++) {
                    Object value = parent.getChildren().get(i).getValue();
                    if (value instanceof TreeBean) {
                        if (!((TreeBean) value).isSelect) {
                            isSelectAll = false;
                        }
                    }
                }
                Object value = parent.getValue();
                if (value instanceof TreeBean) {
                    ((TreeBean) value).isSelect = isSelectAll;
                    parent.getViewHolder().getView().findViewById(R.id.icon).setWillNotDraw(!((TreeBean) value).isSelect);
                    selectFather(parent, isSelectAll);
                }
            } else {
                Object value = node.getValue();
                if (value instanceof TreeBean) {
                    ((TreeBean) value).isSelect = isSelect;
                    node.getViewHolder().getView().findViewById(R.id.icon).setWillNotDraw(!((TreeBean) value).isSelect);
                }
            }
        }


        private void select(TreeNode node, boolean isSelect) {
            if (!ListUtils.isEmpty(node.getChildren())) {
                for (int i = 0; i < node.getChildren().size(); i++) {
                    Object value = node.getChildren().get(i).getValue();
                    if (value instanceof TreeBean) {
                        ((TreeBean) value).isSelect = isSelect;
                        node.getChildren().get(i).getViewHolder().getView().findViewById(R.id.icon)
                                .setWillNotDraw(!((TreeBean) value).isSelect);
                    }
                    select(node.getChildren().get(i), isSelect);
                }
            }
        }

        @Override
        public void toggle(boolean active) {
            if (mNode.isLeaf()) {//如果是叶节点，隐藏展开或收起图片
                arrowView.setVisibility(View.INVISIBLE);
            } else {
                arrowView.setVisibility(View.VISIBLE);
                if (active) {
                    arrowView.setRotation(90);
                } else {
                    arrowView.setRotation(0);
                }
            }
        }

        @Override
        public int getContainerStyle() {
            return R.style.MyTreeNodeStyle;
        }

    }


}
