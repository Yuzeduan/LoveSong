package com.yuzeduan.lovesong.recommend.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.bean.RadioList;
import com.yuzeduan.lovesong.recommend.db.AlbumListdao;
import com.yuzeduan.lovesong.recommend.db.FocusPicdao;
import com.yuzeduan.lovesong.recommend.db.HotSongListdao;
import com.yuzeduan.lovesong.recommend.db.RadioListdao;
import com.yuzeduan.lovesong.util.HttpUtil;
import com.yuzeduan.lovesong.util.ParseJsonUtil;

import java.io.IOException;
import java.util.List;

import api.MusicApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecModel {
    private RecListener mListener;

    public RecModel(RecListener mListener) {
        this.mListener = mListener;
    }

    public void getBannerData(){
        final FocusPicdao dao = new FocusPicdao();
        List<FocusPic> focusList;
        if((focusList = dao.queryData()) != null && !focusList.isEmpty()){
            mListener.onBannerDataFinish(focusList);
        }else{
            String address = MusicApi.focusPic(0);
            HttpUtil.sendHttpRequest(address, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String str = response.body().string();
                    str = ParseJsonUtil.obtainDesignationJson(str, "pic");
                    List<FocusPic> list = new Gson().fromJson(str, new TypeToken<List<FocusPic>>(){}.getType());
                    dao.insetData(list);
                    mListener.onBannerDataFinish(list);
                }
            });
        }
    }

    public void getHotSongListData(){
        final HotSongListdao dao = new HotSongListdao();
        List<HotSongList> songList;
        if((songList = dao.queryData()) != null && !songList.isEmpty()){
            mListener.onHotSongListDataFinish(songList);
        }else{
            String address = MusicApi.GeDan.hotGeDan(0);
            HttpUtil.sendHttpRequest(address, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String str = response.body().string();
                    str = ParseJsonUtil.obtainDesignationJson(str, "content.list");
                    List<HotSongList> list = new Gson().fromJson(str, new TypeToken<List<HotSongList>>(){}.getType());
                    dao.insetData(list);
                    mListener.onHotSongListDataFinish(list);
                }
            });
        }
    }

    public void getAlbumListData(){
        final AlbumListdao dao = new AlbumListdao();
        List<AlbumList> albumList;
        if((albumList = dao.queryData()) != null && !albumList.isEmpty()){
            mListener.onAlbumListDataFinish(albumList);
        }else{
            String address = MusicApi.Album.recommendAlbum(0,6);
            HttpUtil.sendHttpRequest(address, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String str = response.body().string();
                    str = ParseJsonUtil.obtainDesignationJson(str, "plaze_album_list.RM.album_list.list");
                    List<AlbumList> list = new Gson().fromJson(str, new TypeToken<List<AlbumList>>(){}.getType());
                    dao.insetData(list);
                    mListener.onAlbumListDataFinish(list);
                }
            });
        }
    }

    public void getRadioListData(){
        final RadioListdao dao = new RadioListdao();
        List<RadioList> radioList;
        if((radioList = dao.queryData()) != null && !radioList.isEmpty()){
            mListener.onRadioListDataFinish(radioList);
        }else{
            String address = MusicApi.Radio.recommendRadioList(0);
            HttpUtil.sendHttpRequest(address, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull  Call call, @NonNull Response response) throws IOException {
                    String str = response.body().string();
                    str = ParseJsonUtil.obtainDesignationJson(str, "list");
                    List<RadioList> list = new Gson().fromJson(str, new TypeToken<List<RadioList>>(){}.getType());
                    dao.insetData(list);
                    mListener.onRadioListDataFinish(list);
                }
            });
        }
    }

    public void getRefreshBannerData(){
        final FocusPicdao dao = new FocusPicdao();
        String address = MusicApi.focusPic(0);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                str = ParseJsonUtil.obtainDesignationJson(str, "pic");
                List<FocusPic> list = new Gson().fromJson(str, new TypeToken<List<FocusPic>>(){}.getType());
                dao.insetData(list);
                mListener.onRefreshBannerDataFinish(list);
            }
        });
    }

    public void getRefreshHotSongListData(){
        final HotSongListdao dao = new HotSongListdao();
        String address = MusicApi.GeDan.hotGeDan(0);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                str = ParseJsonUtil.obtainDesignationJson(str, "content.list");
                List<HotSongList> list = new Gson().fromJson(str, new TypeToken<List<HotSongList>>(){}.getType());
                dao.insetData(list);
                mListener.onRefreshHotSongListData(list);
            }
        });
    }

    public void getRefreshAlbumListData(){
        final AlbumListdao dao = new AlbumListdao();
        String address = MusicApi.Album.recommendAlbum(0,6);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                str = ParseJsonUtil.obtainDesignationJson(str, "plaze_album_list.RM.album_list.list");
                List<AlbumList> list = new Gson().fromJson(str, new TypeToken<List<AlbumList>>(){}.getType());
                dao.insetData(list);
                mListener.onRefreshAlbumListDataFinish(list);
            }
        });
    }

    public void getRefreshRadioListData(){
        final RadioListdao dao = new RadioListdao();
        String address = MusicApi.Radio.recommendRadioList(0);
        HttpUtil.sendHttpRequest(address, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull  Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                str = ParseJsonUtil.obtainDesignationJson(str, "list");
                List<RadioList> list = new Gson().fromJson(str, new TypeToken<List<RadioList>>(){}.getType());
                dao.insetData(list);
                mListener.onRefreshRadioListDataFinish(list);
            }
        });
    }

    public interface RecListener {
        void onBannerDataFinish(List<FocusPic> list);

        void onHotSongListDataFinish(List<HotSongList> list);

        void onAlbumListDataFinish(List<AlbumList> list);

        void onRadioListDataFinish(List<RadioList> list);

        void onRefreshBannerDataFinish(List<FocusPic> list);

        void onRefreshHotSongListData(List<HotSongList> list);

        void onRefreshAlbumListDataFinish(List<AlbumList> list);

        void onRefreshRadioListDataFinish(List<RadioList> list);
    }
}
