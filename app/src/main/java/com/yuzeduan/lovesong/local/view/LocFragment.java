package com.yuzeduan.lovesong.local.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.local.MVPContract;
import com.yuzeduan.lovesong.local.adapter.LocAdapter;
import com.yuzeduan.lovesong.local.bean.MusicInfo;
import com.yuzeduan.lovesong.local.presenter.LocPresenter;
import com.yuzeduan.lovesong.main.view.MainActivity;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.widget.IndexBar;

import java.util.List;

public class LocFragment extends BaseFragment<MVPContract.IView, LocPresenter> implements MVPContract.IView, CommonAdapter.OnItemClickListener{
    private RecyclerView mRecycleView;
    private IndexBar mIndexBar;
    private TextView mCenterTv;
    private LinearLayoutManager mLinearLayoutManager;
    private LocAdapter mLocAdapter;
    private List<MusicInfo> mMusicInfoList;

    @Override
    protected LocPresenter createPresenter() {
        return new LocPresenter();
    }

    @Override
    protected void initVariables() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getData();
        initEvent();
    }

    private void initEvent() {
        mIndexBar.setmTextView(mCenterTv);
        mIndexBar.setmListener(new IndexBar.OnChooseListener() {
            @Override
            public void touchCharacterListener(int position) {
                int i = mLocAdapter.getPositionForSection(position);
                //如果找不到对应的位置则不进行滑动
                if( i != -1){
                    mLinearLayoutManager.scrollToPositionWithOffset(i, 0);
                }
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_local, container, false);
        mRecycleView = mView.findViewById(R.id.local_rv);
        mIndexBar = mView.findViewById(R.id.local_idr);
        mCenterTv = mView.findViewById(R.id.center_tv);
        return mView;
    }

    @Override
    public void showMusicList(List<MusicInfo> list) {
        mMusicInfoList = list;
        mRecycleView.setLayoutManager(mLinearLayoutManager);
        mLocAdapter = new LocAdapter(getContext(), mMusicInfoList, R.layout.item_local);
        mLocAdapter.setmOnItemClickListener(this);
        mRecycleView.setAdapter(mLocAdapter);
    }

    @Override
    protected void refreshView() {
    }

    @Override
    public void OnItemClick(int position) {
        List<Song> list =  mPresenter.getSongData(mMusicInfoList);
        MainActivity activity = (MainActivity)getActivity();
        if(activity != null) {
            activity.setLocalSongListToBottom(list, position);
        }
    }

    @Override
    public void OnItemViewClick(int position) {
    }
}
