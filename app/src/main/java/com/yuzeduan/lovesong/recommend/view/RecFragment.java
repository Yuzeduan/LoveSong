package com.yuzeduan.lovesong.recommend.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.BaseFragment;
import com.yuzeduan.lovesong.recommend.MVPContract;
import com.yuzeduan.lovesong.recommend.adapter.MultiAdapter;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.presenter.RecPresenter;

import java.util.List;

public class RecFragment extends BaseFragment<MVPContract.IView, RecPresenter> implements MVPContract.IView{
    private View mView;
    private RecyclerView mRecyclerView;

    @Override
    protected void initVariables() {
        if (mView == null) {
            return;
        }
        mRecyclerView = mView.findViewById(R.id.rec_rcv);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recommend, container, false);
        return mView;
    }

    @Override
    protected void lazyLoad() {
        if(isPrepared && isVisible){
            mPresenter.getBannerData();
        }
    }

    @Override
    protected RecPresenter createPresenter() {
        return new RecPresenter();
    }

    @Override
    public void showBanner(List<FocusPic> list) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), 6, GridLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new MultiAdapter(list));
    }

}
