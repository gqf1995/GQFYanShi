package com.gqfyanshi.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 郭青枫 on 2017/11/20.
 */

public class AppVersion implements Parcelable {

    /**
     * content : 更新内容
     * createTime : 1518317396000
     * downloadAddr : fir.im/xx
     * id : 2
     * minVersion : 1.5
     * mustUpdate : true
     * systemVersion : 1.9
     * title : 标题
     * type : 1
     * updateTime : 1518317399000
     */

    private String content;
    private long createTime;
    private String app_url;
    private int id;
    private String minVersion;
    private boolean mustUpdate;
    private boolean isLoadSuccess=false;
    private String app_version;
    private String title;
    private int type;
    private long updateTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    public boolean isMustUpdate() {
        return mustUpdate;
    }

    public void setMustUpdate(boolean mustUpdate) {
        this.mustUpdate = mustUpdate;
    }

    public boolean isLoadSuccess() {
        return isLoadSuccess;
    }

    public void setLoadSuccess(boolean loadSuccess) {
        isLoadSuccess = loadSuccess;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeLong(this.createTime);
        dest.writeString(this.app_url);
        dest.writeInt(this.id);
        dest.writeString(this.minVersion);
        dest.writeByte(this.mustUpdate ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isLoadSuccess ? (byte) 1 : (byte) 0);
        dest.writeString(this.app_version);
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeLong(this.updateTime);
    }

    public AppVersion() {
    }

    protected AppVersion(Parcel in) {
        this.content = in.readString();
        this.createTime = in.readLong();
        this.app_url = in.readString();
        this.id = in.readInt();
        this.minVersion = in.readString();
        this.mustUpdate = in.readByte() != 0;
        this.isLoadSuccess = in.readByte() != 0;
        this.app_version = in.readString();
        this.title = in.readString();
        this.type = in.readInt();
        this.updateTime = in.readLong();
    }

    public static final Parcelable.Creator<AppVersion> CREATOR = new Parcelable.Creator<AppVersion>() {
        @Override
        public AppVersion createFromParcel(Parcel source) {
            return new AppVersion(source);
        }

        @Override
        public AppVersion[] newArray(int size) {
            return new AppVersion[size];
        }
    };
}
