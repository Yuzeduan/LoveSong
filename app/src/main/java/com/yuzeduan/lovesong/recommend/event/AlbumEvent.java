package com.yuzeduan.lovesong.recommend.event;

import com.yuzeduan.lovesong.recommend.bean.AlbumList;

/**
 * 用于点击专辑时候EventBus传递的消息事件
 * author: Allen
 * date: On 2018/9/25
 */
public class AlbumEvent {
    private AlbumList mItem;

    public AlbumEvent(AlbumList mItem) {
        this.mItem = mItem;
    }

    public AlbumList getmItem() {
        return mItem;
    }
}
