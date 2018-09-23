package com.yuzeduan.lovesong.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.recommend.view.BannerDetailActivity;
import com.yuzeduan.lovesong.search.MVPContract;
import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.search.presenter.SearchPresenter;
import com.yuzeduan.lovesong.widget.SearchTipsGroupView;

import java.util.List;

public class HotWordFragment extends BaseFragment<MVPContract.IView, SearchPresenter> implements MVPContract.IView, SearchTipsGroupView.OnItemClick{
    private SearchTipsGroupView mSearchTipsGroupView;
    private List<HotWord> mList;

    @Override
    protected void initVariables() {
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_search_hotword, container,false);
        mSearchTipsGroupView = mView.findViewById(R.id.search_tip);
        return mView;
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getHotWordData();
    }

    @Override
    protected void refreshView() {
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void showHotWord(List<HotWord> list) {
        int i = 0;
        mList = list;
        String[] strs = new String[list.size()];
        for(HotWord hotWord: list){
            String str = hotWord.getmWord();
            strs[i++] = str;
        }
        mSearchTipsGroupView.initViews(strs, this);
    }

    @Override
    public void onClick(int position) {
        String path = mList.get(position).getmLinkUrl();
        if(path.startsWith("http")){
            BannerDetailActivity.actionStart(getContext(), path);
        }
    }
}
