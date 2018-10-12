package com.yuzeduan.lovesong.search.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class SearchFragAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public SearchFragAdapter(FragmentManager fm, List<Fragment>  fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return mFragments.get(0);
            case 1:
                return mFragments.get(1);
            case 2:
                return mFragments.get(2);
            default:
                return mFragments.get(3);
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "歌曲";
            case 1:
                return "歌手";
            case 2:
                return "专辑";
            case 3:
                return "视频";
            case 4:
                return "歌单";
            case 5:
                return "用户";
            case 6:
                return "话题";
            default:
                return null;
        }
    }

    public List<Fragment> getmFragments() {
        return mFragments;
    }
}
