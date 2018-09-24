package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.adapter.ArtistListAdapter;
import com.yuzeduan.lovesong.search.bean.SearchArtistList;
import com.yuzeduan.lovesong.search.event.SearchMessageEvent;
import com.yuzeduan.lovesong.search.presenter.ArtistPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class ArtistListFragment extends BaseFragment<MVPContract.IArtistView, ArtistPresenter> implements MVPContract.IArtistView{
    private String mSearchMsg;
    private RecyclerView mArtistListRv;
    private ArtistListAdapter mAdapter;
    private int mPageNo = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Log.d("ArtistFragment", "onCreate: "+"调用了");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.d("ArtistFragment", "onDestroy: "+"调用了");
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_search, container, false);
        mArtistListRv = mView.findViewById(R.id.search_rv);
        return mView;
    }

    @Override
    protected void lazyLoad() {
        if(isPrepared && isVisible && isFirst){
            Log.d("ArtistFragment", "lazyLoad: "+mSearchMsg);
            mPresenter.getData(mSearchMsg,1);
        }
    }

    @Override
    protected void refreshView() {
    }

    @Override
    protected ArtistPresenter createPresenter() {
        return new ArtistPresenter();
    }

    @Override
    public void showArtistView(List<SearchArtistList> list) {
        if(mAdapter == null){
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAdapter = new ArtistListAdapter(getContext(), list, R.layout.item_search_other);
            mArtistListRv.setLayoutManager(manager);
            mArtistListRv.setAdapter(mAdapter);
        }
        initAdapterEvent();
    }

    private void initAdapterEvent() {
        mAdapter.setmOnLoadMoreListener(new CommonAdapter.onLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.getData(mSearchMsg, mPageNo++);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getSearchMessageEvent(SearchMessageEvent event){
        mSearchMsg = event.getmSearchMessage();
        Log.d("ArtistFragment", "getSearchMessageEvent: "+mSearchMsg);
    }
}
