package com.yuzeduan.lovesong.search.bean;

import java.util.List;

/**
 * 用于存放搜索结果的三种对象容器的实体类
 */
public class SearchMerge {
    private List<SearchSongList> mSongList;
    private List<SearchArtistList> mArtistList;
    private List<SearchAlbumList> mAlbumList;

    public List<SearchSongList> getmSongList() {
        return mSongList;
    }

    public void setmSongList(List<SearchSongList> mSongList) {
        this.mSongList = mSongList;
    }

    public List<SearchArtistList> getmArtistList() {
        return mArtistList;
    }

    public void setmArtistList(List<SearchArtistList> mArtistList) {
        this.mArtistList = mArtistList;
    }

    public List<SearchAlbumList> getmAlbumList() {
        return mAlbumList;
    }

    public void setmAlbumList(List<SearchAlbumList> mAlbumList) {
        this.mAlbumList = mAlbumList;
    }

    public SearchMerge(List<SearchSongList> mSongList, List<SearchArtistList> mArtistList, List<SearchAlbumList> mAlbumList) {
        this.mSongList = mSongList;
        this.mArtistList = mArtistList;
        this.mAlbumList = mAlbumList;
    }
}
