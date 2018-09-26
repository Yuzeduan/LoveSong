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
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.event.AlbumEvent;
import com.yuzeduan.lovesong.songlist.MVPContract;
import com.yuzeduan.lovesong.songlist.adapter.AlbumListAdapter;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.songlist.presenter.AlbumPresenter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class AlbumListActivity extends MVPActivity<MVPContract.IAlbumView, AlbumPresenter> implements MVPContract.IAlbumView{
    private AlbumList mAlbumList;
    private AlbumListAdapter mAdapter;
    private RecyclerView mSongRv;
    private Toolbar mToolbar;
    private TextView mTitleTv, mNameTv, mAuthorTv, mTimeTv;
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
        mNameTv = findViewById(R.id.song_name_tv);
        mAuthorTv = findViewById(R.id.song_author_tv);
        mTimeTv = findViewById(R.id.song_time_tv);
        mLittleIv = findViewById(R.id.song_iv);
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
    protected AlbumPresenter createPresenter() {
        return new AlbumPresenter();
    }

    @Override
    public void showAlbumList(List<AlbumInfo> list) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mAdapter = new AlbumListAdapter(this, list, R.layout.item_song);
        mSongRv.setLayoutManager(manager);
        mSongRv.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void getAlbumEvent(AlbumEvent event){
        mAlbumList= event.getmItem();
        mPresenter.getData(mAlbumList.getmAlbumId());
        mTitleTv.setText(mAlbumList.getmTitle());
        mNameTv.setText(mAlbumList.getmTitle());
        mAuthorTv.setText("歌手:"+mAlbumList.getmAuthor());
        mTimeTv.setText("发行时间:"+mAlbumList.getmPublishTime());
        Glide.with(this)
             .load(mAlbumList.getmPicPath())
             .bitmapTransform(new BlurTransformation(this, 14, 3))
             .into(mTitleIv);
        Glide.with(this)
             .load(mAlbumList.getmPicPath())
             .into(mLittleIv);
    }

    private void initEvent(){
    }
}
