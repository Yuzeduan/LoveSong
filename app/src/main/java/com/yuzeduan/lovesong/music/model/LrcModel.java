package com.yuzeduan.lovesong.music.model;

import android.support.annotation.NonNull;

import com.yuzeduan.lovesong.music.bean.LrcEntity;
import com.yuzeduan.lovesong.music.util.ParseLrc;
import com.yuzeduan.lovesong.util.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 *获取Lrc歌词并解析
 * author: Allen
 * date: On 2018/10/8
 */
public class LrcModel {

    public void getLrcData(String address, final LrcListener listener){
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String lrc = response.body().string();
                List<LrcEntity> list = ParseLrc.parseLrc(lrc);
                listener.onLrcDataFinish(list);
            }
        });
    }

    public interface LrcListener{
        void onLrcDataFinish(List<LrcEntity> list);
    }
}
