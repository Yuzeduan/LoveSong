package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.search.model.SearchHotWordModel;

import java.lang.ref.WeakReference;
import java.util.List;


public class SearchHotWordPresenter extends BasePresenter<MVPContract.IView> implements MVPContract.IPresenter{
    private static final int GET_HOTWORD_DATA = 1;
    private SearchHotWordModel mSearchHotWordModel;
    private UIHandler mUIHandler;

    public SearchHotWordPresenter() {
        SearchHotWordModel.SearchListener listener = new SearchHotWordModel.SearchListener() {
            @Override
            public void onHotWordListener(List<HotWord> list) {
                Message message = Message.obtain();
                message.what = GET_HOTWORD_DATA;
                message.obj = list;
                mUIHandler.sendMessage(message);
            }
        };
        mUIHandler = new UIHandler(this);
        mSearchHotWordModel = new SearchHotWordModel(listener);
    }

    @Override
    public void getHotWordData() {
        mSearchHotWordModel.getHotWordData();
    }

    private static class UIHandler extends Handler {
        private WeakReference<SearchHotWordPresenter> reference;

        public UIHandler(SearchHotWordPresenter searchHotWordPresenter) {
            reference = new WeakReference<>(searchHotWordPresenter);
        }

        @Override
        public void handleMessage(Message msg) {
            SearchHotWordPresenter presenter = reference.get();
            switch (msg.what){
                case GET_HOTWORD_DATA:
                    presenter.getView().showHotWord((List<HotWord>)msg.obj);
                    break;
            }
        }
    }
}
