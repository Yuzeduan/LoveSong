package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

public class RadioList {
    @SerializedName("channelid")
    private String mChannelId;
    @SerializedName("itemid")
    private String mItemId;
    @SerializedName("album_id")
    private String mAlbumId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("pic")
    private String mPicPath;

    public String getmChannelId() {
        return mChannelId;
    }

    public void setmChannelId(String mChannelId) {
        this.mChannelId = mChannelId;
    }

    public String getmItemId() {
        return mItemId;
    }

    public void setmItemId(String mItemId) {
        this.mItemId = mItemId;
    }

    public String getmAlbumId() {
        return mAlbumId;
    }

    public void setmAlbumId(String mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPicPath() {
        return mPicPath;
    }

    public void setmPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }
}
