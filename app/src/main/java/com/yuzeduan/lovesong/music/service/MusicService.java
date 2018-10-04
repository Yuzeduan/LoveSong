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


public class MusicService extends Service implements MediaPlayer.OnCompletionListener{

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
                mPlayer.reset();
                mPlayer.setDataSource(songAddress);
                mPlayer.prepare();
            } catch (IOException e) {
                Log.e("MusicService", Log.getStackTraceString(e));
            }
        }

        @Override
        public void setCurrDuration(int songCurrTime) throws RemoteException {
            if (songCurrTime < 0 || songCurrTime > mPlayer.getDuration() || !mPlayer.isPlaying()) {
                return;
            }
            mPlayer.seekTo(songCurrTime);
        }

        @Override
        public int getCurrentPosition() throws RemoteException {
            return mPlayer.getCurrentPosition();
        }

        @Override
        public int getDuration() throws RemoteException {
            return mPlayer.getDuration();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        mPlayer.setOnCompletionListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Intent intent = new Intent("com.yuzeduan.lovesong.nextmusic");
        sendBroadcast(intent);
    }
}
