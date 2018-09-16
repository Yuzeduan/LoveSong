package com.yuzeduan.lovesong.recommend;

import com.yuzeduan.lovesong.recommend.bean.FocusPic;

import java.util.List;

/**
 * 对view层和presenter层进行规范
 */
public class MVPContract {

    public interface IView {
        void showBanner(List<FocusPic> list);
    }

    public interface IPresenter {
        void getBannerData();
    }
}
