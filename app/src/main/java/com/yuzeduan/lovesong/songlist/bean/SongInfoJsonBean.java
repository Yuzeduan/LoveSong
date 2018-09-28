package com.yuzeduan.lovesong.songlist.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author: Allen
 * date: On 2018/9/27
 */
@Entity
public class SongInfoJsonBean {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "mSongId")
    private String mSongId;
    @Property(nameInDb = "mJsonData")
    private String mJsonData;
    @Generated(hash = 1192830029)
    public SongInfoJsonBean(Long id, String mSongId, String mJsonData) {
        this.id = id;
        this.mSongId = mSongId;
        this.mJsonData = mJsonData;
    }
    @Generated(hash = 94787508)
    public SongInfoJsonBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMSongId() {
        return this.mSongId;
    }
    public void setMSongId(String mSongId) {
        this.mSongId = mSongId;
    }
    public String getMJsonData() {
        return this.mJsonData;
    }
    public void setMJsonData(String mJsonData) {
        this.mJsonData = mJsonData;
    }

}
