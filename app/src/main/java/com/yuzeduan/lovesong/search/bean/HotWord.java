package com.yuzeduan.lovesong.search.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HotWord {
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("word")
    @Property(nameInDb = "mWord")
    private String mWord;
    @SerializedName("linkurl")
    @Property(nameInDb = "mLinkUri")
    private String mLinkUrl;

    @Generated(hash = 1531581942)
    public HotWord(Long id, String mWord, String mLinkUrl) {
        this.id = id;
        this.mWord = mWord;
        this.mLinkUrl = mLinkUrl;
    }

    @Generated(hash = 1886358916)
    public HotWord() {
    }

    public String getmWord() {
        return mWord;
    }

    public void setmWord(String mWord) {
        this.mWord = mWord;
    }

    public String getmLinkUrl() {
        return mLinkUrl;
    }

    public void setmLinkUrl(String mLinkUrl) {
        this.mLinkUrl = mLinkUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMWord() {
        return this.mWord;
    }

    public void setMWord(String mWord) {
        this.mWord = mWord;
    }

    public String getMLinkUrl() {
        return this.mLinkUrl;
    }

    public void setMLinkUrl(String mLinkUrl) {
        this.mLinkUrl = mLinkUrl;
    }
}
