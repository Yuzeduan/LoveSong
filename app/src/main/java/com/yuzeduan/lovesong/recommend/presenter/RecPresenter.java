package com.yuzeduan.lovesong.recommend.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.recommend.MVPContract;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.model.RecModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter {
    private static final int GET_BANNER_DATA = 1;
    private static final int GET_HOTSONGLIST_DATA = 2;

    private RecModel mRecModel;
    private UIHandler mUIHandler;

    public RecPresenter() {
        RecModel.RecListener listener = new RecModel.RecListener() {

            @Override
            public void onBannerDataFinish(List<FocusPic> list) {
                Message message = Message.obtain();
                message.what = GET_BANNER_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onHotSongListDataFinish(List<HotSongList> list) {
                Message message = Message.obtain();
                message.what = GET_HOTSONGLIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }
        };
        mRecModel = new RecModel(listener);
        mUIHandler = new UIHandler(this);
    }

    @Override
    public void getBannerData() {
        mRecModel.getBannerData();
    }

    @Override
    public void getHotSongListData() {
        mRecModel.getHotSongListData();
    }

    private static class UIHandler extends Handler {
        private WeakReference<RecPresenter> reference;

        UIHandler(RecPresenter recPresenter) {
            reference = new WeakReference<>(recPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            RecPresenter presenter = reference.get();
            switch (msg.what){
                case GET_BANNER_DATA:
                    presenter.getView().showBanner((List<FocusPic>)msg.obj);
                    break;
                case GET_HOTSONGLIST_DATA:
                    presenter.getView().showHotSongList((List<HotSongList>)msg.obj);
                    break;
            }
        }
    }
}
