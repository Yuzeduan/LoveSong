package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;
import com.yuzeduan.lovesong.music.view.BottomPlayFragment;
import com.yuzeduan.lovesong.search.event.SearchMessageEvent;
import com.yuzeduan.lovesong.search.event.SongEvent;
import com.yuzeduan.lovesong.util.DensityUtil;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 搜索的主界面
 */
public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener{
    private ImageButton mImageButton;
    private SearchView mSearchView;
    private BottomPlayFragment mBottomPlayFragment;

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
        setContentView(R.layout.activity_search);
        mImageButton = findViewById(R.id.back_ibn);
        mSearchView = findViewById(R.id.search_sv);
        mSearchView.setOnQueryTextListener(this);
        replaceFragment(R.id.below_flt, new HotWordFragment());
        initClick();
        setSearchView();
    }

    private void setSearchView() {
        final EditText editText = mSearchView.findViewById(R.id.search_src_text);
        editText.setTextSize(DensityUtil.dpToPx(5));
        editText.setTextColor(getResources().getColor(R.color.white));
    }

    private void initClick() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        replaceFragment(R.id.below_flt, new SearchMainFragment());
        EventBus.getDefault().postSticky(new SearchMessageEvent(s));
        Log.d("SearchActivity", "onQueryTextSubmit: "+s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.d("SearchActivity", "onQueryTextChange: ");
        if(s.equals("")) {
            replaceFragment(R.id.below_flt, new HotWordFragment());
        }
        return false;
    }

    /**
     * 用来将FrameLayout换成Fragment
     * @param fragment
     */
    private void replaceFragment(int viewId, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(viewId, fragment);
        transaction.commit();
    }

    @Override
    protected MusicConditionEvent getMusicCondition() {
        return mBottomPlayFragment.getPlayerCondition();
    }

    @Subscribe(sticky = true)
    public void getMusicConditionEvent(MusicConditionEvent event){
        mBottomPlayFragment = BottomPlayFragment.getInstance(event);
        replaceFragment(R.id.search_bottom_layout, mBottomPlayFragment);
        EventBus.getDefault().removeStickyEvent(event);
    }

    /**
     * 用于接收点击的歌曲对象事件
     * @param
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getSongEvent(SongEvent event){
        Song song = event.getmSong();
        int flag = event.getmFlag();
        // flag用来判断是添加歌曲还是添加并播放
        if(flag == 0){
            mBottomPlayFragment.insetSongToPlay(song);
        }else{
            mBottomPlayFragment.insetSong(song);
        }
    }
}
