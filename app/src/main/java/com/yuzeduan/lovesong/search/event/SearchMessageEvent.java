package com.yuzeduan.lovesong.search.event;

/**
 * 用户输入的搜索信息的消息事件类
 */
public class SearchMessageEvent {

    private String mSearchMessage;

    public SearchMessageEvent(String mSearchMessage) {
        this.mSearchMessage = mSearchMessage;
    }

    public String getmSearchMessage() {
        return mSearchMessage;
    }

}
