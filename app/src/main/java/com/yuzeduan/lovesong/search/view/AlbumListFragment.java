package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
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
import com.yuzeduan.lovesong.search.adapter.AlbumListAdapter;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.event.SearchMessageEvent;
import com.yuzeduan.lovesong.search.presenter.AlbumPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class AlbumListFragment extends BaseFragment<MVPContract.IAlbumView, AlbumPresenter> implements MVPContract.IAlbumView{
    private String mSearchMsg;
    private RecyclerView mAlbumListRv;
    private AlbumListAdapter mAdapter;
    private int mPageNo = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Log.d("AlbumFragment", "onCreate: "+"调用了");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.d("AlbumFragment", "onDestroy: "+"调用了");
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_search, container, false);
        mAlbumListRv = mView.findViewById(R.id.search_rv);
        return mView;
    }

    @Override
    protected void lazyLoad() {
        if(isPrepared && isVisible && isFirst){
            Log.d("AlbumFragment", "lazyLoad: "+mSearchMsg);
            mPresenter.getData(mSearchMsg, 1);
        }
    }

    @Override
    protected void refreshView() {

    }

    @Override
    protected AlbumPresenter createPresenter() {
        return new AlbumPresenter();
    }

    @Override
    public void showAlbumView(List<SearchAlbumList> list) {
        if(mAdapter == null){
            mAdapter = new AlbumListAdapter(getContext(), list, R.layout.item_search_other);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAlbumListRv.setLayoutManager(manager);
            mAlbumListRv.setAdapter(mAdapter);
        }else{
            mAdapter.setDataChange(list);
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
        Log.d("AlbumFragment", "getSearchMessageEvent: "+mSearchMsg);
    }
}
