package com.yuzeduan.lovesong.music.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用于存储播放列表的播放模式,和播放歌曲的位置
 * author: Allen
 * date: On 2018/10/2
 */
@Entity
public class playCondition {
    @Property(nameInDb = "mPosition")
    private int mPosition;
    @Property(nameInDb = "mPlayMode")
    private int mPlayMode;
    @Generated(hash = 1102545415)
    public playCondition(int mPosition, int mPlayMode) {
        this.mPosition = mPosition;
        this.mPlayMode = mPlayMode;
    }
    @Generated(hash = 908850842)
    public playCondition() {
    }
    public int getMPosition() {
        return this.mPosition;
    }
    public void setMPosition(int mPosition) {
        this.mPosition = mPosition;
    }
    public int getMPlayMode() {
        return this.mPlayMode;
    }
    public void setMPlayMode(int mPlayMode) {
        this.mPlayMode = mPlayMode;
    }
}
