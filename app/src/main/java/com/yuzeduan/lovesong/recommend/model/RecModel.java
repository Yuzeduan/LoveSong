package com.yuzeduan.lovesong.recommend.model;

import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseUtil;

import java.util.List;

import api.MusicApi;

public class RecModel {
    private RecListener mListener;

    public RecModel(RecListener mListener) {
        this.mListener = mListener;
    }

    public void getBannerData(){
        String address = MusicApi.focusPic(0);
        HttpUtil.okHttpAsync(address, new HttpUtil.HttpCallback() {
            @Override
            public void onFinish(String str) {
                List<FocusPic> list = ParseUtil.parseFocusPic(str);
                mListener.onBannerDataFinish(list);
            }
        });
    }

    public interface RecListener {
        void onBannerDataFinish(List<FocusPic> list);
    }
}
