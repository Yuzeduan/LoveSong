package com.yuzeduan.lovesong.songlist.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.base.MVPActivity;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;
import com.yuzeduan.lovesong.music.view.BottomPlayFragment;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.event.AlbumEvent;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;
import com.yuzeduan.lovesong.search.event.SearchAlbumEvent;
import com.yuzeduan.lovesong.songlist.MVPContract;
import com.yuzeduan.lovesong.songlist.adapter.AlbumListAdapter;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.songlist.presenter.AlbumPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class AlbumListActivity extends MVPActivity<MVPContract.IAlbumView, AlbumPresenter> implements MVPContract.IAlbumView, CommonAdapter.OnItemClickListener{
    private AlbumList mAlbumList;
    private List<AlbumInfo> mAlbumInfoList;
    private AlbumListAdapter mAdapter;
    private RecyclerView mSongRv;
    private Toolbar mToolbar;
    private TextView mTitleTv, mNameTv, mAuthorTv, mTimeTv;
    private ImageView mTitleIv, mLittleIv;
    private FrameLayout mBottomLayout;
    private BottomPlayFragment mBottomPlayFragment;
    private MusicConditionEvent mLastEvent;


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
        mNameTv = findViewById(R.id.song_name_tv);
        mAuthorTv = findViewById(R.id.song_author_tv);
        mTimeTv = findViewById(R.id.song_time_tv);
        mLittleIv = findViewById(R.id.song_iv);
        mBottomLayout = findViewById(R.id.song_bottom_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    protected AlbumPresenter createPresenter() {
        return new AlbumPresenter();
    }

    @Override
    public void showAlbumList(List<AlbumInfo> list) {
        mAlbumInfoList = list;
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdapter = new AlbumListAdapter(this, list, R.layout.item_song);
        mSongRv.setLayoutManager(manager);
        mSongRv.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(this);
    }

    @Override
    public void OnItemClick(int position) {
        mPresenter.getSelectSongData(mAlbumInfoList.get(position).getMSongId(), 0);
    }

    @Override
    public void OnItemViewClick(int position) {
        mPresenter.getSelectSongData(mAlbumInfoList.get(position).getMSongId(), 1);
        Toast.makeText(this, this.getResources().getString(R.string.addsong), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSongToBottom(Song song, int flag) {
        if(flag == 0){
            mBottomPlayFragment.insetSongToPlay(song);
        }else{
            mBottomPlayFragment.insetSong(song);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getAlbumEvent(AlbumEvent event){
        AlbumList mAlbumList = event.getmItem();
        mPresenter.getData(mAlbumList.getMAlbumId());
        initCollLayout(mAlbumList.getMTitle(), mAlbumList.getMAuthor(),
                mAlbumList.getMPublishTime(), mAlbumList.getMPicPath());
        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getSearchEvent(SearchAlbumEvent event){
        SearchAlbumList item = event.getmItem();
        mPresenter.getData(item.getmAlbumId());
        initCollLayout(item.getmTitle(), item.getmAuthor(),
                item.getmPublishTime(), item.getmPicPath());
        EventBus.getDefault().removeStickyEvent(event);
    }

    /**
     * 设置折叠标题栏的元素的内容
     */
    public void initCollLayout(String title, String artistName, String publishTime, String picPath ){
        mTitleTv.setText(title);
        mNameTv.setText(title);
        mAuthorTv.setText("歌手:"+ artistName);
        mTimeTv.setText("发行时间:"+ publishTime);
        Glide.with(this)
                .load(picPath)
                .bitmapTransform(new BlurTransformation(this, 14, 3))
                .into(mTitleIv);
        Glide.with(this)
                .load(picPath)
                .into(mLittleIv);
    }

    @Override
    protected MusicConditionEvent getMusicCondition() {
        return mBottomPlayFragment.getPlayerCondition();
    }

    @Subscribe(sticky = true)
    public void getMusicConditionEvent(MusicConditionEvent event){
        if(mBottomPlayFragment == null){
            mBottomPlayFragment = BottomPlayFragment.getInstance(event);
            replaceFragment(mBottomPlayFragment);
        }else if(!mBottomPlayFragment.isEventSame(mLastEvent,event)){
            mBottomPlayFragment.setConditionEvent(event);
        }
        mLastEvent = event;
        EventBus.getDefault().removeStickyEvent(event);
    }

    /**
     * 替换底部的FrameLayout
     */
    private void replaceFragment(Fragment fragment){
        mBottomLayout.setVisibility(View.VISIBLE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.song_bottom_layout, fragment);
        transaction.commit();
    }
}
