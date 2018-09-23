package com.yuzeduan.lovesong.search;

import com.yuzeduan.lovesong.search.bean.HotWord;

import java.util.List;

public class MVPContract {

    public interface IView{
        void showHotWord(List<HotWord> list);
    }

    public interface IPresenter{
        void getHotWordData();
    }

}
