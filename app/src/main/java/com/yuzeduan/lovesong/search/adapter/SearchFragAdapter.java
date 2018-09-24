package com.yuzeduan.lovesong.search.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.yuzeduan.lovesong.search.view.AlbumListFragment;
import com.yuzeduan.lovesong.search.view.ArtistListFragment;
import com.yuzeduan.lovesong.search.view.SearchNullFragment;
import com.yuzeduan.lovesong.search.view.SongListFragment;

public class SearchFragAdapter extends FragmentPagerAdapter {

    public SearchFragAdapter(FragmentManager fm) {
        super(fm);
        Log.d("SearchFragAdapter", "SearchFragAdapter: "+"创建adapter");
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SongListFragment();
            case 1:
                return new ArtistListFragment();
            case 2:
                return new AlbumListFragment();
            default:
                return new SearchNullFragment();
        }
    }

    @Override
    public int getCount() {
        return 7;
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
}
