package com.yuzeduan.lovesong.music.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.yuzeduan.lovesong.aidl.IMusicManager;

import java.io.IOException;

public class MusicService extends Service {

    private MediaPlayer mPlayer;
    private Binder mBinder = new IMusicManager.Stub() {
        @Override
        public void onStart() throws RemoteException {
            try {
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                }
            } catch (Exception e) {
                Log.e("PlayerManagerService", Log.getStackTraceString(e));
            }
        }

        @Override
        public void onPause() throws RemoteException {
            try {
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
            } catch (IllegalStateException e) {
                Log.e("MusicService", Log.getStackTraceString(e));
            }
        }

        @Override
        public void setMusic(String songAddress) throws RemoteException {
            try {
                mPlayer.release();
                mPlayer = new MediaPlayer();
                mPlayer.setDataSource(songAddress);
                mPlayer.prepare();
            } catch (IOException e) {
                Log.e("MusicService", Log.getStackTraceString(e));
            }
        }

        @Override
        public void setCurrDuration(long songCurrTime) throws RemoteException {
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mPlayer != null) {
            mPlayer.release();
        }
        return super.onUnbind(intent);
    }
}
