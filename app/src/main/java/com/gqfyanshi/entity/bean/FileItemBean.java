package com.gqfyanshi.entity.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 郭青枫 on 2018/11/9 0009.
 */

public class FileItemBean implements Parcelable {
    /**
     * path : /upload/fileCabinet/newUser1
     * name : newUser1
     * time : 1541728042836
     * type : 01
     */

    private String path;
    private String name;
    private long time;
    private String type;
    private String sendeeId;

    public String getSendeeId() {
        return sendeeId;
    }

    public void setSendeeId(String sendeeId) {
        this.sendeeId = sendeeId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeLong(this.time);
        dest.writeString(this.type);
        dest.writeString(this.sendeeId);
    }

    public FileItemBean() {
    }

    protected FileItemBean(Parcel in) {
        this.path = in.readString();
        this.name = in.readString();
        this.time = in.readLong();
        this.type = in.readString();
        this.sendeeId = in.readString();
    }

    public static final Parcelable.Creator<FileItemBean> CREATOR = new Parcelable.Creator<FileItemBean>() {
        @Override
        public FileItemBean createFromParcel(Parcel source) {
            return new FileItemBean(source);
        }

        @Override
        public FileItemBean[] newArray(int size) {
            return new FileItemBean[size];
        }
    };
}
