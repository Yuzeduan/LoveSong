package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.search.adapter.SearchFragAdapter;

/**
 * author: Allen
 * date: On 2018/9/23
 */
public class SearchMainFragment extends Fragment{
    private View mView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_search_main, container, false);
            Log.d("SearchMainFragment", "onCreateView: "+"创建主界面的view");
        }
        mViewPager = mView.findViewById(R.id.search_vp);
        mTabLayout = mView.findViewById(R.id.search_tablayout);
        Log.d("SearchMainFragment", "onCreateView: "+"创建了搜索主界面");
        initView();
        return mView;
    }

    private void initView() {
        mViewPager.setAdapter(new SearchFragAdapter(getActivity().getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SearchMainFragment", "onDestroy: "+"调用了");
    }
}
