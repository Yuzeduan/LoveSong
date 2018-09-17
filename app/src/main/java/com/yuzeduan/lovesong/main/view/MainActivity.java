package com.yuzeduan.lovesong.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;
import com.yuzeduan.lovesong.main.adapter.FragAdapter;
import com.yuzeduan.lovesong.recommend.view.RecFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private FragAdapter mFragAdapter;
    private ViewPager mViewPager;
    private RadioButton mRecBtn, mLocBtn;
    private RadioGroup mRadioGroup;

    @Override
    protected void initVariables() {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new RecFragment());
        mFragments.add(new RecFragment());
        mFragAdapter = new FragAdapter(getSupportFragmentManager(), mFragments);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mRecBtn = findViewById(R.id.top_rg_a);
        mLocBtn = findViewById(R.id.top_rg_b);
        mRadioGroup = findViewById(R.id.main_top_rg);
        mViewPager = findViewById(R.id.main_viewpager);
        mViewPager.setAdapter(mFragAdapter);
        initPagerEvent();
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

    @Override
    protected void loadData() {
    }
}
