package com.gqfyanshi.entity.bean;

/**
 * Created by 郭青枫 on 2018/10/20.
 */

public class LeaveTypeBean {
    /**
     * dict_id : 33
     * create_time : 2018-10-16 15:21:10
     * modify_time : 2018-10-16 15:21:10
     * dict_value : 01
     * remark : 请假
     * dict_code : leave_type
     */

    private int dict_id;
    private String create_time;
    private String modify_time;
    private String dict_value;
    private String remark;
    private String dict_code;

    public int getDict_id() {
        return dict_id;
    }

    public void setDict_id(int dict_id) {
        this.dict_id = dict_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getDict_value() {
        return dict_value;
    }

    public void setDict_value(String dict_value) {
        this.dict_value = dict_value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDict_code() {
        return dict_code;
    }

    public void setDict_code(String dict_code) {
        this.dict_code = dict_code;
    }
}
