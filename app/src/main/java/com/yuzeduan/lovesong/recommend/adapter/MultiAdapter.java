package com.yuzeduan.lovesong.recommend.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory;
import com.yuzeduan.lovesong.recommend.adapter.factory.TypeFactory;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.AlbumListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BannerHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BaseViewHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.HotSongListHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternNameHolder;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;

import java.util.List;

import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.ALBUMLIST_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.BANNER_TYPE_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.HOTSONGLIST_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.PATTERN_NAME_TYPE_LAYOUT;

public class MultiAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private TypeFactory mFactory;
    private List<FocusPic> mPicList;
    private List<HotSongList> mHotSongList;
    private List<AlbumList> mAlbumList;

    public MultiAdapter(List<FocusPic> mPicList) {
        this.mPicList = mPicList;
        mFactory = new ItemTypeFactory();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int type = 0;
        switch (viewType){
            case BANNER_TYPE_LAYOUT:
                type = R.layout.item_banner_rec;
                break;
            case PATTERN_NAME_TYPE_LAYOUT:
                type = R.layout.item_pattern_name_rec;
                break;
            case HOTSONGLIST_LAYOUT:
            case ALBUMLIST_LAYOUT:
                type = R.layout.item_pattern_rec;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(type, parent,false);
        return mFactory.createViewHolder(viewType, view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if(holder instanceof BannerHolder){
            ((BannerHolder) holder).bindViewData(mPicList);
        }else if(holder instanceof PatternNameHolder){
            ((PatternNameHolder) holder).bindViewData(position);
        }else if(holder instanceof HotSongListHolder){
            if(mHotSongList != null && !mHotSongList.isEmpty()){
                HotSongList data = mHotSongList.get(position - 2);
                ((HotSongListHolder) holder).bindViewData(data);
            }
        }else if(holder instanceof AlbumListHolder){
            if(mAlbumList != null && !mAlbumList.isEmpty()){
                AlbumList data = mAlbumList.get(position - 9);
                ((AlbumListHolder) holder).bindViewData(data);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }


    @Override
    public int getItemViewType(int position) {
        return mFactory.Type(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = ((GridLayoutManager) manager);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type){
                        case BANNER_TYPE_LAYOUT:
                        case PATTERN_NAME_TYPE_LAYOUT:
                            //占满一列
                            return gridLayoutManager.getSpanCount();
                        default:
                            //占一列的1/3
                            return gridLayoutManager.getSpanCount() / 3;
                    }
                }
            });
        }
    }

    public void setmHotSongList(List<HotSongList> mHotSongList) {
        this.mHotSongList = mHotSongList;
        notifyDataSetChanged();
    }

    public void setmAlbumList(List<AlbumList> mAlbumList) {
        this.mAlbumList = mAlbumList;
        notifyDataSetChanged();
    }

    public void setmPicList(List<FocusPic> mPicList) {
        this.mPicList = mPicList;
        notifyDataSetChanged();
    }
}
