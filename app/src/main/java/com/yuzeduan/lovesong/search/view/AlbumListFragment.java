package com.yuzeduan.lovesong.search.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.adapter.AlbumListAdapter;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.event.SearchAlbumEvent;
import com.yuzeduan.lovesong.search.event.SearchMessageEvent;
import com.yuzeduan.lovesong.search.presenter.AlbumPresenter;
import com.yuzeduan.lovesong.songlist.view.AlbumListActivity;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class AlbumListFragment extends BaseFragment<MVPContract.IAlbumView, AlbumPresenter> implements MVPContract.IAlbumView, CommonAdapter.OnItemClickListener{
    private String mSearchMsg;
    private RecyclerView mAlbumListRv;
    private AlbumListAdapter mAdapter;
    private List<SearchAlbumList> mSearchAlbumList;
    private int mPageNo = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
            mSearchAlbumList = list;
            mAdapter = new AlbumListAdapter(getContext(), list, R.layout.item_search_other);
            mAdapter.setmOnItemClickListener(this);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAlbumListRv.setLayoutManager(manager);
            mAlbumListRv.setAdapter(mAdapter);
        }else{
            if(list != null){
                mSearchAlbumList.addAll(list);
                mAdapter.setmDatas(mSearchAlbumList);
            }
        }
        initAdapterEvent();
    }

    @Override
    public void OnItemClick(int position) {
        SearchAlbumList item = mSearchAlbumList.get(position);
        EventBus.getDefault().postSticky(new SearchAlbumEvent(item));
        Intent intent = new Intent(getContext(), AlbumListActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnItemViewClick(int position) {
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
        mPresenter.getData(mSearchMsg, 1);
        if(mSearchAlbumList != null){
            mSearchAlbumList.clear();
        }
    }
}
