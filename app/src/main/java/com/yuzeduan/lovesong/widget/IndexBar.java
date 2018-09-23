package com.yuzeduan.lovesong.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.util.DensityUtil;

/**
 * 歌曲列表的字母导航栏
 */
public class IndexBar extends View{
    private Paint mPaint;
    public static String[] sStrs = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private int mChooseIndex;
    private int mDefaultColor;
    private int mSelectColor;
    private OnChooseListener mListener;
    private TextView mTextView;


    public IndexBar(Context context) {
        super(context, null);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(DensityUtil.dpToPx(14));
        mDefaultColor = getResources().getColor(R.color.black);
        mSelectColor = getResources().getColor(R.color.title_bar);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAlphabet(canvas);
    }

    private void drawAlphabet(Canvas canvas) {
        //获取View的宽高
        int width = getWidth();
        int height = getHeight();
        //获取每个字母的高度
        int singleHeight = height / sStrs.length;
        //画字母
        for (int i = 0; i < sStrs.length; i++) {
            if (mChooseIndex == i) {
                mPaint.setColor(mSelectColor);
            }else{
                mPaint.setColor(mDefaultColor);
            }
            //计算每个字母的坐标
            float x = (width - mPaint.measureText(sStrs[i])) / 2;
            float y = (i + 1) * singleHeight;
            canvas.drawText(sStrs[i], x, y, mPaint);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //计算选中字母
        int index = (int) (event.getY() / getHeight() * sStrs.length);
        //防止脚标越界
        if (index >= sStrs.length) {
            index = sStrs.length - 1;
        } else if (index < 0) {
            index = 0;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //选中字母高亮
                mChooseIndex = index;
                //出现中间文字
                mTextView.setVisibility(VISIBLE);
                mTextView.setText(sStrs[mChooseIndex]);
                //调用RecycleView连动接口
                if (mListener != null) {
                    mListener.touchCharacterListener(mChooseIndex);
                }
                //重绘
                invalidate();
                break;
            default:
                setBackgroundColor(Color.TRANSPARENT);
                //取消选中字母高亮
                mChooseIndex = -1;
                //隐藏中间文字
                mTextView.setVisibility(GONE);
                //重绘
                invalidate();
                break;
        }
        return true;
    }

    public interface OnChooseListener{
        void touchCharacterListener(int position);
    }

    public void setmTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public void setmListener(OnChooseListener mListener) {
        this.mListener = mListener;
    }
}
