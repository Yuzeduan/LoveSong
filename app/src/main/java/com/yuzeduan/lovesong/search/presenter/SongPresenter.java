package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.SearchSongList;
import com.yuzeduan.lovesong.search.model.SongModel;

import java.lang.ref.WeakReference;
import java.util.List;


public class SongPresenter extends BasePresenter<MVPContract.ISongView> implements MVPContract.ImpPresenter{
    private UIHandler mUIHandler;
    private SongModel mSongModel;

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
                message.obj = list;
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
            reference.get().getView().showSongView((List<SearchSongList>)msg.obj);
        }
    }
}
