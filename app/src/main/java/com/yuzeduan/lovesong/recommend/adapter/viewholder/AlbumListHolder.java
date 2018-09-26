package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;

public class AlbumListHolder extends BaseViewHolder<AlbumList>{
    private Context mContext;

    public AlbumListHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
    }

    /**
     * 绑定itemView 的数据
     * @param data
     */
    @Override
    public void bindViewData(AlbumList data) {
        ImageView mImageView = getView(R.id.rec_imv);
        TextView mTvInfo = getView(R.id.rec_img_info_tv);
        TextView mTvName = getView(R.id.rec_img_name_tv);
        Glide.with(mContext).load(data.getmPicPath()).into(mImageView);
        mTvInfo.setText(data.getmTitle());
        mTvName.setText(data.getmAuthor());
    }
}
