package com.gqfyanshi.entity.bean;

import java.util.List;

/**
 * Created by 郭青枫 on 2018/10/20.
 */

public class AskleaveBean extends DocumentBean {

    /**
     * managerPosition : 主管领导职位
     * reason : 请假缘由
     * outLPhoneNum : 外出领导手机号
     * outLName : 外出领导姓名
     * cTime : 2018-10-16 17:36:51
     * managerName : 主管领导姓名
     * userName : 人大办
     * userId : 10
     * managerPhoneNum : 主管领导手机号
     * outLPosition : 外出领导职位
     * startTime : 2018-10-16 00:00:00
     * id : 3
     * endTime : 2018-10-18 00:00:00
     * department : 33,10,64,65,19,41,46,18,74,42,10
     */

    private String managerPosition;
    private String reason;
    private String outLPhoneNum;
    private String outLName;
    private String cTime;
    private String managerName;
    private String userName;
    private int userId;
    private String managerPhoneNum;
    private String outLPosition;
    private String startTime;
    private String endTime;
    private String department;

    /**
     * id : 2
     * postils : [{"contentId":2,"name":"人大办","id":10,"postilAddress":"upload/leave/10.sql"},{"contentId":2,"name":"人大办","id":10,"postilAddress":"upload/leave/10.sql"}]
     */

    private List<PostilsBean> postils;

    public String getManagerPosition() {
        return managerPosition;
    }

    public void setManagerPosition(String managerPosition) {
        this.managerPosition = managerPosition;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOutLPhoneNum() {
        return outLPhoneNum;
    }

    public void setOutLPhoneNum(String outLPhoneNum) {
        this.outLPhoneNum = outLPhoneNum;
    }

    public String getOutLName() {
        return outLName;
    }

    public void setOutLName(String outLName) {
        this.outLName = outLName;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getManagerPhoneNum() {
        return managerPhoneNum;
    }

    public void setManagerPhoneNum(String managerPhoneNum) {
        this.managerPhoneNum = managerPhoneNum;
    }

    public String getOutLPosition() {
        return outLPosition;
    }

    public void setOutLPosition(String outLPosition) {
        this.outLPosition = outLPosition;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<PostilsBean> getPostils() {
        return postils;
    }

    public void setPostils(List<PostilsBean> postils) {
        this.postils = postils;
    }


}
