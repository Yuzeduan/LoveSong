package com.yuzeduan.lovesong.recommend.bean;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HotSongList {
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("listid")
    @Property(nameInDb = "mListId")
    private String mListId;
    @SerializedName("pic")
    @Property(nameInDb = "mPicPath")
    private String mPicPath;
    @SerializedName("title")
    @Property(nameInDb = "mTitle")
    private String mTitle;
    @SerializedName("tag")
    @Property(nameInDb = "mTag")
    private String mTag;
    @Generated(hash = 2063492625)
    public HotSongList(Long id, String mListId, String mPicPath, String mTitle,
            String mTag) {
        this.id = id;
        this.mListId = mListId;
        this.mPicPath = mPicPath;
        this.mTitle = mTitle;
        this.mTag = mTag;
    }
    @Generated(hash = 1737696673)
    public HotSongList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMListId() {
        return this.mListId;
    }
    public void setMListId(String mListId) {
        this.mListId = mListId;
    }
    public String getMPicPath() {
        return this.mPicPath;
    }
    public void setMPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMTag() {
        return this.mTag;
    }
    public void setMTag(String mTag) {
        this.mTag = mTag;
    }

}
