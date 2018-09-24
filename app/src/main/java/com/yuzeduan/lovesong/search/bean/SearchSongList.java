package com.yuzeduan.lovesong.search.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 搜索结果中的歌曲实体类
 */
public class SearchSongList {
    @SerializedName("album_title")
    private String mAlbumTitle;
    @SerializedName("lrclink")
    private String mLrclink;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("song_id")
    private String mSongId;

    public String getmAlbumTitle() {
        return mAlbumTitle;
    }

    public void setmAlbumTitle(String mAlbumTitle) {
        this.mAlbumTitle = mAlbumTitle;
    }

    public String getmLrclink() {
        return mLrclink;
    }

    public void setmLrclink(String mLrclink) {
        this.mLrclink = mLrclink;
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

    public String getmSongId() {
        return mSongId;
    }

    public void setmSongId(String mSongId) {
        this.mSongId = mSongId;
    }
}
