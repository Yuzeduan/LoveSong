package com.yuzeduan.lovesong.search;

import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.bean.SearchArtistList;
import com.yuzeduan.lovesong.search.bean.SearchSongList;

import java.util.List;

public class MVPContract {

    public interface IView{
        void showHotWord(List<HotWord> list);
    }

    public interface IPresenter{
        void getHotWordData();
    }


    public interface ISongPresenter{
        void getData(String str, int pageNo);
        void getSong(String songId, int flag);
    }

    public interface ISongView{
        void showSongView(List<SearchSongList> list);
        void sendSelectSong(Song song, int flag);
    }

    public interface IAlbumView{
        void showAlbumView(List<SearchAlbumList> list);
    }

    public interface IArtistView{
        void showArtistView(List<SearchArtistList> list);
    }

    public interface ImpPresenter{
        void getData(String str, int pageNo);
    }
}
