package com.yuzeduan.lovesong.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseActivity;
import com.yuzeduan.lovesong.local.view.LocFragment;
import com.yuzeduan.lovesong.main.adapter.FragAdapter;
import com.yuzeduan.lovesong.recommend.view.RecFragment;
import com.yuzeduan.lovesong.search.view.SearchActivity;
import com.yuzeduan.lovesong.util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private FragAdapter mFragAdapter;
    private ViewPager mViewPager;
    private RadioButton mRecBtn, mLocBtn;
    private RadioGroup mRadioGroup;
    private ImageView mImageView;

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
        mViewPager.setAdapter(mFragAdapter);
        PermissionUtil.requestAllPower(this);
        initPagerEvent();
        initClick();
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
    protected void loadData() {
    }
}
