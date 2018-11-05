package com.gqfyanshi.entity.bean;

/**
 * Created by 郭青枫 on 2018/10/29 0029.
 */

public class PostilsBean {
    private int contentId;
    private String name;
    private String cTime;
    private int id;
    private int userId;
    private String postilAddress;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostilAddress() {
        return postilAddress;
    }

    public void setPostilAddress(String postilAddress) {
        this.postilAddress = postilAddress;
    }
}
