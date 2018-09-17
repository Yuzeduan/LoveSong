package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;

public class PatternNameHolder extends BaseViewHolder<Integer>{

    public PatternNameHolder(View itemView) {
        super(itemView);
    }

    /**
     * 绑定itemView 的数据
     *
     * @param data
     */
    @Override
    public void bindViewData(Integer data) {
        TextView mTextView = getView(R.id.rec_ptn_name_tv);
        switch (data){
            case 1:
                mTextView.setText("最热音乐");
                break;
            case 8:
                mTextView.setText("最新专辑");
                break;
            case 15:
                mTextView.setText("电台活动");
                break;
        }
    }
}
