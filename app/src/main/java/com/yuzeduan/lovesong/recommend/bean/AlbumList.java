package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

public class AlbumList {
    @SerializedName("album_id")
    private String mAlbumId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("pic_radio")
    private String mPicPath;

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

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmPicPath() {
        return mPicPath;
    }

    public void setmPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }
}
