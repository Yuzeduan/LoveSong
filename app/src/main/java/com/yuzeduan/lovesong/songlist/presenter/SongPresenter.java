package com.yuzeduan.lovesong.songlist.presenter;

import android.media.MediaExtractor;
import android.os.Handler;
import android.os.Message;
import android.print.PrinterId;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.songlist.MVPContract;
import com.yuzeduan.lovesong.songlist.bean.SongInfo;
import com.yuzeduan.lovesong.songlist.model.SongModel;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * author: Allen
 * date: On 2018/9/25
 */
public class SongPresenter extends BasePresenter<MVPContract.ISongView> implements MVPContract.IPresenter{
    private static final int GET_LIST_DATA = 0;
    private static final int GET_SELECT_SONG_DAAT = 1;
    private UIHandler mUIHandler;
    private SongModel mSongModel;

    public SongPresenter() {
        mUIHandler = new UIHandler(this);
        mSongModel = new SongModel();
    }

    @Override
    public void getData(String code) {
        mSongModel.getSongData(code, new SongModel.SongListener() {
            @Override
            public void onSongDataFinish(List<SongInfo> list) {
                Message message = Message.obtain();
                message.what = GET_LIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onSelectSongDataFinish(Song song) {
            }
        });
    }

    @Override
    public void getSelectSongData(String songId, final int flag) {
        mSongModel.getSelectSongData(songId, new SongModel.SongListener() {
            @Override
            public void onSongDataFinish(List<SongInfo> list) {
            }

            @Override
            public void onSelectSongDataFinish(Song song) {
                Message message = Message.obtain();
                message.what = GET_SELECT_SONG_DAAT;
                message.arg1 = flag;
                message.obj = song;
                mUIHandler.sendMessage(message);
            }
        });
    }

    private static class UIHandler extends Handler {
        private WeakReference<SongPresenter> reference;

        UIHandler(SongPresenter presenter) {
            this.reference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_LIST_DATA:
                    reference.get().getView().showSongList((List<SongInfo>)msg.obj);
                    break;
                case GET_SELECT_SONG_DAAT:
                    reference.get().getView().setSongToBottom((Song)msg.obj, msg.arg1);
                    break;
            }
        }
    }
}
