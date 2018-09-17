package com.yuzeduan.lovesong.recommend.adapter.factory;

import android.content.Context;
import android.view.View;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.AlbumListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BannerHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BaseViewHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.HotSongListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternNameHolder;

public class ItemTypeFactory implements TypeFactory{
    public static final int BANNER_TYPE_LAYOUT = 10001;
    public static final int PATTERN_NAME_TYPE_LAYOUT = 10002;
    public static final int HOTSONGLIST_LAYOUT = 10003;
    public static final int ALBUMLIST_LAYOUT = 10004;

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
            case 8:
                return PATTERN_NAME_TYPE_LAYOUT;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return HOTSONGLIST_LAYOUT;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return ALBUMLIST_LAYOUT;
            default:
                return 0;
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
            case HOTSONGLIST_LAYOUT:
                return new HotSongListHolder(itemView, context);
            case ALBUMLIST_LAYOUT:
                return new AlbumListHolder(itemView, context);
            default:
                return null;
        }
    }
}
