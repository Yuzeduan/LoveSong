package com.yuzeduan.lovesong.search.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.search.bean.SearchSongList;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import de.greenrobot.event.Subscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class SongModel {

    public void getSongData(String str, int pageNo, final SongListener listener){
        String address = MusicApi.Search.searchMerge(str, pageNo, 10);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                String songJson = ParseJsonUtil.obtainDesignationJson(str, "result.song_info.song_list");
                List<SearchSongList> songList = new Gson().fromJson(songJson, new TypeToken<List<SearchSongList>>(){}.getType());
                listener.onSongDataFinish(songList);
            }
        });
    }

    public void getSelectedSongData(final String songId, final SongListener listener){
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
        void onSongDataFinish(List<SearchSongList> list);
        void onSelectSongDataFinish(Song song);
    }
}
