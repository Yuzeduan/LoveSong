package com.yuzeduan.lovesong.recommend.bean;


import com.google.gson.annotations.SerializedName;

public class HotSongList {
    @SerializedName("listid")
    private String mListId;
    @SerializedName("pic")
    private String mPicPath;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("tag")
    private String mTag;

    public String getmListId() {
        return mListId;
    }

    public void setmListId(String mListId) {
        this.mListId = mListId;
    }

    public String getmPicPath() {
        return mPicPath;
    }

    public void setmPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTag() {
        return mTag;
    }

    public void setmTag(String mTag) {
        this.mTag = mTag;
    }

}
