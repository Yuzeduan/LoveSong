package com.yuzeduan.lovesong.music.bean;

import android.support.annotation.NonNull;

/**
 * 每行歌词的基类
 * author: Allen
 * date: On 2018/10/5
 */
public class LrcEntity implements Comparable<LrcEntity>{
    private String mTime;
    private long mTimeLong;
    private String mText;


    @Override
    public int compareTo(@NonNull LrcEntity lrcEntity) {
        return (int)(mTimeLong - lrcEntity.getmTimeLong());
        // 按照时间的升序进行排序
    }

    public String getmTime() {
        return mTime;
    }


    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public long getmTimeLong() {
        return mTimeLong;
    }

    public void setmTimeLong(long mTimeLong) {
        this.mTimeLong = mTimeLong;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}
