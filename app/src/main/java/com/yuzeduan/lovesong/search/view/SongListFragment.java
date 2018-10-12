package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.adapter.SongListAdapter;
import com.yuzeduan.lovesong.search.bean.SearchSongList;
import com.yuzeduan.lovesong.search.event.SearchMessageEvent;
import com.yuzeduan.lovesong.search.event.SongEvent;
import com.yuzeduan.lovesong.search.presenter.SongPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class SongListFragment extends BaseFragment<MVPContract.ISongView, SongPresenter> implements MVPContract.ISongView{
    private String mSearchMsg;
    private RecyclerView mSongListRv;
    private SongListAdapter mAdapter;
    private List<SearchSongList> mSearchSongList;
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
    protected View initView(LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_search, container, false);
        mSongListRv = mView.findViewById(R.id.search_rv);
        return mView;
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshView() {
    }

    @Override
    protected SongPresenter createPresenter() {
        return new SongPresenter();
    }

    @Override
    public void showSongView(List<SearchSongList> list) {
        if(mAdapter == null){
            mSearchSongList = list;
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            mAdapter = new SongListAdapter(getContext(), list, R.layout.item_song);
            mSongListRv.setLayoutManager(manager);
            mSongListRv.setAdapter(mAdapter);
        }else {
            if(list != null){
                mSearchSongList.addAll(list);
                mAdapter.setmDatas(mSearchSongList);
            }
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
        mAdapter.setmOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                String songId = mSearchSongList.get(position).getmSongId();
                mPresenter.getSong(songId, 0);
            }

            @Override
            public void OnItemViewClick(int position) {
                String songId = mSearchSongList.get(position).getmSongId();
                mPresenter.getSong(songId, 1);
                Toast.makeText(getContext(), getContext().getResources().getString(R.string.addsong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sendSelectSong(Song song, int flag) {
        EventBus.getDefault().post(new SongEvent(song, flag));
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getSearchMessageEvent(SearchMessageEvent event){
        mSearchMsg = event.getmSearchMessage();mPresenter.getData(mSearchMsg,1);
        if(mSearchSongList != null){
            mSearchSongList.clear();
        }
    }
}
