package com.yuzeduan.lovesong.songlist.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.base.ViewHolder;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.util.ViewExpandUtil;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/25
 */
public class AlbumListAdapter extends CommonAdapter<AlbumInfo> {
    public AlbumListAdapter(Context mContext, List<AlbumInfo> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
    }

    /**
     * 抽象出来的填充控件的方法
     *
     * @param viewHolder
     * @param item
     * @param position
     */
    @Override
    public void convert(ViewHolder viewHolder, AlbumInfo item, final int position) {
        viewHolder.setText(R.id.title_tv, item.getMTitle())
                  .setText(R.id.author_tv, item.getMAuthor());
        ImageButton imageButton = viewHolder.getView(R.id.add_ibn);
        ViewExpandUtil.expandViewTouchDelegate(imageButton, 40, 40, 40, 40);
        viewHolder.itemView.findViewById(R.id.add_ibn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.OnItemViewClick(position);
                }
            }
        });
    }
}
