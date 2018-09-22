package com.yuzeduan.lovesong.recommend.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.recommend.MVPContract;
import com.yuzeduan.lovesong.recommend.adapter.MultiAdapter;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.bean.RadioList;
import com.yuzeduan.lovesong.recommend.presenter.RecPresenter;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.yuzeduan.lovesong.recommend.presenter.RecPresenter.REFRESH;

public class RecFragment extends BaseFragment<MVPContract.IView, RecPresenter> implements MVPContract.IView{
    private static final int FINISH_BANNERDATA = 1;
    private static final int FINISH_HOTSONGLISTDATA = 2;
    private static final int FINISH_ALBUMLISTDATA = 3;
    private static final int FINISH_REFRESH_BANNERDATA = 4;
    private static final int FINISH_REFRESH_HOTSONGLIST_DATA = 5;
    private static final int FINISH_REFRESH_ALBUMLIST_DATA = 6;

    private RecyclerView mRecyclerView;
    private MultiAdapter mMultiAdapter;
    private FragmentHandler mFragmentHandler;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int time;

    @Override
    protected void initVariables() {
        mFragmentHandler = new FragmentHandler(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_recommend, container, false);
        mRecyclerView = mView.findViewById(R.id.rec_rcv);
        mSwipeRefreshLayout = mView.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.title_bar);
        mSwipeRefreshLayout.setRefreshing(true);
        return mView;
    }

    @Override
    protected void lazyLoad() {
        if(isPrepared && isVisible && time == 0){
            mPresenter.getBannerData();
            time++;
        }
    }

    @Override
    public void showRefreshData(List<FocusPic> list) {
        mMultiAdapter.setmPicList(list);
        Message message = Message.obtain();
        message.what = FINISH_REFRESH_BANNERDATA;
        mFragmentHandler.sendMessage(message);
    }

    @Override
    protected void refreshView() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getRefreshBannerData();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected RecPresenter createPresenter() {
        return new RecPresenter();
    }

    @Override
    public void showBanner(List<FocusPic> list) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));
        mMultiAdapter = new MultiAdapter(list);
        mRecyclerView.setAdapter(mMultiAdapter);
        Message message = Message.obtain();
        message.what = FINISH_BANNERDATA;
        mFragmentHandler.sendMessage(message);
    }

    @Override
    public void showHotSongList(List<HotSongList> list, int flag) {
        mMultiAdapter.setmHotSongList(list);
        Message message = Message.obtain();
        if(flag == REFRESH){
            message.what = FINISH_REFRESH_HOTSONGLIST_DATA;
        }else{
            message.what = FINISH_HOTSONGLISTDATA;
        }
        mFragmentHandler.sendMessage(message);
    }

    @Override
    public void showAlbumList(List<AlbumList> list, int flag) {
        mMultiAdapter.setmAlbumList(list);
        Message message = Message.obtain();
        if(flag == REFRESH){
            message.what = FINISH_REFRESH_ALBUMLIST_DATA;
        }else{
            message.what = FINISH_ALBUMLISTDATA;
        }
        mFragmentHandler.sendMessage(message);
    }

    @Override
    public void showRadioList(List<RadioList> list, int flag) {
        mMultiAdapter.setmRadioList(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private static class FragmentHandler extends Handler{
        private WeakReference<RecFragment> recFragmentWeakReference;

        FragmentHandler(RecFragment recFragment) {
            recFragmentWeakReference = new WeakReference<>(recFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case FINISH_BANNERDATA:
                    recFragmentWeakReference.get().mPresenter.getHotSongListData();
                    break;
                case FINISH_HOTSONGLISTDATA:
                    recFragmentWeakReference.get().mPresenter.getAlbumListData();
                    break;
                case FINISH_ALBUMLISTDATA:
                    recFragmentWeakReference.get().mPresenter.getRadioListData();
                    break;
                case FINISH_REFRESH_BANNERDATA:
                    recFragmentWeakReference.get().mPresenter.getRefreshHotSongListData();
                    break;
                case FINISH_REFRESH_HOTSONGLIST_DATA:
                    recFragmentWeakReference.get().mPresenter.getRefreshAlbumListData();
                    break;
                case FINISH_REFRESH_ALBUMLIST_DATA:
                    recFragmentWeakReference.get().mPresenter.getRefreshRadioListData();
                    break;
            }
        }
    }
}
