package com.yuzeduan.lovesong.search.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.bean.SearchArtistList;
import com.yuzeduan.lovesong.search.bean.SearchMerge;
import com.yuzeduan.lovesong.search.bean.SearchSongList;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 获取搜索结果的类
 */
public class SearchMainModel {
    private SearchMainListener mListener;

    public SearchMainModel(SearchMainListener mListener) {
        this.mListener = mListener;
    }

    public void getMergeData(String str){
        String address = MusicApi.Search.searchMerge(str, 1, 10);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                String str = response.body().string();
                String artistJson = ParseJsonUtil.obtainDesignationJson(str, "result.artist_info.artist_list");
                List<SearchArtistList> artistList = new Gson().fromJson(artistJson, new TypeToken<List<SearchArtistList>>(){}.getType());
                String albumJson = ParseJsonUtil.obtainDesignationJson(str, "result.album_info.album_list");
                List<SearchAlbumList> albumList = new Gson().fromJson(albumJson, new TypeToken<List<SearchAlbumList>>(){}.getType());
                String songJson = ParseJsonUtil.obtainDesignationJson(str, "result.song_info.song_list");
                List<SearchSongList> songList = new Gson().fromJson(songJson, new TypeToken<List<SearchSongList>>(){}.getType());
                SearchMerge merge = new SearchMerge(songList, artistList, albumList);
                mListener.onMergeDataFinish(merge);
            }
        });
    }

    public interface SearchMainListener{
        void onMergeDataFinish(SearchMerge merge);
    }
}
