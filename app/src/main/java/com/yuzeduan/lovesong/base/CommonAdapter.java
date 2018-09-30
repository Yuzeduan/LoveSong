package com.yuzeduan.lovesong.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 构建公共的适配器
 * @param <T> 表示子项的类型
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder>{
    protected Context mContext;
    private List<T> mDatas;
    private int mLayoutId;
    protected OnItemClickListener mOnItemClickListener;
    private onLoadMoreListener mOnLoadMoreListener;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.OnItemClick(holder.getLayoutPosition());
                    }
                }
            });
        convert(holder, mDatas.get(position), position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.getViewHolder(parent, mLayoutId);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int totalItemCount = manager.getItemCount();
                    int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                    if (totalItemCount <= (lastVisibleItemPosition + 2)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                    }
                }
            });
        }
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnLoadMoreListener(onLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public CommonAdapter(Context mContext, List<T> mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    /**
     * 抽象出来的填充控件的方法
     * @param viewHolder
     * @param item
     */
    public abstract void convert(ViewHolder viewHolder, T item, int position);

    public interface OnItemClickListener {
        void OnItemClick(int position);
        void OnItemViewClick(int position);
    }

    /**
     * 用于底部加载更多时候改变数据源
     * @param list
     */
    public void setDataChange(List<T> list){
        if(list != null){
            mDatas.addAll(list);
        }
        Log.d("onLoadmore", "setDataChange: "+"加载更多");
        notifyDataSetChanged();
    }

    /**
     * 暴露的底部加载更多的接口
     */
    public interface onLoadMoreListener{
        void onLoadMore();
    }
}
