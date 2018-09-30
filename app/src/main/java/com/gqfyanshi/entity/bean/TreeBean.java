package com.gqfyanshi.entity.bean;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/9/30 0030.
 */

public class TreeBean {
    /**
     * img : null
     * isexpand : false
     * parentnodes : 0
     * childNodes : null
     * hasChildren : true
     * id : 1
     * showcheck : true
     * text : 偃师
     * complete : true
     * checkstate : 0
     * value : null
     */

    List<TreeBean> ChildNodes;

    private String img;
    private boolean isexpand;
    private String parentnodes;
    private Object childNodes;
    private boolean hasChildren;
    private String id;
    private boolean showcheck;
    private boolean complete;
    private int checkstate;
    private String value;

    public boolean isLeaf;//是否是叶节点
    public boolean isSelect = false;//是否选中节点
    public String text;//节点内容




    public List<TreeBean> getChildNodes() {
        return ChildNodes;
    }

    public void setChildNodes(Object childNodes) {
        this.childNodes = childNodes;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isShowcheck() {
        return showcheck;
    }

    public void setShowcheck(boolean showcheck) {
        this.showcheck = showcheck;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getCheckstate() {
        return checkstate;
    }

    public void setCheckstate(int checkstate) {
        this.checkstate = checkstate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setChildNodes(List<TreeBean> childNodes) {
        ChildNodes = childNodes;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIsexpand() {
        return isexpand;
    }

    public void setIsexpand(boolean isexpand) {
        this.isexpand = isexpand;
    }

    public String getParentnodes() {
        return parentnodes;
    }

    public void setParentnodes(String parentnodes) {
        this.parentnodes = parentnodes;
    }
}
