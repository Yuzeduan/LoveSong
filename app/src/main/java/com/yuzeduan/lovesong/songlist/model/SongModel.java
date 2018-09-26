package com.yuzeduan.lovesong.songlist.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.songlist.bean.Song;
import com.yuzeduan.lovesong.songlist.bean.SongInfo;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 获取歌单音乐列表的数据
 * author: Allen
 * date: On 2018/9/25
 */
public class SongModel {

    public void getSongData(String code, final SongListener listener){
        String address = MusicApi.GeDan.geDanInfo(code);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                String jsonData = ParseJsonUtil.obtainDesignationJson(str, "content");
                List<SongInfo> list = new Gson().fromJson(jsonData, new TypeToken<List<SongInfo>>(){}.getType());
                listener.onSongDataFinish(list);
            }
        });
    }

    public interface SongListener{
        void onSongDataFinish(List<SongInfo> list);
    }
}
