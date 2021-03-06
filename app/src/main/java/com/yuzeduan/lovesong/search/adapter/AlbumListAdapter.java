package com.yuzeduan.lovesong.search.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.base.ViewHolder;
import com.yuzeduan.lovesong.search.bean.SearchAlbumList;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/24
 */
public class AlbumListAdapter extends CommonAdapter<SearchAlbumList>{

    public AlbumListAdapter(Context mContext, List<SearchAlbumList> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    /**
     * 抽象出来的填充控件的方法
     * @param viewHolder
     * @param item
     * @param position
     */
    @Override
    public void convert(ViewHolder viewHolder, SearchAlbumList item, int position) {
        viewHolder.setText(R.id.album_tv, item.getmTitle())
                  .setText(R.id.artist_tv, item.getmAuthor());
        ImageView imageView = viewHolder.getView(R.id.pic_iv);
        Glide.with(mContext).load(item.getmPicPath()).into(imageView);
    }
}
