package com.yuzeduan.lovesong.search.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 搜索结果中的歌手实体类
 */
public class SearchArtistList {
    @SerializedName("song_num")
    private String mSongNum;
    @SerializedName("avatar_middle")
    private String mPicPath;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("artist_id")
    private String mArtistId;

    public String getmSongNum() {
        return mSongNum;
    }

    public void setmSongNum(String mSongNum) {
        this.mSongNum = mSongNum;
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

    public String getmArtistId() {
        return mArtistId;
    }

    public void setmArtistId(String mArtistId) {
        this.mArtistId = mArtistId;
    }
}
