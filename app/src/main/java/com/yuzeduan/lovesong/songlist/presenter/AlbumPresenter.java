package com.yuzeduan.lovesong.songlist.presenter;

import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.songlist.MVPContract;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.songlist.model.AlbumModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/25
 */
public class AlbumPresenter extends BasePresenter<MVPContract.IAlbumView> implements MVPContract.IPresenter{
    private UIHandler mUIHandler;
    private AlbumModel mModel;
    private static final int GET_LIST_DATA = 0;
    private static final int GET_SELECT_SONG_DATA = 1;

    public AlbumPresenter() {
        mModel = new AlbumModel();
        mUIHandler = new UIHandler(this);
    }

    @Override
    public void getData(String code) {
        mModel.getAlbumData(code, new AlbumModel.AlbumListener() {
            @Override
            public void onAlbumDataFinish(List<AlbumInfo> list) {
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
        mModel.getSelectSongData(songId, new AlbumModel.AlbumListener() {
            @Override
            public void onAlbumDataFinish(List<AlbumInfo> list) {
            }

            @Override
            public void onSelectSongDataFinish(Song song) {
                Message message = Message.obtain();
                message.what = GET_SELECT_SONG_DATA;
                message.obj = song;
                message.arg1 = flag;
                mUIHandler.sendMessage(message);
            }
        });
    }

    private static class UIHandler extends android.os.Handler{
        private WeakReference<AlbumPresenter> reference;

        UIHandler(AlbumPresenter presenter) {
            this.reference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_LIST_DATA:
                    reference.get().getView().showAlbumList((List<AlbumInfo>) msg.obj);
                    break;
                case GET_SELECT_SONG_DATA:
                    reference.get().getView().setSongToBottom((Song)msg.obj, msg.arg1);
                    break;
            }
        }
    }
}
