package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.model.AlbumModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class AlbumPresenter extends BasePresenter<MVPContract.IAlbumView> implements MVPContract.ImpPresenter{
    private UIHandler mUIHandler;
    private AlbumModel mAlbumModel;

    public AlbumPresenter() {
        mUIHandler = new UIHandler(this);
        mAlbumModel = new AlbumModel();
    }

    @Override
    public void getData(String str, int pageNo) {
        mAlbumModel.getAlbumData(str, pageNo, new AlbumModel.AlbumListener() {
            @Override
            public void onAlbumDataFinish(List<SearchAlbumList> list) {
                Message message = Message.obtain();
                message.obj = list;
                mUIHandler.sendMessage(message);
            }
        });
    }

    private static class UIHandler extends Handler{
        private WeakReference<AlbumPresenter> reference;

        UIHandler(AlbumPresenter presenter) {
            reference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            reference.get().getView().showAlbumView((List<SearchAlbumList>)msg.obj);
        }
    }
}
