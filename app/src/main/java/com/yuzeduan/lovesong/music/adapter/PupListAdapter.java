package com.yuzeduan.lovesong.music.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.base.ViewHolder;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.util.ViewExpandUtil;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/30
 */
public class PupListAdapter extends CommonAdapter<Song> {

    public PupListAdapter(Context mContext, List<Song> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    /**
     * 抽象出来的填充控件的方法
     * @param viewHolder
     * @param item
     * @param position
     */
    @Override
    public void convert(ViewHolder viewHolder, Song item, final int position) {
        viewHolder.setText(R.id.name_tv, item.getmSongName())
                  .setText(R.id.artist_tv, item.getmArtist());
        ImageButton imageButton = viewHolder.getView(R.id.delete_ibn);
        ViewExpandUtil.expandViewTouchDelegate(imageButton, 40, 40, 40, 40);
        viewHolder.itemView.findViewById(R.id.delete_ibn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.OnItemViewClick(position);
            }
        });
    }
}
