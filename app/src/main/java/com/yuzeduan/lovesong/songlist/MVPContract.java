package com.yuzeduan.lovesong.songlist;

import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.songlist.bean.SongInfo;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/25
 */
public class MVPContract {

    public interface ISongView{
        void showSongList(List<SongInfo> list);
        void setSongToBottom(Song song, int flag);
    }

    public interface IAlbumView{
        void showAlbumList(List<AlbumInfo> list);
        void setSongToBottom(Song song, int flag);
    }

    public interface IPresenter{
        void getData(String code);
        void getSelectSongData(String songId, int flag);
    }
}
