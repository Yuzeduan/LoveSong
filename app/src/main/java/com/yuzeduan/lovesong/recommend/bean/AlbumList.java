package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AlbumList {
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("album_id")
    @Property(nameInDb = "mAlbumId")
    private String mAlbumId;
    @SerializedName("title")
    @Property(nameInDb = "mTitle")
    private String mTitle;
    @SerializedName("author")
    @Property(nameInDb = "mAuthor")
    private String mAuthor;
    @SerializedName("pic_radio")
    @Property(nameInDb = "mPicPath")
    private String mPicPath;
    @SerializedName("publishtime")
    @Property(nameInDb = "mPublishTime")
    private String mPublishTime;
    @Generated(hash = 1980507598)
    public AlbumList(Long id, String mAlbumId, String mTitle, String mAuthor,
            String mPicPath, String mPublishTime) {
        this.id = id;
        this.mAlbumId = mAlbumId;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mPicPath = mPicPath;
        this.mPublishTime = mPublishTime;
    }
    @Generated(hash = 1290875712)
    public AlbumList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getMAuthor() {
        return this.mAuthor;
    }
    public void setMAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
    public String getMPicPath() {
        return this.mPicPath;
    }
    public void setMPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }
    public String getMPublishTime() {
        return this.mPublishTime;
    }
    public void setMPublishTime(String mPublishTime) {
        this.mPublishTime = mPublishTime;
    }
    
}
