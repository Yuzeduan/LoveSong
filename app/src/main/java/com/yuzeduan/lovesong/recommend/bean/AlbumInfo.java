package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

public class AlbumInfo {
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("publishtime")
    private String mPublishTime;
    @SerializedName("pic_radio")
    private String mPicPath;

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPublishTime() {
        return mPublishTime;
    }

    public void setmPublishTime(String mPublishTime) {
        this.mPublishTime = mPublishTime;
    }

    public String getmPicPath() {
        return mPicPath;
    }

    public void setmPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }
}
