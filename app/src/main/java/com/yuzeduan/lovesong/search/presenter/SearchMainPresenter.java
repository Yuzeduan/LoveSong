package com.yuzeduan.lovesong.search.presenter;

import android.os.Handler;
import android.os.Message;

import com.yuzeduan.lovesong.base.BasePresenter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.SearchMerge;
import com.yuzeduan.lovesong.search.model.SearchMainModel;

import java.lang.ref.WeakReference;

/**
 * 用于连接搜索主Fragment和model的数据中转站
 */
public class SearchMainPresenter extends BasePresenter<MVPContract.ImView> implements MVPContract.ImPresenter{
    private SearchMainModel mSearchModel;
    private SearchMainModel.SearchMainListener mListener;
    private UIHandler mUIHandler;

    public SearchMainPresenter() {
        mListener = new SearchMainModel.SearchMainListener() {
            @Override
            public void onMergeDataFinish(SearchMerge merge) {
                Message message = Message.obtain();
                message.obj = merge;
            }
        };
        mUIHandler = new UIHandler(this);
        mSearchModel = new SearchMainModel(mListener);
    }

    @Override
    public void getMergeData(String str) {
        mSearchModel.getMergeData(str);
    }

    private static class UIHandler extends Handler{
        private WeakReference<SearchMainPresenter> ref;

        UIHandler(SearchMainPresenter presenter) {
            ref = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            ref.get().getView().showSearchMerge((SearchMerge)msg.obj);
        }
    }
}
