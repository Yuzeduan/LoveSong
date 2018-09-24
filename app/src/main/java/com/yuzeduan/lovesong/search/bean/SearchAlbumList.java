package com.yuzeduan.lovesong.search.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 搜索结果中的专辑实体类
 */
public class SearchAlbumList {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("album_id")
    private String mAlbumId;
    @SerializedName("pic_small")
    private String mPicPath;
    @SerializedName("author")
    private String mAuthor;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAlbumId() {
        return mAlbumId;
    }

    public void setmAlbumId(String mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public String getmPicPath() {
        return mPicPath;
    }

    public void setmPicPath(String mPicPath) {
        this.mPicPath = mPicPath;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
