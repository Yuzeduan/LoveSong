package com.yuzeduan.lovesong.recommend;

import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.bean.RadioList;

import java.util.List;

/**
 * 对view层和presenter层进行规范
 */
public class MVPContract {

    public interface IView {
        void showBanner(List<FocusPic> list);
        void showHotSongList(List<HotSongList> list, int flag);
        void showAlbumList(List<AlbumList> list, int flag);
        void showRadioList(List<RadioList> list, int flag);
        void showRefreshData(List<FocusPic> list);
    }

    public interface IPresenter {
        void getBannerData();
        void getHotSongListData();
        void getAlbumListData();
        void getRadioListData();
        void getRefreshBannerData();
        void getRefreshHotSongListData();
        void getRefreshAlbumListData();
        void getRefreshRadioListData();
    }
}
