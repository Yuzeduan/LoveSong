package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

public class FocusPic {
    @SerializedName("code")
    private String mCode;
    @SerializedName("randpic")
    private String mRandpic;

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmRandpic() {
        return mRandpic;
    }

    public void setmRandpic(String mRandpic) {
        this.mRandpic = mRandpic;
    }
}
