package com.yuzeduan.lovesong.recommend.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory;
import com.yuzeduan.lovesong.recommend.adapter.factory.TypeFactory;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BannerHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.BaseViewHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternHolder;
import com.yuzeduan.lovesong.recommend.adapter.viewholder.PatternNameHolder;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;

import java.util.List;

import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.BANNER_TYPE_LAYOUT;
import static com.yuzeduan.lovesong.recommend.adapter.factory.ItemTypeFactory.PATTERN_NAME_TYPE_LAYOUT;

public class MultiAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private TypeFactory mFactory;
    private List<FocusPic> mPicList;

    public MultiAdapter(List<FocusPic> mPicList) {
        this.mPicList = mPicList;
        mFactory = new ItemTypeFactory();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent,false);
        return mFactory.createViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if(holder instanceof BannerHolder){
            ((BannerHolder) holder).bindViewData(mPicList);
        }else if(holder instanceof PatternNameHolder){
            ((PatternNameHolder) holder).bindViewData(position);
        }else if(holder instanceof PatternHolder){
            ((PatternHolder) holder).bindViewData(position);
        }
    }

    @Override
    public int getItemCount() {
        return 22;
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
}
