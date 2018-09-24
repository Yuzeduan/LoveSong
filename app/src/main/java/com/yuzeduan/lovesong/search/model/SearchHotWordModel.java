package com.yuzeduan.lovesong.search.model;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchHotWordModel {
    private SearchListener mSearchListener;

    public SearchHotWordModel(SearchListener mSearchListener) {
        this.mSearchListener = mSearchListener;
    }

    public void getHotWordData(){
        String address = MusicApi.Search.hotWord();
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                str = ParseJsonUtil.obtainDesignationJson(str, "result");
                List<HotWord> list = new Gson().fromJson(str, new TypeToken<List<HotWord>>(){}.getType());
                mSearchListener.onHotWordListener(list);
            }
        });
    }
    public interface SearchListener{
        void onHotWordListener(List<HotWord> list);
    }
}
