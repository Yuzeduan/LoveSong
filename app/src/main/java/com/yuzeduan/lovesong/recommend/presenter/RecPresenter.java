package com.yuzeduan.lovesong.recommend.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.recommend.MVPContract;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.bean.RadioList;
import com.yuzeduan.lovesong.recommend.model.RecModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter {
    private static final int GET_BANNER_DATA = 1;
    private static final int GET_HOTSONGLIST_DATA = 2;
    private static final int GET_ALBUMLIST_DATA = 3;
    private static final int GET_RADIOLIST_DATA = 4;
    private static final int GET_REFRESH_BANNER_DATA = 5;
    private static final int GET_REFRESH_HOTSONGLIST_DATA = 6;
    private static final int GET_REFRESH_ALBUMLIST_DATA = 7;
    private static final int GET_REFRESH_RADIOLIST_DATA = 8;
    public static final int REFRESH = 9;

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

            @Override
            public void onAlbumListDataFinish(List<AlbumList> list) {
                Message message = Message.obtain();
                message.what = GET_ALBUMLIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onRadioListDataFinish(List<RadioList> list) {
                Message message = Message.obtain();
                message.what = GET_RADIOLIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onRefreshBannerDataFinish(List<FocusPic> list) {
                Message message = Message.obtain();
                message.what = GET_REFRESH_BANNER_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onRefreshAlbumListDataFinish(List<AlbumList> list) {
                Message message = Message.obtain();
                message.what = GET_REFRESH_ALBUMLIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onRefreshHotSongListData(List<HotSongList> list) {
                Message message = Message.obtain();
                message.what = GET_REFRESH_HOTSONGLIST_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }

            @Override
            public void onRefreshRadioListDataFinish(List<RadioList> list) {
                Message message = Message.obtain();
                message.what = GET_REFRESH_RADIOLIST_DATA;
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

    @Override
    public void getAlbumListData() {
        mRecModel.getAlbumListData();
    }

    @Override
    public void getRadioListData() {
        mRecModel.getRadioListData();
    }

    @Override
    public void getRefreshBannerData() {
        mRecModel.getRefreshBannerData();
    }

    @Override
    public void getRefreshHotSongListData() {
        mRecModel.getRefreshHotSongListData();
    }

    @Override
    public void getRefreshAlbumListData() {
        mRecModel.getRefreshAlbumListData();
    }

    @Override
    public void getRefreshRadioListData() {
        mRecModel.getRefreshRadioListData();
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
                    presenter.getView().showHotSongList((List<HotSongList>)msg.obj, 0);
                    break;
                case GET_ALBUMLIST_DATA:
                    presenter.getView().showAlbumList((List<AlbumList>)msg.obj,0 );
                    break;
                case GET_RADIOLIST_DATA:
                    presenter.getView().showRadioList((List<RadioList>)msg.obj, 0);
                    break;
                case GET_REFRESH_BANNER_DATA:
                    presenter.getView().showRefreshData((List<FocusPic>)msg.obj);
                    break;
                case GET_REFRESH_HOTSONGLIST_DATA:
                    presenter.getView().showHotSongList((List<HotSongList>)msg.obj, REFRESH);
                    break;
                case GET_REFRESH_ALBUMLIST_DATA:
                    presenter.getView().showAlbumList((List<AlbumList>)msg.obj, REFRESH);
                    break;
                case GET_REFRESH_RADIOLIST_DATA:
                    presenter.getView().showRadioList((List<RadioList>)msg.obj, REFRESH);
                    break;
            }
        }
    }
}
