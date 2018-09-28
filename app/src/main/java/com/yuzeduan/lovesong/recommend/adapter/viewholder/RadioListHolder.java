package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.recommend.bean.RadioList;

public class RadioListHolder extends BaseViewHolder<RadioList>{
    private Context mContext;

    public RadioListHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
    }

    /**
     * 绑定itemView 的数据
     *
     * @param data
     */
    @Override
    public void bindViewData(RadioList data) {
        ImageView mImageView = getView(R.id.rec_imv);
        TextView mTvInfo = getView(R.id.rec_img_info_tv);
        Glide.with(mContext).load(data.getMPicPath()).into(mImageView);
        mTvInfo.setText(data.getMTitle());
    }
}
