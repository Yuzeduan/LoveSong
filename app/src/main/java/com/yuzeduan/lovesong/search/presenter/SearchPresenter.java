package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.search.model.SearchModel;

import java.lang.ref.WeakReference;
import java.util.List;


public class SearchPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter{
    private static final int GET_HOTWORD_DATA = 1;
    private SearchModel mSearchModel;
    private UIHandler mUIHandler;

    public SearchPresenter() {
        SearchModel.SearchListener listener = new SearchModel.SearchListener() {
            @Override
            public void onHotWordListener(List<HotWord> list) {
                Message message = Message.obtain();
                message.what = GET_HOTWORD_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }
        };
        mUIHandler = new UIHandler(this);
        mSearchModel = new SearchModel(listener);
    }

    @Override
    public void getHotWordData() {
        mSearchModel.getHotWordData();
    }

    private static class UIHandler extends Handler {
        private WeakReference<SearchPresenter> reference;

        public UIHandler(SearchPresenter searchPresenter) {
            reference = new WeakReference<>(searchPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            SearchPresenter presenter = reference.get();
            switch (msg.what){
                case GET_HOTWORD_DATA:
                    presenter.getView().showHotWord((List<HotWord>)msg.obj);
                    break;
            }
        }
    }
}
