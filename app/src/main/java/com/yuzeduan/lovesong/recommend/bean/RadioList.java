package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RadioList {
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("channelid")
    @Property(nameInDb = "mChannelId")
    private String mChannelId;
    @SerializedName("itemid")
    @Property(nameInDb = "mItemId")
    private String mItemId;
    @SerializedName("album_id")
    @Property(nameInDb = "mAlbumId")
    private String mAlbumId;
    @SerializedName("title")
    @Property(nameInDb = "mTitle")
    private String mTitle;
    @SerializedName("pic")
    @Property(nameInDb = "mPicPath")
    private String mPicPath;
    @Generated(hash = 1905635092)
    public RadioList(Long id, String mChannelId, String mItemId, String mAlbumId,
            String mTitle, String mPicPath) {
        this.id = id;
        this.mChannelId = mChannelId;
        this.mItemId = mItemId;
        this.mAlbumId = mAlbumId;
        this.mTitle = mTitle;
        this.mPicPath = mPicPath;
    }
    @Generated(hash = 1634977497)
    public RadioList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMChannelId() {
        return this.mChannelId;
    }
    public void setMChannelId(String mChannelId) {
        this.mChannelId = mChannelId;
    }
    public String getMItemId() {
        return this.mItemId;
    }
    public void setMItemId(String mItemId) {
        this.mItemId = mItemId;
    }
    public String getMAlbumId() {
        return this.mAlbumId;
    }
    public void setMAlbumId(String mAlbumId) {
        this.mAlbumId = mAlbumId;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMPicPath() {
        return this.mPicPath;
    }
    public void setMPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }

}
