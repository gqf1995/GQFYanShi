package com.gqfyanshi.mvp.activity.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.CommonUtils;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.mybaselibrary.utils.ListUtils;
import com.fivefivelike.mybaselibrary.utils.callback.DefaultClickLinsener;
import com.gqfyanshi.R;
import com.gqfyanshi.entity.bean.TreeBean;
import com.gqfyanshi.mvp.databinder.AddSharerBinder;
import com.gqfyanshi.mvp.delegate.AddSharerDelegate;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.Arrays;
import java.util.List;

public class AddSharerActivity extends BaseDataBindActivity<AddSharerDelegate, AddSharerBinder> {

    @Override
    protected Class<AddSharerDelegate> getDelegateClass() {
        return AddSharerDelegate.class;
    }

    @Override
    public AddSharerBinder getDataBinder(AddSharerDelegate viewDelegate) {
        return new AddSharerBinder(viewDelegate);
    }

    DefaultClickLinsener defaultClickLinsener = new DefaultClickLinsener() {
        @Override
        public void onClick(View view, int position, Object item) {

        }
    };

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle("添加共享成员").setmRightImg1("保存成员"));
        addRequest(binder.leave_getUserTree(this));
    }

    @Override
    protected void clickRightIv() {
        super.clickRightIv();
        if (!ListUtils.isEmpty(treeBean)) {
            addRequest(binder.fileCabinet_editFoldSeedeeId(path,
                    getSelectId(), this
            ));
        }
    }

    public static void startAct(Activity activity,
                                String id,
                                String path,int code) {
        Intent intent = new Intent(activity, AddSharerActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("path", path);
        activity.startActivityForResult(intent,code);
    }

    private String id;
    private String path;
    List<String> selectId;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        path = intent.getStringExtra("path");
        selectId = Arrays.asList(id.split(","));
    }

    List<TreeBean> treeBean;

    StringBuffer selectIdString;

    public String getSelectId() {
        selectIdString = new StringBuffer();
        for (int i = 0; i < treeBean.size(); i++) {
            getId(treeBean.get(i));
        }
        if (!TextUtils.isEmpty(selectId.toString())) {
            selectIdString.deleteCharAt(selectIdString.length() - 1);
        }
        return selectIdString.toString();
    }

    private void getId(TreeBean treeBean) {
        if (treeBean.isSelect && ListUtils.isEmpty(treeBean.getChildNodes())) {
            selectIdString.append(treeBean.getId()).append(",");
        }
        for (int i = 0; i < treeBean.getChildNodes().size(); i++) {
            getId(treeBean.getChildNodes().get(i));
        }
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x130:
                treeBean = GsonUtil.getInstance().toList(data, TreeBean.class);
                setTreeBean(treeBean);
                break;
            case 0x131:
                Intent intent = new Intent();
                intent.putExtra("selectId", selectIdString.toString());
                setResult(RESULT_OK, intent);
                onBackPressed();
                break;
        }
    }


    public void setTreeBean(List<TreeBean> treeBean) {
        this.treeBean = treeBean;
        TreeNode root = TreeNode.root();
        for (int i = 0, n = treeBean.size(); i < n; ++i) {
            TreeNode parent = new TreeNode(treeBean.get(i))
                    .setViewHolder(new MarkTreeItemHolder(viewDelegate.getActivity())
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

        AndroidTreeView treeView = new AndroidTreeView(viewDelegate.getActivity(), root);
        treeView.setDefaultAnimation(true);
        treeView.setDefaultViewHolder(MarkTreeItemHolder.class);
        viewDelegate.viewHolder.contentView.addView(treeView.getView());
    }

    //递归获取所有书签节点
    public void getTree(List<TreeBean> bookmarks, TreeNode parent) {
        for (int i = 0, n = bookmarks.size(); i < n; ++i) {

            for (int j = 0; j < selectId.size(); j++) {
                if (ObjectUtils.equals(selectId.get(j), bookmarks.get(i).getId())) {
                    bookmarks.get(i).isSelect = true;
                    break;
                }
            }

            TreeNode child = new TreeNode(bookmarks.get(i))
                    .setViewHolder(new MarkTreeItemHolder(viewDelegate.getActivity())
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


    public static class MarkTreeItemHolder extends TreeNode.BaseNodeViewHolder<TreeBean> {
        private TextView tvValue;
        private ImageView arrowView;

        int selectType = 0;//0 组选+单选 1 单选
        DefaultClickLinsener defaultClickLinsener;

        public MarkTreeItemHolder setDefaultClickLinsener(DefaultClickLinsener defaultClickLinsener) {
            this.defaultClickLinsener = defaultClickLinsener;
            return this;
        }

        public MarkTreeItemHolder setSelectType(int selectType) {
            this.selectType = selectType;
            return this;
        }

        public MarkTreeItemHolder(Context context) {
            super(context);
        }

        @Override
        public View createNodeView(final TreeNode node, final TreeBean value) {
            final LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.layout_select_people_node, null, false);
            tvValue = (TextView) view.findViewById(R.id.node_value);
            tvValue.setText(value.text);
            tvValue.setTextSize(TypedValue.COMPLEX_UNIT_PX, CommonUtils.getDimensionPixelSize(R.dimen.trans_18px));
            final ImageView iconView = (ImageView) view.findViewById(R.id.icon);
            iconView.setImageResource(value.isSelect ? R.drawable.weixuanzhongkaobei : R.drawable.weixuanzhong2);
            iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v instanceof ImageView) {
                        if (selectType == 0) {
                            value.isSelect = !value.isSelect;
                            ((ImageView) v).setImageResource(value.isSelect ? R.drawable.weixuanzhongkaobei : R.drawable.weixuanzhong2);
                            select(node, value.isSelect);
                            selectFather(node, value.isSelect);
                            if (defaultClickLinsener != null) {
                                defaultClickLinsener.onClick(v, 0, value);
                            }
                        } else if (selectType == 1) {
                            if (ListUtils.isEmpty(value.getChildNodes())) {
                                value.isSelect = !value.isSelect;
                                ((ImageView) v).setImageResource(value.isSelect ? R.drawable.weixuanzhongkaobei : R.drawable.weixuanzhong2);
                                select(node, value.isSelect);
                                if (defaultClickLinsener != null) {
                                    defaultClickLinsener.onClick(v, 0, value);
                                }
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
                    ((ImageView) parent.getViewHolder().getView().findViewById(R.id.icon))
                            .setImageResource(((TreeBean) value).isSelect ? R.drawable.weixuanzhongkaobei : R.drawable.weixuanzhong2);
                    selectFather(parent, isSelectAll);
                }
            } else {
                Object value = node.getValue();
                if (value instanceof TreeBean) {
                    ((TreeBean) value).isSelect = isSelect;
                    ((ImageView) node.getViewHolder().getView().findViewById(R.id.icon))
                            .setImageResource(((TreeBean) value).isSelect ? R.drawable.weixuanzhongkaobei : R.drawable.weixuanzhong2);
                }
            }
        }


        private void select(TreeNode node, boolean isSelect) {
            if (!ListUtils.isEmpty(node.getChildren())) {
                for (int i = 0; i < node.getChildren().size(); i++) {
                    Object value = node.getChildren().get(i).getValue();
                    if (value instanceof TreeBean) {
                        ((TreeBean) value).isSelect = isSelect;
                        ((ImageView) node.getChildren().get(i).getViewHolder().getView().findViewById(R.id.icon))
                                .setImageResource(((TreeBean) value).isSelect ? R.drawable.weixuanzhongkaobei : R.drawable.weixuanzhong2);
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
