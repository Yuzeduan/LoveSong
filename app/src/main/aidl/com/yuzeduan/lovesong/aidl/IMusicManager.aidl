// IMusicManager.aidl
package com.yuzeduan.lovesong.aidl;

// Declare any non-default types here with import statements

interface IMusicManager {
    void onStart();
    void onPause();
    void setMusic(String address);
    void setCurrDuration(int songCurrTime);
    int getDuration();
    int getCurrentPosition();
    boolean isPlaying();
}
