package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.SearchArtistList;
import com.yuzeduan.lovesong.search.model.ArtistModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class ArtistPresenter extends BasePresenter<MVPContract.IArtistView> implements MVPContract.ImpPresenter {
    private UIHandler mUIHandler;
    private ArtistModel mArtistModel;

    public ArtistPresenter() {
        mUIHandler = new UIHandler(this);
        mArtistModel = new ArtistModel();
    }

    @Override
    public void getData(String str, int pageNo) {
        mArtistModel.getArtistData(str, pageNo, new ArtistModel.ArtistListener() {
            @Override
            public void onArtistDataFinish(List<SearchArtistList> list) {
                Message message = Message.obtain();
                message.obj = list;
                mUIHandler.sendMessage(message);
            }
        });
    }

    private static class UIHandler extends Handler {
        private WeakReference<ArtistPresenter> reference;

        UIHandler(ArtistPresenter artistPresenter) {
            reference = new WeakReference<>(artistPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            reference.get().getView().showArtistView((List<SearchArtistList>)msg.obj);
        }
    }
}
