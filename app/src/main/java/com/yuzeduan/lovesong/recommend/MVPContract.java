package com.yuzeduan.lovesong.recommend;

import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;

import java.util.List;

/**
 * 对view层和presenter层进行规范
 */
public class MVPContract {

    public interface IView {
        void showBanner(List<FocusPic> list);
        void showHotSongList(List<HotSongList> list);
    }

    public interface IPresenter {
        void getBannerData();
        void getHotSongListData();
    }
}
