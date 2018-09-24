package com.yuzeduan.lovesong.search;

import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.bean.SearchArtistList;
import com.yuzeduan.lovesong.search.bean.SearchMerge;
import com.yuzeduan.lovesong.search.bean.SearchSongList;

import java.util.List;

public class MVPContract {

    public interface IView{
        void showHotWord(List<HotWord> list);
    }

    public interface IPresenter{
        void getHotWordData();
    }

    public interface ImView{
        void showSearchMerge(SearchMerge searchMerge);
    }

    public interface ImPresenter{
        void getMergeData(String str);
    }

    public interface ISongView{
        void showSongView(List<SearchSongList> list);
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
