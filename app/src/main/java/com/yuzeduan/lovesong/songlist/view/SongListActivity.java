package com.yuzeduan.lovesong.songlist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.MVPActivity;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.event.HotSongEvent;
import com.yuzeduan.lovesong.songlist.MVPContract;
import com.yuzeduan.lovesong.songlist.adapter.SongListAdapter;
import com.yuzeduan.lovesong.songlist.bean.SongInfo;
import com.yuzeduan.lovesong.songlist.presenter.SongPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class SongListActivity extends MVPActivity<MVPContract.ISongView, SongPresenter> implements MVPContract.ISongView {
    private HotSongList mHotSongList;
    private SongListAdapter mAdapter;
    private RecyclerView mSongRv;
    private Toolbar mToolbar;
    private TextView mTitleTv, mNameTv, mTagTv;
    private ImageView mTitleIv, mLittleIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_song_list);
        mSongRv = findViewById(R.id.song_rv);
        mToolbar = findViewById(R.id.toolbar);
        mTitleTv = findViewById(R.id.title_tv);
        mTitleIv = findViewById(R.id.iv);
        mLittleIv = findViewById(R.id.song_iv);
        mNameTv = findViewById(R.id.song_name_tv);
        mTagTv = findViewById(R.id.song_author_tv);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initEvent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void loadData() {
    }

    @Override
    protected SongPresenter createPresenter() {
        return new SongPresenter();
    }

    @Override
    public void showSongList(List<SongInfo> list) {
        if(list == null){
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdapter = new SongListAdapter(this, list, R.layout.item_song);
        mSongRv.setLayoutManager(manager);
        mSongRv.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getHotSongEvent(HotSongEvent event){
        mHotSongList = event.getmItem();
        mPresenter.getData(mHotSongList.getMListId());
        mTitleTv.setText(mHotSongList.getMTitle());
        mNameTv.setText(mHotSongList.getMTitle());
        mTagTv.setText(mHotSongList.getMTag());
        Glide.with(this)
             .load(mHotSongList.getMPicPath())
             .bitmapTransform(new BlurTransformation(this, 14, 3))
             .into(mTitleIv);
        Glide.with(this)
             .load(mHotSongList.getMPicPath())
             .into(mLittleIv);
    }

    private void initEvent(){

    }
}
