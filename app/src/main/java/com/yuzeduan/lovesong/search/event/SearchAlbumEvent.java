package com.yuzeduan.lovesong.search.event;

import com.yuzeduan.lovesong.search.bean.SearchAlbumList;

/**
 * 用于点击搜索的专辑事件传递
 * author: Allen
 * date: On 2018/10/2
 */
public class SearchAlbumEvent {
    private SearchAlbumList mItem;

    public SearchAlbumEvent(SearchAlbumList mItem) {
        this.mItem = mItem;
    }

    public SearchAlbumList getmItem() {
        return mItem;
    }
}
