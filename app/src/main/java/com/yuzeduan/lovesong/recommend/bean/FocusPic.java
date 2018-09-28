package com.yuzeduan.lovesong.recommend.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FocusPic {
    @Id(autoincrement = true)
    private Long id;
    @SerializedName("code")
    @Property(nameInDb = "mCode")
    private String mCode;
    @SerializedName("randpic")
    @Property(nameInDb = "mRandpic")
    private String mRandpic;
    @Generated(hash = 1677878316)
    public FocusPic(Long id, String mCode, String mRandpic) {
        this.id = id;
        this.mCode = mCode;
        this.mRandpic = mRandpic;
    }
    @Generated(hash = 1321136726)
    public FocusPic() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMCode() {
        return this.mCode;
    }
    public void setMCode(String mCode) {
        this.mCode = mCode;
    }
    public String getMRandpic() {
        return this.mRandpic;
    }
    public void setMRandpic(String mRandpic) {
        this.mRandpic = mRandpic;
    }

}
