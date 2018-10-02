package com.yuzeduan.lovesong.music.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Allen
 * date: On 2018/9/28
 */
@Entity
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    @Property(nameInDb = "mSongId")
    private String mSongId;
    @Property(nameInDb = "mSongAddress")
    private String mSongAddress;
    @Property(nameInDb = "mSongName")
    private String mSongName;
    @Property(nameInDb = "mArtist")
    private String mArtist;
    @Property(nameInDb = "mHugePicPath")
    private String mHugePicPath;
    @Property(nameInDb = "mSmallPicPath")
    private String mSmallPicPath;
    @Property(nameInDb = "mLrcLink")
    private String mLrcLink;
    @Property(nameInDb = "isLocal")
    private boolean isLocal;


    @Generated(hash = 1997881037)
    public Song(String mSongId, String mSongAddress, String mSongName,
            String mArtist, String mHugePicPath, String mSmallPicPath,
            String mLrcLink, boolean isLocal) {
        this.mSongId = mSongId;
        this.mSongAddress = mSongAddress;
        this.mSongName = mSongName;
        this.mArtist = mArtist;
        this.mHugePicPath = mHugePicPath;
        this.mSmallPicPath = mSmallPicPath;
        this.mLrcLink = mLrcLink;
        this.isLocal = isLocal;
    }

    @Generated(hash = 87031450)
    public Song() {
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

    public String getMSongId() {
        return this.mSongId;
    }

    public void setMSongId(String mSongId) {
        this.mSongId = mSongId;
    }

    public String getMSongAddress() {
        return this.mSongAddress;
    }

    public void setMSongAddress(String mSongAddress) {
        this.mSongAddress = mSongAddress;
    }

    public String getMSongName() {
        return this.mSongName;
    }

    public void setMSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getMArtist() {
        return this.mArtist;
    }

    public void setMArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public String getMHugePicPath() {
        return this.mHugePicPath;
    }

    public void setMHugePicPath(String mHugePicPath) {
        this.mHugePicPath = mHugePicPath;
    }

    public String getMSmallPicPath() {
        return this.mSmallPicPath;
    }

    public void setMSmallPicPath(String mSmallPicPath) {
        this.mSmallPicPath = mSmallPicPath;
    }

    public String getMLrcLink() {
        return this.mLrcLink;
    }

    public void setMLrcLink(String mLrcLink) {
        this.mLrcLink = mLrcLink;
    }

    public boolean getIsLocal() {
        return this.isLocal;
    }

    public void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }
    
}
