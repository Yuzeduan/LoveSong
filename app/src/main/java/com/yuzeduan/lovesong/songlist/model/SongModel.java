package com.yuzeduan.lovesong.songlist.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.songlist.bean.SongInfo;
import com.yuzeduan.lovesong.songlist.bean.SongInfoJsonBean;
import com.yuzeduan.lovesong.songlist.db.SongInfoJsonBeandao;
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
    private String mJsonData;

    public void getSongData(final String code, final SongListener listener){
        final SongInfoJsonBeandao dao = new SongInfoJsonBeandao();
        SongInfoJsonBean bean;
        if((bean = dao.queryDataBySongId(code)) != null){
            mJsonData = bean.getMJsonData();
            List<SongInfo> list = new Gson().fromJson(mJsonData, new TypeToken<List<SongInfo>>(){}.getType());
            listener.onSongDataFinish(list);
        }else{
            String address = MusicApi.GeDan.geDanInfo(code);
            HttpUtil.sendHttpRequest(address, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String str = response.body().string();
                    mJsonData = ParseJsonUtil.obtainDesignationJson(str, "content");
                    SongInfoJsonBean jsonBean = new SongInfoJsonBean();
                    jsonBean.setMJsonData(mJsonData);
                    jsonBean.setMSongId(code);
                    dao.insetData(jsonBean);  // 添加数据进数据库
                    List<SongInfo> list = new Gson().fromJson(mJsonData, new TypeToken<List<SongInfo>>(){}.getType());
                    listener.onSongDataFinish(list);
                }
            });
        }
    }

    public void getSelectSongData(final String songId, final SongListener listener){
        String address = MusicApi.Song.songInfo(songId);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                Song song = ParseJsonUtil.parseSongUtil(str);
                listener.onSelectSongDataFinish(song);
            }
        });
    }

    public interface SongListener{
        void onSongDataFinish(List<SongInfo> list);
        void onSelectSongDataFinish(Song song);
    }
}
