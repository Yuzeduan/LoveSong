package com.yuzeduan.lovesong.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {
    //View 接口类型的弱引用
    protected Reference<V> mViewRef;

    /**
     * 与View建立关联
     * @param view view层接口
     */
    public void attachView(V view){
        mViewRef = new WeakReference<>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }

    /**
     * 判断是否与View建立了连接
     * @return
     */
    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除和View的关联
     */
    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
