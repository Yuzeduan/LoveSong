package com.yuzeduan.lovesong.songlist.presenter;

import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
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
                message.obj = list;
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
            reference.get().getView().showAlbumList((List<AlbumInfo>) msg.obj);
        }
    }
}
