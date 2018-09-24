package com.yuzeduan.lovesong.search.event;

import com.yuzeduan.lovesong.search.bean.SearchArtistList;

import java.util.List;

/**
 * 查询到的歌手数据的消息事件类
 */
public class SearchArtistEvent {
    private List<SearchArtistList> mArtistList;

    public SearchArtistEvent(List<SearchArtistList> mArtistList) {
        this.mArtistList = mArtistList;
    }

    public List<SearchArtistList> getmArtistList() {
        return mArtistList;
    }
}
