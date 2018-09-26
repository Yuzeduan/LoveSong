package com.yuzeduan.lovesong.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Activity和Presenter生命周期关联
 * author: Allen
 * date: On 2018/9/25
 */
public abstract class MVPActivity<V, P extends BasePresenter<V>> extends BaseActivity{
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract P createPresenter();
}
