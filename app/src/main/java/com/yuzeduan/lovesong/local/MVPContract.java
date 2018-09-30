package com.yuzeduan.lovesong.local;

import com.yuzeduan.lovesong.local.bean.MusicInfo;
import com.yuzeduan.lovesong.music.bean.Song;

import java.util.List;

public class MVPContract {

    public interface IView{
        void showMusicList(List<MusicInfo> list);
    }

    public interface IPresenter{
        void getData();
        List<Song> getSongData(List<MusicInfo> list);
    }
}
