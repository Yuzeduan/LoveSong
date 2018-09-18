package com.yuzeduan.lovesong.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class LazyFragment extends Fragment{
    private View mView;
    protected boolean isVisible; //用于判断是否可见
    protected boolean isPrepared;
    protected boolean isFirst = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == mView){
            mView = initView(inflater, container, savedInstanceState);
        }
        isPrepared = true;
        initVariables();
        lazyLoad();
        refreshView();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("setUserVisible", "setUserVisibleHint: 调用了");
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
        }
    }

    protected void onVisible(){
        lazyLoad();
        isFirst = false;
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    protected abstract void initVariables();
    protected abstract void lazyLoad();
    protected abstract void refreshView();

}
