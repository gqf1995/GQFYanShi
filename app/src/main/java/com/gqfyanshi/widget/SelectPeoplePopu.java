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
import com.fivefivelike.mybaselibrary.view.popupWindow.BasePopupWindow;
import com.gqfyanshi.R;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * Created by 郭青枫 on 2017/8/17.
 */

public class SelectPeoplePopu extends BasePopupWindow {

    public RelativeLayout contentView;

    public SelectPeoplePopu(Context context) {
        super(context);
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
        TreeNode root = TreeNode.root();
        //        for (int i = 0, n = bookMarks.size(); i < n; ++i) {
        //            TreeNode parent = new TreeNode(new IconTreeItem(
        //                    bookMarks.get(i).getChildren().size() > 0 ? false : true,
        //                    bookMarks.get(i).getPageIdx(),
        //                    bookMarks.get(i).getTitle()))
        //                    .setViewHolder(new BookMarkTreeItemHolder(context));
        //            getTree(bookMarks.get(i).getChildren(), parent);//递归获取所有书签
        //            root.addChild(parent);
        //        }

        TreeNode parent1 = new TreeNode(new IconTreeItem(
                false,
                "gqf1"));
        TreeNode child = new TreeNode(new IconTreeItem(
                true,
                "123"))
                .setViewHolder(new BookMarkTreeItemHolder(context));
        parent1.addChild(child);
        root.addChild(parent1);
        TreeNode parent2 = new TreeNode(new IconTreeItem(
                false,
                "gqf2"));
        TreeNode child2 = new TreeNode(new IconTreeItem(
                true,
                "123"))
                .setViewHolder(new BookMarkTreeItemHolder(context));
        parent2.addChild(child2);
        root.addChild(parent2);
        TreeNode parent3 = new TreeNode(new IconTreeItem(
                false,
                "gqf3"));
        TreeNode child3 = new TreeNode(new IconTreeItem(
                false,
                "123"))
                .setViewHolder(new BookMarkTreeItemHolder(context));
        TreeNode child4 = new TreeNode(new IconTreeItem(
                true,
                "123"))
                .setViewHolder(new BookMarkTreeItemHolder(context));
        child3.addChild(child4);
        parent3.addChild(child3);
        root.addChild(parent3);
        AndroidTreeView treeView = new AndroidTreeView(context, root);
        treeView.setDefaultAnimation(true);
        treeView.setDefaultViewHolder(BookMarkTreeItemHolder.class);
        contentView.addView(treeView.getView());
    }


    //    //递归获取所有书签节点
    //    public void getTree(List<PdfDocument.Bookmark> bookmarks, TreeNode parent) {
    //        for (int i = 0, n = bookmarks.size(); i < n; ++i) {
    //            TreeNode child = new TreeNode(new IconTreeItem(
    //                    bookmarks.get(i).hasChildren() ? false : true,
    //                    bookmarks.get(i).getPageIdx(), bookmarks.get(i).getTitle()))
    //                    .setViewHolder(new BookMarkTreeItemHolder(context));
    //            if (bookmarks.get(i).hasChildren()) {
    //                getTree(bookmarks.get(i).getChildren(), child);
    //            }
    //            parent.addChild(child);
    //        }
    //    }
    public static class BookMarkTreeItemHolder extends TreeNode.BaseNodeViewHolder<IconTreeItem> {
        private TextView tvValue;
        private ImageView arrowView;

        public BookMarkTreeItemHolder(Context context) {
            super(context);
        }

        @Override
        public View createNodeView(final TreeNode node, final IconTreeItem value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.layout_select_people_node, null, false);
            tvValue = (TextView) view.findViewById(R.id.node_value);
            tvValue.setText(value.text);

            final ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            iconView.setWillNotDraw(!value.isSelect);
            iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    value.isSelect = !value.isSelect;
                    v.setWillNotDraw(!value.isSelect);
                    select(node, value.isSelect);
                    selectFather(node, value.isSelect);
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
                    if (value instanceof IconTreeItem) {
                        if (!((IconTreeItem) value).isSelect) {
                            isSelectAll = false;
                        }
                    }
                }
                Object value = parent.getValue();
                if (value instanceof IconTreeItem) {
                    ((IconTreeItem) value).isSelect = isSelectAll;
                    parent.getViewHolder().getView().findViewById(R.id.icon).setWillNotDraw(!((IconTreeItem) value).isSelect);
                    selectFather(parent, isSelectAll);
                }
            }else {
                Object value = node.getValue();
                if (value instanceof IconTreeItem) {
                    ((IconTreeItem) value).isSelect = isSelect;
                    node.getViewHolder().getView().findViewById(R.id.icon).setWillNotDraw(!((IconTreeItem) value).isSelect);
                }
            }
        }


        private void select(TreeNode node, boolean isSelect) {
            if (!ListUtils.isEmpty(node.getChildren())) {
                for (int i = 0; i < node.getChildren().size(); i++) {
                    Object value = node.getChildren().get(i).getValue();
                    if (value instanceof IconTreeItem) {
                        ((IconTreeItem) value).isSelect = isSelect;
                        node.getChildren().get(i).getViewHolder().getView().findViewById(R.id.icon).setWillNotDraw(!((IconTreeItem) value).isSelect);
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

    /**
     * 书签节点类
     */
    public static class IconTreeItem {
        public boolean isLeaf;//是否是叶节点
        public boolean isSelect = false;//是否选中节点
        public String text;//节点内容

        public IconTreeItem(boolean isLeaf, String text) {
            this.isLeaf = isLeaf;
            this.text = text;
        }
    }
}
