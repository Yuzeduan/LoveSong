package com.yuzeduan.lovesong.recommend.adapter.viewholder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;

public class PatternHolder extends BaseViewHolder<Integer>{
    private ImageView mImageView;
    private TextView mTvInfo, mTvName;

    public PatternHolder(View itemView) {
        super(itemView);
    }

    /**
     * 绑定itemView 的数据
     *
     * @param data
     */
    @Override
    public void bindViewData(Integer data) {
        String info = null;
        String name = null;
        mImageView = getView(R.id.rec_imv);
        mTvInfo = getView(R.id.rec_img_info_tv);
        mTvName = getView(R.id.rec_img_name_tv);
        mImageView.setImageResource(R.mipmap.ic_launcher);
        switch(data) {
            case 2:
            case 3:
            case 4: info ="111111";
                    name = "第一排";
                    break;
            case 5:
            case 6:
            case 7: info = "222222";
                    name = "第二排";
                    break;
            case 9:
            case 10:
            case 11: info = "333333";
                     name = "第三排";
                    break;
            case 12:
            case 13:
            case 14: info = "444444";
                     name = "第四排";
                     break;
            case 16:
            case 17:
            case 18: info = "555555";
                     name = "第五排";
                     break;
            case 19:
            case 20:
            case 21: info = "666666";
                     name = "第六排";
                     break;
        }
        mTvInfo.setText(info);
        mTvName.setText(name);
    }
}
