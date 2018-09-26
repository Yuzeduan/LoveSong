package com.yuzeduan.lovesong.songlist.bean;

import com.google.gson.annotations.SerializedName;

/**
 * author: Allen
 * date: On 2018/9/25
 */
public class SongInfo {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("song_id")
    private String mSongId;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("album_title")
    private String mAlbumTitle;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
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

    public String getmAlbumTitle() {
        return mAlbumTitle;
    }

    public void setmAlbumTitle(String mAlbumTitle) {
        this.mAlbumTitle = mAlbumTitle;
    }
}
