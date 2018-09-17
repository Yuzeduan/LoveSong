package com.yuzeduan.lovesong.recommend.adapter.factory;

import android.content.Context;
import android.view.View;

import com.yuzeduan.lovesong.recommend.adapter.viewholder.BaseViewHolder;

/**
 * 用于判断Type并返回相应的ViewHolder
 */
public interface TypeFactory {

    /**
     * 根据在RecycleView中的位置返回Type的值
     * @param position
     * @return
     */
    int Type(int position);

    /**
     * 根据Type的值,返回对应的ViewHolder
     * @param type
     * @return
     */
    BaseViewHolder createViewHolder(int type, View itemView, Context context);

}
