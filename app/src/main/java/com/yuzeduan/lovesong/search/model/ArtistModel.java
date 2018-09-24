package com.yuzeduan.lovesong.search.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.search.bean.SearchArtistList;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ArtistModel {

    /**
     * 获取查询得到的歌手数据
     * @param str
     * @param pageNo
     */
    public void getArtistData(String str, int pageNo, final ArtistListener listener){
        String address = MusicApi.Search.searchMerge(str, pageNo, 10);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                String artistJson = ParseJsonUtil.obtainDesignationJson(str, "result.artist_info.artist_list");
                List<SearchArtistList> artistList = new Gson().fromJson(artistJson, new TypeToken<List<SearchArtistList>>(){}.getType());
                listener.onArtistDataFinish(artistList);
            }
        });
    }

    public interface ArtistListener{
        void onArtistDataFinish(List<SearchArtistList> list);
    }
}
