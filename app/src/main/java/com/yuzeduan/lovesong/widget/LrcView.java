package com.yuzeduan.lovesong.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.music.bean.LrcEntity;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/10/7
 */
public class LrcView extends View {
    private static final int DISPLAY_MODE_NORMAL = 0;
    private static final int DISPLAY_MODE_SEEK = 1;
    private static final int SHOW_ONE_LINE = 0;
    private static final int SHOW_ALL_LINE = 1;
    private int mDisplayMode;
    private int mLrcShowMode;
    private List<LrcEntity> mLrcList;
    private int mMinSeekFiredOffect; //当移动距离超过此值,才做处理
    private int mHighLightRow;
    private int mHighLightRowColor;
    private int mNormalRowColor;
    private int mSeekLineColor;
    private int mLrcFontSize;
    private int mPaddingY; //两行歌词的距离
    private float mLastMotionY; //手指的坐标
    private boolean mIsFirstMove;
    private LrcViewSeekListener mListener;
    private String mLoadingLrcTip;
    private Paint mPaint;

    public LrcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDisplayMode = DISPLAY_MODE_NORMAL;
        mLrcShowMode = SHOW_ONE_LINE;
        mMinSeekFiredOffect = 10;
        mHighLightRow = 0;
        mHighLightRowColor = Color.BLACK;
        mNormalRowColor = getResources().getColor(R.color.lightgrey);
        mSeekLineColor = getResources().getColor(R.color.title_bar);
        mLrcFontSize = 40;
        mPaddingY = 30;
        mLoadingLrcTip = "无歌词信息";
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(mLrcFontSize);
    }

    public void setmListener(LrcViewSeekListener mListener) {
        this.mListener = mListener;
    }

    public void setmLrcList(List<LrcEntity> mLrcList) {
        this.mLrcList = mLrcList;
        invalidate();
    }

    public void setmLrcShowMode(int mLrcShowMode) {
        this.mLrcShowMode = mLrcShowMode;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int height = getHeight();
        final int width = getWidth();
        //当没有歌词的时候
        if (mLrcList== null || mLrcList.size() == 0) {
            if (mLoadingLrcTip != null) {
                // draw tip when no lrc.
                mPaint.setColor(mHighLightRowColor);
                mPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(mLoadingLrcTip, width / 2, height / 2 - mLrcFontSize, mPaint);
            }
            return;
        }
        int rowY = 0;
        final int rowX = width / 2;
        int rowNum = 0;
        //画出高亮的歌词
        String highlightText = mLrcList.get(mHighLightRow).getmText();
        int highlightRowY = height / 2 - mLrcFontSize;
        mPaint.setColor(mHighLightRowColor);
        canvas.drawText(highlightText, rowX, highlightRowY, mPaint);
        //上下拖动歌词的时候 画出拖动要高亮的那句歌词的时间 和 高亮的那句歌词下面的一条直线
        if (mDisplayMode == DISPLAY_MODE_SEEK) {
            //画出高亮的那句歌词下面的一条直线
            mPaint.setColor(mSeekLineColor);
            //该直线的x坐标从0到屏幕宽度  y坐标为高亮歌词和下一行歌词中间
            canvas.drawLine(0, highlightRowY + mPaddingY, width, highlightRowY + mPaddingY, mPaint);

            //画出高亮的那句歌词的时间
            mPaint.setColor(mSeekLineColor);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(mLrcList.get(mHighLightRow).getmTime(), 0, highlightRowY, mPaint);
        }
        rowNum = mHighLightRow - 1;
        mPaint.setColor(mNormalRowColor);
        mPaint.setTextAlign(Paint.Align.CENTER);
        rowY = highlightRowY - mPaddingY - mLrcFontSize;
        if(mLrcShowMode == SHOW_ONE_LINE){
            if(rowNum > 0){
                String text = mLrcList.get(rowNum).getmText();
                canvas.drawText(text, rowX, rowY, mPaint);
            }
        }else{
            //画出正在播放的那句歌词的上面所有歌词
            while( rowY > -mLrcFontSize && rowNum >= 0){
                String text = mLrcList.get(rowNum).getmText();
                canvas.drawText(text, rowX, rowY, mPaint);
                rowY -=  (mPaddingY + mLrcFontSize);
                rowNum --;
            }
        }
        rowNum = mHighLightRow + 1;
        rowY = highlightRowY + mPaddingY + mLrcFontSize;
        if(mLrcShowMode == SHOW_ONE_LINE){
            if(rowNum < mLrcList.size()){
                String text = mLrcList.get(rowNum).getmText();
                canvas.drawText(text, rowX, rowY, mPaint);
            }
        }else{
            while( rowY < height && rowNum < mLrcList.size()){
                String text = mLrcList.get(rowNum).getmText();
                canvas.drawText(text, rowX, rowY, mPaint);
                rowY += (mPaddingY + mLrcFontSize);
                rowNum ++;
            }
        }
    }

    /**
     * 设置要高亮的歌词
     * @param position
     * @param cb
     */
    public void seekLrc(int position, boolean cb){
        if (mLrcList == null || position < 0 || position > mLrcList.size()) {
            return;
        }
        LrcEntity entity = mLrcList.get(position);
        mHighLightRow = position;
        invalidate();
        //如果是手指拖动歌词后
        if (mListener != null && cb) {
            //回调onLrcSeek方法，将音乐播放器播放的位置移动到高亮歌词的位置
            mListener.onLrcSeek(position, entity);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mLrcList == null || mLrcList.size() == 0){
            return super.onTouchEvent(event);
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = event.getY();
                mIsFirstMove = true;
                mLrcShowMode = (mLrcShowMode+1)%2;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                doSeek(event);
                break;
            case MotionEvent.ACTION_UP:
                if (mDisplayMode == DISPLAY_MODE_SEEK) {
                    //高亮手指抬起时的歌词并播放从该句歌词开始播放
                    seekLrc(mHighLightRow, true);
                    mLrcShowMode = (mLrcShowMode+1)%2;
                    invalidate();
                }
                mDisplayMode = DISPLAY_MODE_NORMAL;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 处理单指在屏幕移动时，歌词上下滚动
     */
    private void doSeek(MotionEvent event) {
        float y = event.getY();//手指当前位置的y坐标
        float offsetY = y - mLastMotionY; //第一次按下的y坐标和目前移动手指位置的y坐标之差
        //如果移动距离小于10，不做任何处理
        if (Math.abs(offsetY) < mMinSeekFiredOffect) {
            return;
        }
        //将模式设置为拖动歌词模式
        mDisplayMode = DISPLAY_MODE_SEEK;
        int rowOffset = Math.abs((int) offsetY / mLrcFontSize); //歌词要滚动的行数
        if (offsetY < 0) {
            //手指向上移动，歌词向下滚动
            mHighLightRow += rowOffset;//设置要高亮的歌词为 当前高亮歌词 向下滚动rowOffset行后的歌词
        } else if (offsetY > 0) {
            //手指向下移动，歌词向上滚动
            mHighLightRow -= rowOffset;//设置要高亮的歌词为 当前高亮歌词 向上滚动rowOffset行后的歌词
        }
        //设置要高亮的歌词为0和mHighRow中的较大值，即如果mHighlightRow < 0，mHighlightRow=0
        mHighLightRow = Math.max(0, mHighLightRow);
        //设置要高亮的歌词为0和mHighlightRow中的较小值，即如果mHighlight > RowLrcRows.size()-1，mHighlightRow=mLrcRows.size()-1
        mHighLightRow = Math.min(mHighLightRow, mLrcList.size() - 1);
        //如果歌词要滚动的行数大于0，则重画LrcView
        if (rowOffset > 0) {
            mLastMotionY = y;
            invalidate();
        }
    }

    /**
     * 播放的时候调用该方法滚动歌词，高亮正在播放的那句歌词
     * @param time
     */
    public void seekLrcToTime(long time) {
        if (mLrcList == null || mLrcList.size() == 0) {
            return;
        }
        if (mDisplayMode != DISPLAY_MODE_NORMAL) {
            return;
        }
        for (int i = 0; i < mLrcList.size(); i++) {
            LrcEntity entity = mLrcList.get(i);
            LrcEntity next = i + 1 == mLrcList.size() ? null : mLrcList.get(i + 1);
            /**
             *  正在播放的时间大于current行的歌词的时间而小于next行歌词的时间， 设置要高亮的行为current行
             *  正在播放的时间大于current行的歌词，而current行为最后一句歌词时，设置要高亮的行为current行
             */
            if ((time >= entity.getmTimeLong() && next != null && time < next.getmTimeLong())
                    || (time > entity.getmTimeLong() && next == null)){
                seekLrc(i, false);
                return;
            }
        }
    }

    public interface LrcViewSeekListener{
        void onLrcSeek(int position, LrcEntity entity);
    }
}
