package com.gqfyanshi.entity.bean;

import java.util.List;

/**
 * Created by 郭青枫 on 2019/9/16.
 */

public class ReceiveSignDetailBean {

    //      `id` int(20) NOT NULL AUTO_INCREMENT,
    int id;
    //  `file_no` varchar(50)  '文件编号',
    String file_no;
    //            `name` varchar(255)  '文章字号',
    String name;
    //            `title` varchar(255)  '文章标题',
    String title;
    //            `userId` int(20) NULL DEFAULT NULL COMMENT '文件发送人id',
    int userId;
    //            `sendeeId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '接收人id',
    String sendeeId;
    //            `view_userId` varchar(255)  '查看人id',
    String view_userId;
    //            `priview` varchar(255)  '摘要预览',
    String priview;
    //            `priority` varchar(255)  '紧急情况',
    String priority;
    //            `security` varchar(255)  '密级',
    String security;
    //            `scope` varchar(255)  '发送范围',
    String scope;
    //            `sponsor` varchar(255)  '主办单位',
    String sponsor;
    //            `draftDoc` varchar(255)  '拟稿',
    String draftDoc;
    //            `verifyDoc` varchar(255)  '核稿',
    String verifyDoc;
    //            `opinion` varchar(255)  '拟办意见',
    String opinion;
    //            `verifySend` varchar(255)  '核发',
    String verifySend;
    //            `counterSign` varchar(255)  '会签',
    String counterSign;
    //            `copyTo` varchar(255)  '抄送',
    String copyTo;
    //            `num` int(10) NULL DEFAULT NULL COMMENT '份数',
    int num;
    //            `leaderOpinion` varchar(255)  '办公室领导意见/主管领导意见',
    String leaderOpinion;
    //            `audit` varchar(255)  '审核',
    String audit;
    //            `issue` varchar(255)  '签发',
    String issue;
    //            `status` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '00' COMMENT '00:未签批,01:已签批,02:连续签批中',
    String status;
    //            `fileName` varchar(255)  '文件名称',
    String fileName;
    //            `fileAddress` varchar(255)  '文件地址',
    String fileAddress;
    //            `cTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    long cTime;
    //            `uTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    long uTime;
//    department
//    科室（局）
    String department;
//    phone
//            电话
    String phone;
//            releaseTime
//            municipalLeaderOpinion
//    市委领导意见
    String municipalLeaderOpinion;
    //备注
    String remark;
    //签批图片列表
    private List<PostilsBean> postils;
    String fromDepartment;//来问单位
    String releaseTime;//  下发时间
    String issuedTime;// 印发时间

    public String getFromDepartment() {
        return fromDepartment;
    }

    public void setFromDepartment(String fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    public void setIssuedTime(String issuedTime) {
        this.issuedTime = issuedTime;
    }

    public List<PostilsBean> getPostils() {
        return postils;
    }

    public void setPostils(List<PostilsBean> postils) {
        this.postils = postils;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getMunicipalLeaderOpinion() {
        return municipalLeaderOpinion;
    }

    public void setMunicipalLeaderOpinion(String municipalLeaderOpinion) {
        this.municipalLeaderOpinion = municipalLeaderOpinion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String file_no) {
        this.file_no = file_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSendeeId() {
        return sendeeId;
    }

    public void setSendeeId(String sendeeId) {
        this.sendeeId = sendeeId;
    }

    public String getView_userId() {
        return view_userId;
    }

    public void setView_userId(String view_userId) {
        this.view_userId = view_userId;
    }

    public String getPriview() {
        return priview;
    }

    public void setPriview(String priview) {
        this.priview = priview;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getVerifyDoc() {
        return verifyDoc;
    }

    public void setVerifyDoc(String verifyDoc) {
        this.verifyDoc = verifyDoc;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getVerifySend() {
        return verifySend;
    }

    public void setVerifySend(String verifySend) {
        this.verifySend = verifySend;
    }

    public String getCounterSign() {
        return counterSign;
    }

    public void setCounterSign(String counterSign) {
        this.counterSign = counterSign;
    }

    public String getCopyTo() {
        return copyTo;
    }

    public void setCopyTo(String copyTo) {
        this.copyTo = copyTo;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLeaderOpinion() {
        return leaderOpinion;
    }

    public void setLeaderOpinion(String leaderOpinion) {
        this.leaderOpinion = leaderOpinion;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getIssuedTime() {
        return issuedTime;
    }

    public long getcTime() {
        return cTime;
    }

    public void setcTime(long cTime) {
        this.cTime = cTime;
    }

    public long getuTime() {
        return uTime;
    }

    public void setuTime(long uTime) {
        this.uTime = uTime;
    }

}
