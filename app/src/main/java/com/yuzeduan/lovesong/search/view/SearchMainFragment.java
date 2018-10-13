package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.search.adapter.SearchFragAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/23
 */
public class SearchMainFragment extends Fragment{
    private View mView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragments;
    private SearchFragAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_search_main, container, false);
        }
        mViewPager = mView.findViewById(R.id.search_vp);
        mTabLayout = mView.findViewById(R.id.search_tablayout);
        initValiables();
        initView();
        return mView;
    }

    private void initValiables() {
        mFragments = new ArrayList<>();
        mFragments.add(new SongListFragment());
        mFragments.add(new ArtistListFragment());
        mFragments.add(new AlbumListFragment());
        mFragments.add(new SearchNullFragment());
        mFragments.add(new SearchNullFragment());
        mFragments.add(new SearchNullFragment());
        mFragments.add(new SearchNullFragment());
    }

    private void initView() {
        mAdapter = new SearchFragAdapter(getActivity().getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
