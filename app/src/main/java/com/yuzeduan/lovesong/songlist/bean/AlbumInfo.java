package com.yuzeduan.lovesong.songlist.bean;

import com.google.gson.annotations.SerializedName;

public class AlbumInfo {
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("lrclink")
    private String mLrclink;
    @SerializedName("song_id")
    private String mSongId;
    @SerializedName("title")
    private String mTitle;

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

    public String getmLrclink() {
        return mLrclink;
    }

    public void setmLrclink(String mLrclink) {
        this.mLrclink = mLrclink;
    }

    public String getmSongId() {
        return mSongId;
    }

    public void setmSongId(String mSongId) {
        this.mSongId = mSongId;
    }
}
