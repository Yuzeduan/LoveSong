package com.yuzeduan.lovesong.search.bean;

import com.google.gson.annotations.SerializedName;

public class HotWord {
    @SerializedName("word")
    private String mWord;
    @SerializedName("linkurl")
    private String mLinkUrl;

    public String getmWord() {
        return mWord;
    }

    public void setmWord(String mWord) {
        this.mWord = mWord;
    }

    public String getmLinkUrl() {
        return mLinkUrl;
    }

    public void setmLinkUrl(String mLinkUrl) {
        this.mLinkUrl = mLinkUrl;
    }
}
