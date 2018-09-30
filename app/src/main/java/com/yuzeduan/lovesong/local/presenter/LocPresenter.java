package com.yuzeduan.lovesong.local.presenter;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.local.MVPContract;
import com.yuzeduan.lovesong.local.bean.MusicInfo;
import com.yuzeduan.lovesong.local.model.LocModel;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.ArrayList;
import java.util.List;

public class LocPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter{
    private LocModel mLocModel;

    public LocPresenter() {
        mLocModel = new LocModel();
    }

    @Override
    public void getData() {
        List<MusicInfo> list = mLocModel.getMusicList(LoveSongApplication.getContext().getContentResolver());
        getView().showMusicList(list);
    }

    /**
     * 将本地音乐转换为播放的音乐对象
     * @param list
     * @return
     */
    @Override
    public List<Song> getSongData(List<MusicInfo> list) {
        List<Song> songList = new ArrayList<>();
        for(MusicInfo musicInfo : list){
            Song song = new Song();
            song.setmSongId(musicInfo.getmId()+"");
            song.setmSongAddress(musicInfo.getmUrl());
            song.setmArtist(musicInfo.getmArtist());
            song.setmSongName(musicInfo.getmTitle());
            song.setmSmallPicPath(musicInfo.getmAlbumId()+"");
            song.setLocal(true);
            songList.add(song);
        }
        return songList;
    }
}
