package com.yuzeduan.lovesong.local.adapter;

import android.content.Context;
import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.base.ViewHolder;
import com.yuzeduan.lovesong.local.bean.MusicInfo;
import com.yuzeduan.lovesong.widget.IndexBar;

import java.util.Arrays;
import java.util.List;

public class LocAdapter extends CommonAdapter<MusicInfo> implements SectionIndexer {
    private List<MusicInfo> mList;

    public LocAdapter(Context mContext, List<MusicInfo> mDatas, int mLayoutId) {
        super(mContext, mDatas, mLayoutId);
        mList = mDatas;
    }

    @Override
    public Object[] getSections() {
        return Arrays.copyOf(IndexBar.sStrs, IndexBar.sStrs.length);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex == 26){
            return mList.size();
        }
        for(int i = 0; i < mList.size(); i++){
            if (((String)getSections()[sectionIndex]).charAt(0) == mList.get(i).getmFirstAlphabet().charAt(0)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }


    /**
     * 抽象出来的填充控件的方法
     *
     * @param viewHolder
     * @param item
     */
    @Override
    public void convert(ViewHolder viewHolder, MusicInfo item, int position) {
        TextView headText = viewHolder.getView(R.id.head_tv);
        viewHolder.setText(R.id.head_tv, item.getmFirstAlphabet())
                  .setText(R.id.title_tv, item.getmTitle())
                  .setText(R.id.artist_tv, item.getmArtist())
                  .setText(R.id.album_tv, "-《"+item.getmAlbum()+"》");
        if(0 == position){
            headText.setVisibility(View.VISIBLE);
        }else {
            if (mList.get(position - 1).getmFirstAlphabet().equals(item.getmFirstAlphabet())) {
                headText.setVisibility(View.GONE);
            }else {
                headText.setVisibility(View.VISIBLE);
            }
        }
    }
}
