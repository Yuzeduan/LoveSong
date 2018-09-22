package com.yuzeduan.lovesong.local.presenter;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.local.MVPContract;
import com.yuzeduan.lovesong.local.bean.MusicInfo;
import com.yuzeduan.lovesong.local.model.LocModel;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

public class LocPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter{
    private LocModel mLocModel;

    public LocPresenter() {
        mLocModel = new LocModel();
    }

    @Override
    public void getData() {
        List<MusicInfo> list = mLocModel.getMusicList(LoveSongApplication.getContext().getContentResolver());
        getView().showMusicList(list);
    }
}
