package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

public class Song {
    @SerializedName("lrclink")
    private String mLrcLink;
    @SerializedName("song_id")
    private String mSongId;
    @SerializedName("author")
    private String mAuthor;

    public String getmLrcLink() {
        return mLrcLink;
    }

    public void setmLrcLink(String mLrcLink) {
        this.mLrcLink = mLrcLink;
    }

    public String getmSongId() {
        return mSongId;
    }

    public void setmSongId(String mSongId) {
        this.mSongId = mSongId;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
