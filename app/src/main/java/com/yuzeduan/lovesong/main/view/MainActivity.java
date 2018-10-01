package com.yuzeduan.lovesong.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;
import com.yuzeduan.lovesong.local.view.LocFragment;
import com.yuzeduan.lovesong.main.adapter.FragAdapter;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;
import com.yuzeduan.lovesong.music.view.BottomPlayFragment;
import com.yuzeduan.lovesong.recommend.view.RecFragment;
import com.yuzeduan.lovesong.search.view.SearchActivity;
import com.yuzeduan.lovesong.util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends BaseActivity {
    private FragAdapter mFragAdapter;
    private ViewPager mViewPager;
    private RadioButton mRecBtn, mLocBtn;
    private RadioGroup mRadioGroup;
    private ImageView mImageView;
    private FrameLayout mBottomLayout;
    private BottomPlayFragment mBottomFragment;
    private MusicConditionEvent mLastEvent; // 记录前一次的事件的信息

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
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new RecFragment());
        mFragments.add(new LocFragment());
        mFragAdapter = new FragAdapter(getSupportFragmentManager(), mFragments);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mRecBtn = findViewById(R.id.top_rg_a);
        mLocBtn = findViewById(R.id.top_rg_b);
        mImageView = findViewById(R.id.main_top_right);
        mRadioGroup = findViewById(R.id.main_top_rg);
        mViewPager = findViewById(R.id.main_viewpager);
        mBottomLayout = findViewById(R.id.main_bottom_layout);
        mViewPager.setAdapter(mFragAdapter);
        PermissionUtil.requestAllPower(this);
        initBottomFragment();
        initPagerEvent();
        initClick();
    }

    /**
     * 打开主活动时候,根据缓存的播放列表是否有歌曲,展示播放栏
     */
    private void initBottomFragment() {
        mBottomFragment = new BottomPlayFragment();
        replaceFragment(mBottomFragment);
    }

    private void initPagerEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) mRecBtn.setChecked(true);
                else mLocBtn.setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRecBtn.getId()) mViewPager.setCurrentItem(0);
                else if (checkedId == mLocBtn.getId()) mViewPager.setCurrentItem(1);
            }
        });
        //设置默认选中页
        mViewPager.setCurrentItem(0);
    }

    private void initClick() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected MusicConditionEvent getMusicCondition() {
        return mBottomFragment.getPlayerCondition();
    }

    @Subscribe(sticky = true)
    public void getMusicConditionEvent(MusicConditionEvent event){
        if(mBottomFragment == null){
            mBottomFragment = BottomPlayFragment.getInstance(event);
            replaceFragment(mBottomFragment);
        }else if(!mBottomFragment.isEventSame(mLastEvent, event)){
            // 如果两次事件信息不同,则要重置状态栏
            mBottomFragment.setConditionEvent(event);
        }
        mLastEvent = event;
    }

    /**
     * 将本地音乐的音乐列表传递给播放栏,并且传递点击的歌曲位置
     * @param list
     * @param position
     */
    public void setLocalSongListToBottom(List<Song> list, int position){
        mBottomFragment.setSongList(list, position);
    }

    /**
     * 替换底部的FrameLayout
     */
    private void replaceFragment(Fragment fragment){
        mBottomLayout.setVisibility(View.VISIBLE);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_bottom_layout, fragment);
        transaction.commit();
    }
}
