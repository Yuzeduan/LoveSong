package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;

/**
 * author: Allen
 * date: On 2018/9/24
 */
public class IconHolder extends BaseViewHolder<Integer>{

    public IconHolder(View itemView) {
        super(itemView);
    }

    /**
     * 绑定itemView 的数据
     *
     * @param data
     */
    @Override
    public void bindViewData(Integer data) {
        ImageView imageView = getView(R.id.icon_iv);
        TextView textView = getView(R.id.icon_tv);
        switch (data){
            case 1:
                imageView.setImageResource(R.drawable.ic_rec);
                textView.setText("今日推荐");
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_artist);
                textView.setText("火爆歌手");
                break;
            case 3:
                imageView.setImageResource(R.drawable.ic_hot);
                textView.setText("最热歌单");
                break;
            case 4:
                imageView.setImageResource(R.drawable.ic_bangdan);
                textView.setText("最新榜单");
                break;
            case 5:
                imageView.setImageResource(R.drawable.ic_gift);
                textView.setText("会员福利");
                break;
            case 6:
                imageView.setImageResource(R.drawable.ic_share);
                textView.setText("乐在分享");
        }
    }
}
