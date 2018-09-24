package com.yuzeduan.lovesong.recommend.adapter.factory;

import android.content.Context;
import android.view.View;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.AlbumListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BannerHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BaseViewHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.HotSongListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.IconHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternNameHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.RadioListHolder;
import com.yuzeduan.lovesong.recommend.bean.RadioList;

public class ItemTypeFactory implements TypeFactory{
    public static final int BANNER_TYPE_LAYOUT = 10001;
    public static final int PATTERN_NAME_TYPE_LAYOUT = 10002;
    public static final int HOTSONGLIST_LAYOUT = 10003;
    public static final int ALBUMLIST_LAYOUT = 10004;
    public static final int RADIOLIST_LAYOUT = 10005;
    public static final int ICON_LAYOUT = 10006;

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
            case 7:
            case 14:
            case 21:
                return PATTERN_NAME_TYPE_LAYOUT;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return ICON_LAYOUT;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return HOTSONGLIST_LAYOUT;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                return ALBUMLIST_LAYOUT;
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                return RADIOLIST_LAYOUT;
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
                return new BannerHolder(itemView, context);
            case PATTERN_NAME_TYPE_LAYOUT:
                return new PatternNameHolder(itemView);
            case HOTSONGLIST_LAYOUT:
                return new HotSongListHolder(itemView, context);
            case ALBUMLIST_LAYOUT:
                return new AlbumListHolder(itemView, context);
            case RADIOLIST_LAYOUT:
                return new RadioListHolder(itemView, context);
            case ICON_LAYOUT:
                return new IconHolder(itemView);
            default:
                return null;
        }
    }
}
