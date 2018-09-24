package com.yuzeduan.lovesong.search.event;

import com.yuzeduan.lovesong.search.bean.SearchSongList;

import java.util.List;

/**
 * 查询到的歌曲的信息的消息事件类
 */
public class SearchSongEvent {
    private List<SearchSongList> mSongList;

    public SearchSongEvent(List<SearchSongList> mSongList) {
        this.mSongList = mSongList;
    }

    public List<SearchSongList> getmSongList() {
        return mSongList;
    }
}
