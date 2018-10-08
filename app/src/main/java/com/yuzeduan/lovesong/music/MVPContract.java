package com.yuzeduan.lovesong.music;

import com.yuzeduan.lovesong.music.bean.LrcEntity;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/10/8
 */
public class MVPContract {
    public interface IView{
        void showLrc(List<LrcEntity> list);
    }

    public interface IPresenter{
        void getData(String address);
    }
}
