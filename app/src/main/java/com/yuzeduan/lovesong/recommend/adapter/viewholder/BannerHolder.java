package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.main.view.MainActivity;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.view.BannerDetailActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BannerHolder extends BaseViewHolder<List<FocusPic>> implements OnBannerListener{
    private Banner mBanner;
    private List<FocusPic> mList;
    private Context mContext;

    public BannerHolder(View itemView, Context mContext) {
        super(itemView);
        this.mContext = mContext;
    }

    /**
     * 绑定itemView 的数据
     *
     * @param data
     */
    @Override
    public void bindViewData(List<FocusPic> data) {
        mList = data;
        List<String> mImgList = new ArrayList<>();
        Iterator<FocusPic> iterator = data.iterator();
        mBanner = getView(R.id.rec_bnr);
        while(iterator.hasNext()){
            mImgList.add(iterator.next().getmRandpic());
        }
        mBanner.setImageLoader(new GlideImageLoader()).
                setImages(mImgList).
                setOnBannerListener(this).
                start();
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Intent intent = new Intent(mContext, BannerDetailActivity.class);
        mContext.startActivity(intent);
    }
}
