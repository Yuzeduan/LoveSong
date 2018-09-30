package com.yuzeduan.lovesong.songlist.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.songlist.db.AlbumInfodao;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 获取专辑列表的数据
 * author: Allen
 * date: On 2018/9/25
 */
public class AlbumModel {

    public void getAlbumData(String code, final AlbumListener listener){
        List<AlbumInfo> albumList;
        final AlbumInfodao dao = new AlbumInfodao();
        if((albumList = dao.queryDataByAlbumId(code))!= null && !albumList.isEmpty()) {
            listener.onAlbumDataFinish(albumList);
        }else{
            String address = MusicApi.Album.albumInfo(code);
            HttpUtil.sendHttpRequest(address, new Callback() {
                @Override
                public void onFailure(@NonNull Call call,@NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String str = response.body().string();
                    String jsonData = ParseJsonUtil.obtainDesignationJson(str, "songlist");
                    List<AlbumInfo> list = new Gson().fromJson(jsonData, new TypeToken<List<AlbumInfo>>(){}.getType());
                    dao.insetData(list); // 添加数据进数据库
                    listener.onAlbumDataFinish(list);
                }
            });
        }
    }

    public void getSelectSongData(String songId, final AlbumListener listener){
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

    public interface AlbumListener{
        void onAlbumDataFinish(List<AlbumInfo> list);
        void onSelectSongDataFinish(Song song);
    }
}
