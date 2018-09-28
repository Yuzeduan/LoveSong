package com.yuzeduan.lovesong.songlist.bean;


import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AlbumInfo {
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("author")
    @Property(nameInDb = "mAuthor")
    private String mAuthor;
    @SerializedName("lrclink")
    @Property(nameInDb = "mLrclink")
    private String mLrclink;
    @SerializedName("song_id")
    @Property(nameInDb = "mSongId")
    private String mSongId;
    @SerializedName("title")
    @Property(nameInDb = "mTitle")
    private String mTitle;
    @SerializedName("album_id")
    @Property(nameInDb = "mAlbumId")
    private String mAlbumId;
    @Generated(hash = 663531865)
    public AlbumInfo(Long id, String mAuthor, String mLrclink, String mSongId,
            String mTitle, String mAlbumId) {
        this.id = id;
        this.mAuthor = mAuthor;
        this.mLrclink = mLrclink;
        this.mSongId = mSongId;
        this.mTitle = mTitle;
        this.mAlbumId = mAlbumId;
    }
    @Generated(hash = 1561175486)
    public AlbumInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMAuthor() {
        return this.mAuthor;
    }
    public void setMAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
    public String getMLrclink() {
        return this.mLrclink;
    }
    public void setMLrclink(String mLrclink) {
        this.mLrclink = mLrclink;
    }
    public String getMSongId() {
        return this.mSongId;
    }
    public void setMSongId(String mSongId) {
        this.mSongId = mSongId;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMAlbumId() {
        return this.mAlbumId;
    }
    public void setMAlbumId(String mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

}
