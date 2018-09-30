package com.yuzeduan.lovesong.search.event;

import com.yuzeduan.lovesong.music.bean.Song;

/**
 * author: Allen
 * date: On 2018/9/29
 */
public class SongEvent {
    private Song mSong;
    private int mFlag;

    public SongEvent(Song mSong, int mFlag) {
        this.mSong = mSong;
        this.mFlag = mFlag;
    }

    public Song getmSong() {
        return mSong;
    }

    public int getmFlag() {
        return mFlag;
    }
}
