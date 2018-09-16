package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private View mConvertView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    // 通过相应的id获取控件
    public <E extends View> E getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (E) view;
    }

    /**
     *绑定itemView 的数据
     */
    public abstract void bindViewData(T data);

}
