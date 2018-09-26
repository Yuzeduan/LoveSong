package com.yuzeduan.lovesong.recommend.event;

import com.yuzeduan.lovesong.recommend.bean.HotSongList;

/**
 * 点击热门歌单的时候EvenBus传递的消息事件类
 * author: Allen
 * date: On 2018/9/25
 */
public class HotSongEvent {
    private HotSongList mItem;

    public HotSongEvent(HotSongList mItem) {
        this.mItem = mItem;
    }

    public HotSongList getmItem() {
        return mItem;
    }
}
