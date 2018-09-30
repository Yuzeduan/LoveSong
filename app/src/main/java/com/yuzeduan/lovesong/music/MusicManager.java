package com.yuzeduan.lovesong.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.yuzeduan.lovesong.aidl.IMusicManager;
import com.yuzeduan.lovesong.music.service.MusicService;
import com.yuzeduan.lovesong.util.LoveSongApplication;

/**
 * 管理后台播放服务
 * author: Allen
 * date: On 2018/9/29
 */
public class MusicManager {
    private IMusicManager mPlayManager;
    private Context mContext;
    private static MusicManager mMusicManger;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mPlayManager = IMusicManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mPlayManager = null;
        }
    };

    private MusicManager(){
        mContext = LoveSongApplication.getContext();
        Intent intent = new Intent(mContext, MusicService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public static MusicManager getInstance(){
        if(mMusicManger == null){
            synchronized (MusicManager.class){
                if(mMusicManger == null){
                    mMusicManger = new MusicManager();
                }
            }
        }
        return mMusicManger;
    }

    /**
     * 解绑服务
     */
    public void unBindService(){
        mContext.unbindService(mConnection);
    }

    /**
     * 播放音乐
     */
    public void onPlayMusic(){
        try {
            mPlayManager.onStart();
        } catch (RemoteException e) {
            Log.e("MusicManager", Log.getStackTraceString(e));
        }
    }

    /**
     * 暂停音乐
     */
    public void onPauseMusic(){
        try {
            mPlayManager.onPause();
        } catch (RemoteException e) {
            Log.e("MusicManager", Log.getStackTraceString(e));
        }
    }

    /**
     * 添加歌曲
     */
    public void setSong(String address){
        try {
            mPlayManager.setMusic(address);
        } catch (RemoteException e) {
            Log.e("MusicManager", Log.getStackTraceString(e));
        }
    }

    /**
     * 设置跳转到歌曲的某个时间点
     * @param currDuration 跳转到的时间点
     */
    public void setCurrDuration(long currDuration){
        try {
            mPlayManager.setCurrDuration(currDuration);
        } catch (RemoteException e) {
            Log.e("MusicManager", Log.getStackTraceString(e));
        }
    }
}
