package com.yuzeduan.lovesong.search.event;

import com.yuzeduan.lovesong.search.bean.SearchAlbumList;

import java.util.List;

/**
 * 查询到的专辑数据的消息事件类
 */
public class SearchAlbumEvent {
    private List<SearchAlbumList> mAlbumList;

    public SearchAlbumEvent(List<SearchAlbumList> mAlbumList) {
        this.mAlbumList = mAlbumList;
    }

    public List<SearchAlbumList> getmAlbumList() {
        return mAlbumList;
    }
}
