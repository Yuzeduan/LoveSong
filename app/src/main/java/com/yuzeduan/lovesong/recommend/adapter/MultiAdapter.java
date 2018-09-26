package com.yuzeduan.lovesong.recommend.adapter;

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
import com.yuzeduan.lovesong.recommend.adapter.viewholder.IconHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternNameHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.RadioListHolder;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.recommend.bean.RadioList;

import java.util.List;

import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.ALBUMLIST_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.BANNER_TYPE_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.HOTSONGLIST_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.ICON_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.PATTERN_NAME_TYPE_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.RADIOLIST_LAYOUT;

public class MultiAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private TypeFactory mFactory;
    private List<FocusPic> mPicList;
    private List<HotSongList> mHotSongList;
    private List<AlbumList> mAlbumList;
    private List<RadioList> mRadioList;
    private OnItemClickListener mListener;

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
            case ICON_LAYOUT:
                type = R.layout.item_icon;
                break;
            case HOTSONGLIST_LAYOUT:
            case ALBUMLIST_LAYOUT:
            case RADIOLIST_LAYOUT:
                type = R.layout.item_pattern_rec;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(type, parent,false);
        return mFactory.createViewHolder(viewType, view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, final int position) {
        if(holder instanceof BannerHolder){
            ((BannerHolder) holder).bindViewData(mPicList);
        }else if(holder instanceof PatternNameHolder){
            ((PatternNameHolder) holder).bindViewData(position);
        }else if(holder instanceof IconHolder){
            ((IconHolder) holder).bindViewData(position);
        }else if(holder instanceof HotSongListHolder){
            if(mHotSongList != null && !mHotSongList.isEmpty()){
                final HotSongList data = mHotSongList.get(position - 8);
                ((HotSongListHolder) holder).bindViewData(data);
                ((HotSongListHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mListener != null){
                            mListener.onHotSongClick(data);
                        }
                    }
                });
            }
        }else if(holder instanceof AlbumListHolder){
            if(mAlbumList != null && !mAlbumList.isEmpty()){
                final AlbumList data = mAlbumList.get(position - 15);
                ((AlbumListHolder) holder).bindViewData(data);
                ((AlbumListHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mListener != null){
                            mListener.onAlbumClick(data);
                        }
                    }
                });
            }
        }else if(holder instanceof RadioListHolder){
            if(mRadioList != null && !mRadioList.isEmpty()){
                final RadioList data = mRadioList.get(position - 22);
                ((RadioListHolder) holder).bindViewData(data);
                ((RadioListHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mListener != null){
                            mListener.onRadioClick(data);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return 28;
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
                        case ICON_LAYOUT:
                            return gridLayoutManager.getSpanCount() / 6;
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

    public void setmRadioList(List<RadioList> mRadioList) {
        this.mRadioList = mRadioList;
        notifyDataSetChanged();
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener{
        void onAlbumClick(AlbumList data);
        void onHotSongClick(HotSongList data);
        void onRadioClick(RadioList data);
    }
}
