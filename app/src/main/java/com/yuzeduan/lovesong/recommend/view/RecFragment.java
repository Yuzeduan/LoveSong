package com.yuzeduan.lovesong.recommend.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.yuzeduan.lovesong.recommend.presenter.RecPresenter;

import java.lang.ref.WeakReference;
import java.util.List;

public class RecFragment extends BaseFragment<MVPContract.IView, RecPresenter> implements MVPContract.IView{
    private static final int FINISH_BANNERDATA = 1;
    private static final int FINISH_HOTSONGLISTDATA = 2;

    private RecyclerView mRecyclerView;
    private MultiAdapter mMultiAdapter;
    private FragmentHandler mFragmentHandler;
    private int time;

    @Override
    protected void initVariables() {
        mFragmentHandler = new FragmentHandler(this);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_recommend, container, false);
        mRecyclerView = mView.findViewById(R.id.rec_rcv);
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
    public void showHotSongList(List<HotSongList> list) {
        mMultiAdapter.setmHotSongList(list);
        Message message = Message.obtain();
        message.what = FINISH_HOTSONGLISTDATA;
        mFragmentHandler.sendMessage(message);
    }

    @Override
    public void showAlbumList(List<AlbumList> list) {
        mMultiAdapter.setmAlbumList(list);
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
            }
        }
    }
}
