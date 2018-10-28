package com.gqfyanshi.entity.bean;

/**
 * Created by 郭青枫 on 2018/10/28.
 */

public class ApprovalBean extends DocumentBean {
    /**
     * sendeeId : 1,2,3,4,5,6,10
     * sponsor : 主办单位
     * draftDoc : 拟稿
     * cTime : 2018-10-26 14:42:22
     * priority : 紧急情况
     * userName : 人大办
     * userId : 10
     * verifySend : 核发
     * opinion : 拟办意见
     * security : 密级
     * counterSign : 会签
     * verifyDoc : 核稿
     * scope : 发送范围
     * uTime : 2018-10-26 14:50:21
     * id : 1
     */

    private String sendeeId;
    private String sponsor;
    private String draftDoc;
    private String cTime;
    private String priority;
    private String userName;
    private int userId;
    private String verifySend;
    private String opinion;
    private String security;
    private String counterSign;
    private String verifyDoc;
    private String scope;
    private String uTime;

    public String getSendeeId() {
        return sendeeId;
    }

    public void setSendeeId(String sendeeId) {
        this.sendeeId = sendeeId;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getDraftDoc() {
        return draftDoc;
    }

    public void setDraftDoc(String draftDoc) {
        this.draftDoc = draftDoc;
    }

    public String getCTime() {
        return cTime;
    }

    public void setCTime(String cTime) {
        this.cTime = cTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getVerifySend() {
        return verifySend;
    }

    public void setVerifySend(String verifySend) {
        this.verifySend = verifySend;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getCounterSign() {
        return counterSign;
    }

    public void setCounterSign(String counterSign) {
        this.counterSign = counterSign;
    }

    public String getVerifyDoc() {
        return verifyDoc;
    }

    public void setVerifyDoc(String verifyDoc) {
        this.verifyDoc = verifyDoc;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUTime() {
        return uTime;
    }

    public void setUTime(String uTime) {
        this.uTime = uTime;
    }

}
