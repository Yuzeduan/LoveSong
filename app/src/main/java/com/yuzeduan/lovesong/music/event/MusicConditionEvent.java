package com.yuzeduan.lovesong.music.event;

import com.yuzeduan.lovesong.music.bean.Song;

import java.io.Serializable;
import java.util.List;

/**
 * 用于EventBus传递底部音乐播放栏的状态的事件类
 * author: Allen
 * date: On 2018/9/28
 */
public class MusicConditionEvent implements Serializable{
    private List<Song> mList;
    private int mPosition;
    private int mPlayMode;
    private boolean isPlay;

    public MusicConditionEvent(List<Song> mList, int mPosition, int mPlayMode, boolean isPlay) {
        this.mList = mList;
        this.mPosition = mPosition;
        this.mPlayMode = mPlayMode;
        this.isPlay = isPlay;
    }

    public List<Song> getmList() {
        return mList;
    }

    public void setmList(List<Song> mList) {
        this.mList = mList;
    }

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public int getmPlayMode() {
        return mPlayMode;
    }

    public void setmPlayMode(int mPlayMode) {
        this.mPlayMode = mPlayMode;
    }
}
