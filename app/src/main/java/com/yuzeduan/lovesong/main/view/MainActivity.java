package com.yuzeduan.lovesong.main.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.main.adapter.FragAdapter;
import com.yuzeduan.lovesong.recommend.view.RecFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new RecFragment());
        mFragments.add(new RecFragment());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), mFragments);
        final ViewPager mViewpager = findViewById(R.id.main_viewpager);
        final RadioButton mRadioButtonA = findViewById(R.id.top_rg_a);
        final RadioButton mRadioButtonB = findViewById(R.id.top_rg_b);
        RadioGroup mRadioGroup = findViewById(R.id.main_top_rg);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) mRadioButtonA.setChecked(true);
                else mRadioButtonB.setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRadioButtonA.getId()) mViewpager.setCurrentItem(0);
                else if (checkedId == mRadioButtonB.getId()) mViewpager.setCurrentItem(1);
            }
        });
        //设置默认选中页
        mViewpager.setCurrentItem(0);
    }
}
