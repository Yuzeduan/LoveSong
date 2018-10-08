package com.yuzeduan.lovesong.music.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.music.MVPContract;
import com.yuzeduan.lovesong.music.bean.LrcEntity;
import com.yuzeduan.lovesong.music.model.LrcModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * author: Allen
 * date: On 2018/10/8
 */
public class LrcPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter{
    private LrcModel mLocModel;
    private LrcHandler mHandler;

    public LrcPresenter() {
        mLocModel = new LrcModel();
        mHandler = new LrcHandler(this);
    }

    @Override
    public void getData(String address) {
        mLocModel.getLrcData(address, new LrcModel.LrcListener() {
            @Override
            public void onLrcDataFinish(List<LrcEntity> list) {
                Message message = Message.obtain();
                message.obj = list;
                mHandler.sendMessage(message);
            }
        });
    }

    private static class LrcHandler extends Handler {
        private WeakReference<LrcPresenter> reference;

        LrcHandler(LrcPresenter presenter) {
            reference = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
           reference.get().getView().showLrc((List<LrcEntity>)msg.obj);
        }
    }
}
