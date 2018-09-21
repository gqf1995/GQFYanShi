package com.gqfyanshi.entity.bean;

/**
 * Created by 郭青枫 on 2018/9/21 0021.
 */

public class MainLeftBean {
    /**
     * icon : null
     * pid : 1
     * permission : null
     * id : 3
     * title : 一般性公告
     * url : /notice/noticeIndex/1
     */

    private String icon;
    private int pid;
    private String permission;
    private int id;
    private String title;
    private String url;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
