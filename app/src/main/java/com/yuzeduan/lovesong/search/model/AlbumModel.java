package com.yuzeduan.lovesong.search.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AlbumModel {

    public void getAlbumData(String str, int pageNo, final AlbumListener listener){
        String address = MusicApi.Search.searchMerge(str, pageNo, 10);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                String albumJson = ParseJsonUtil.obtainDesignationJson(str, "result.album_info.album_list");
                List<SearchAlbumList> albumList = new Gson().fromJson(albumJson, new TypeToken<List<SearchAlbumList>>(){}.getType());
                listener.onAlbumDataFinish(albumList);
            }
        });
    }

    public interface AlbumListener{
        void onAlbumDataFinish(List<SearchAlbumList> list);
    }
}
