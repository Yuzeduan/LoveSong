package com.yuzeduan.lovesong.music.bean;


import java.io.Serializable;

/**
 * author: Allen
 * date: On 2018/9/28
 */
public class Song implements Serializable {
    private String mSongId;
    private String mSongAddress;
    private String mSongName;
    private String mArtist;
    private String mHugePicPath;
    private String mSmallPicPath;
    private String mLrcLink;
    private boolean isLocal;

    public Song(String mSongId, String mSongAddress, String mSongName, String mAritist, String mHugePicPath, String mSmallPicPath, String mLrcLink, boolean isLocal) {
        this.mSongId = mSongId;
        this.mSongAddress = mSongAddress;
        this.mSongName = mSongName;
        this.mArtist = mAritist;
        this.mHugePicPath = mHugePicPath;
        this.mSmallPicPath = mSmallPicPath;
        this.mLrcLink = mLrcLink;
        this.isLocal = isLocal;
    }

    public String getmSongId() {
        return mSongId;
    }

    public void setmSongId(String mSongId) {
        this.mSongId = mSongId;
    }

    public String getmSongAddress() {
        return mSongAddress;
    }

    public void setmSongAddress(String mSongAddress) {
        this.mSongAddress = mSongAddress;
    }

    public String getmSongName() {
        return mSongName;
    }

    public void setmSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getmHugePicPath() {
        return mHugePicPath;
    }

    public void setmHugePicPath(String mHugePicPath) {
        this.mHugePicPath = mHugePicPath;
    }

    public String getmSmallPicPath() {
        return mSmallPicPath;
    }

    public void setmSmallPicPath(String mSmallPicPath) {
        this.mSmallPicPath = mSmallPicPath;
    }

    public String getmLrcLink() {
        return mLrcLink;
    }

    public void setmLrcLink(String mLrcLink) {
        this.mLrcLink = mLrcLink;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public Song() {
    }
}
