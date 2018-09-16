package com.yuzeduan.lovesong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseFragment <V, P extends BasePresenter<V>> extends LazyFragment{
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract P createPresenter();
}
