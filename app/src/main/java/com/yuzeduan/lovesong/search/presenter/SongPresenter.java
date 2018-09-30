package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.SearchSongList;
import com.yuzeduan.lovesong.search.model.SongModel;

import java.lang.ref.WeakReference;
import java.util.List;


public class SongPresenter extends BasePresenter<MVPContract.ISongView> implements MVPContract.ISongPresenter{
    private UIHandler mUIHandler;
    private SongModel mSongModel;
    private static final int GET_SONGLIST_DATA = 0;
    private static final int GET_SELECT_SONG = 1;

    public SongPresenter() {
        mUIHandler = new UIHandler(this);
        mSongModel = new SongModel();
    }

    @Override
    public void getData(String str, int pageNo) {
        mSongModel.getSongData(str, pageNo, new SongModel.SongListener() {
            @Override
            public void onSongDataFinish(List<SearchSongList> list) {
                Message message = Message.obtain();
                message.what = GET_SONGLIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }
            @Override
            public void onSelectSongDataFinish(Song song) {
            }
        });
    }

    @Override
    public void getSong(String songId, final int flag) {
        mSongModel.getSelectedSongData(songId, new SongModel.SongListener() {
            @Override
            public void onSongDataFinish(List<SearchSongList> list) {
            }

            @Override
            public void onSelectSongDataFinish(Song song) {
                Message message = Message.obtain();
                message.what = GET_SELECT_SONG;
                message.obj = song;
                message.arg1 = flag;
                mUIHandler.sendMessage(message);
            }
        });
    }

    private static class UIHandler extends Handler {
        private WeakReference<SongPresenter> reference;

        UIHandler(SongPresenter songPresenter) {
            reference = new WeakReference<>(songPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_SONGLIST_DATA:
                    reference.get().getView().showSongView((List<SearchSongList>)msg.obj);
                    break;
                case GET_SELECT_SONG:
                    reference.get().getView().sendSelectSong((Song)msg.obj, msg.arg1);
                    break;
            }
        }
    }
}
