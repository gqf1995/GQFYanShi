package com.gqfyanshi.entity.event;

/**
 * Created by 郭青枫 on 2018/4/3 0003.
 */

public class StartPageEventBus {

    private String msg;

    public StartPageEventBus(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

}
