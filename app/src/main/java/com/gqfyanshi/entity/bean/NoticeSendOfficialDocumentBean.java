package com.gqfyanshi.entity.bean;

/**
 * Created by 郭青枫 on 2018/9/22.
 */

public class NoticeSendOfficialDocumentBean {
    /**
     * summary : null
     * receipt_time : 2017-08-30 18:12:33
     * createtime : 2017-08-30
     * file_address : /upload/20170830181233.pdf
     * readed_record : 1
     * remark : 党群口文件
     * model_id : 5
     * title : 关于全市关键岗位以案促改工作检查情况的通报
     * type : 02
     * user_id : 74
     * name : 偃纪通〔2017〕25号
     * id : 167
     * status : 00
     */

    private String summary;
    private String receipt_time;
    private String createtime;
    private String file_address;
    private int readed_record;
    private String remark;
    private int model_id;
    private String title;
    private String type;
    private int user_id;
    private String name;
    private int id;
    private String status;
    /**
     * repceipt : 0
     * departId : 145
     * depart : 文书科
     */

    private int repceipt;
    private int departId;
    private String depart;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReceipt_time() {
        return receipt_time;
    }

    public void setReceipt_time(String receipt_time) {
        this.receipt_time = receipt_time;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFile_address() {
        return file_address;
    }

    public void setFile_address(String file_address) {
        this.file_address = file_address;
    }

    public int getReaded_record() {
        return readed_record;
    }

    public void setReaded_record(int readed_record) {
        this.readed_record = readed_record;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getModel_id() {
        return model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRepceipt() {
        return repceipt;
    }

    public void setRepceipt(int repceipt) {
        this.repceipt = repceipt;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }
}
