package com.yuzeduan.lovesong.songlist.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
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
                message.obj = list;
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
            reference.get().getView().showSongList((List<SongInfo>)msg.obj);
        }
    }
}
