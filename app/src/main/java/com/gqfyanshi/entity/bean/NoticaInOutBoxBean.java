package com.gqfyanshi.entity.bean;

/**
 * Created by 郭青枫 on 2018/9/23.
 */

public class NoticaInOutBoxBean {
    /**
     * summary : null
     * receipt_time : 2018-02-26 18:03:00
     * thumbnail : null
     * createtime : 2018-02-26 18:03:00
     * file_name : null
     * file_address : null
     * readed_record : 74,152,44,67,18,154,30,120
     * model_id : 40
     * title : 工作督办函
     * type : null
     * content : <p style="line-height: 16px;"><img src="/municipal/Widget/ueditor/1.4.3/dialogs/attachment/fileTypeImages/icon_pdf.gif"/><a title="工作督办函.pdf" style="color: rgb(0, 102, 204); font-size: 12px;" href="http://1.194.225.66:15968/upload/file/20180226/1519639344832050838.pdf">工作督办函.pdf</a></p><p>请各单位抓紧时间查收。</p>
     * user_id : 74
     * parent_id : null
     * sendee_id : 1,9,121,122,123,124,125,126,127,128,129,130,131,132,133
     * name : null
     * sendee_group_id : null
     * id : 520
     * updatetime : 2018-03-28 10:10:41
     * status : 00
     */

    private String summary;
    private String receipt_time;
    private String thumbnail;
    private String createtime;
    private String file_name;
    private String file_address;
    private String readed_record;
    private int model_id;
    private String title;
    private String type;
    private String content;
    private int user_id;
    private String parent_id;
    private String sendee_id;
    private String name;
    private String sendee_group_id;
    private int id;
    private String updatetime;
    private String status;

    @Override
    public String toString() {
        return "NoticaOutBoxBean{" +
                "summary='" + summary + '\'' +
                ", receipt_time='" + receipt_time + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", createtime='" + createtime + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_address='" + file_address + '\'' +
                ", readed_record='" + readed_record + '\'' +
                ", model_id=" + model_id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", user_id=" + user_id +
                ", parent_id='" + parent_id + '\'' +
                ", sendee_id='" + sendee_id + '\'' +
                ", name='" + name + '\'' +
                ", sendee_group_id='" + sendee_group_id + '\'' +
                ", id=" + id +
                ", updatetime='" + updatetime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_address() {
        return file_address;
    }

    public void setFile_address(String file_address) {
        this.file_address = file_address;
    }

    public String getReaded_record() {
        return readed_record;
    }

    public void setReaded_record(String readed_record) {
        this.readed_record = readed_record;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getSendee_id() {
        return sendee_id;
    }

    public void setSendee_id(String sendee_id) {
        this.sendee_id = sendee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSendee_group_id() {
        return sendee_group_id;
    }

    public void setSendee_group_id(String sendee_group_id) {
        this.sendee_group_id = sendee_group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
