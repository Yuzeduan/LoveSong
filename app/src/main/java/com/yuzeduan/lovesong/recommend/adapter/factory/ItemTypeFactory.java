package com.yuzeduan.lovesong.recommend.adapter.factory;

import android.content.Context;
import android.view.View;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BannerHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BaseViewHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.HotSongListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternNameHolder;

public class ItemTypeFactory implements TypeFactory{
    public static final int BANNER_TYPE_LAYOUT = R.layout.item_banner_rec;
    public static final int PATTERN_NAME_TYPE_LAYOUT = R.layout.item_pattern_name_rec;
    private static final int PATTERN_TYPE_LAYOUT = R.layout.item_pattern_rec;

    /**
     * 根据在RecycleView中的位置返回Type的值
     *
     * @param position
     * @return
     */
    @Override
    public int Type(int position) {
        switch (position){
            case 0:
                return BANNER_TYPE_LAYOUT;
            case 1:
                return PATTERN_NAME_TYPE_LAYOUT;
            default:
                return PATTERN_TYPE_LAYOUT;
        }
    }

    /**
     * 根据Type的值,返回对应的ViewHolder
     *
     * @param type
     * @return
     */
    @Override
    public BaseViewHolder createViewHolder(int type, View itemView, Context context) {
        switch (type){
            case BANNER_TYPE_LAYOUT:
                return new BannerHolder(itemView);
            case PATTERN_NAME_TYPE_LAYOUT:
                return new PatternNameHolder(itemView);
            case PATTERN_TYPE_LAYOUT:
                return new HotSongListHolder(itemView, context);
            default:
                return null;
        }
    }
}
