package com.yuzeduan.lovesong.music.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.CommonAdapter;
import com.yuzeduan.lovesong.music.adapter.PupListAdapter;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.util.StringConcatUtil;
import com.yuzeduan.lovesong.util.WindowUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.yuzeduan.lovesong.music.view.BottomPlayFragment.LIST_PLAY;
import static com.yuzeduan.lovesong.music.view.BottomPlayFragment.ONE_CICAL;
import static com.yuzeduan.lovesong.music.view.BottomPlayFragment.RANDOM_PLAY;

/**
 * author: Allen
 * date: On 2018/9/30
 */
public class SongPopupWindow extends PopupWindow implements CommonAdapter.OnItemClickListener, View.OnClickListener{
    private Context mContext;
    private BottomPlayFragment mBottomPlayFragment;
    private View mCovertView;
    private List<Song> mSongList;
    private RecyclerView mSongRv;
    private Button mPupBn;
    private ImageButton mPupIbn;
    private PupListAdapter mAdapter;
    private int mPosition;
    private int mPlayMode;
    private int mListSize;
    private PopHandler mPopHandler;

    SongPopupWindow(Context mContext, BottomPlayFragment mBottomPlayFragment, List<Song> mSongList) {
        this.mContext = mContext;
        this.mBottomPlayFragment = mBottomPlayFragment;
        this.mSongList = mSongList;
        this.mPosition = mBottomPlayFragment.getmPosition();
        this.mListSize = mBottomPlayFragment.getmSongList().size();
        this.mPlayMode = mBottomPlayFragment.getmPlayMode();
        this.mPopHandler = new PopHandler(this);
        this.mCovertView = LayoutInflater.from(mContext).inflate(R.layout.popup_song, null);
        int mWidth = WindowUtil.getWindowHeightAndWidth(mContext).widthPixels;
        int mHeight = (int)(WindowUtil.getWindowHeightAndWidth(mContext).heightPixels * 0.6);
        setContentView(mCovertView);
        setWidth(mWidth);
        setHeight(mHeight);
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE){
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        initView();
        initRecycleView();
        initPupBnView();
    }

    private void initPupBnView() {
        String text = null;
        switch (mPlayMode){
            case LIST_PLAY:
                text = StringConcatUtil.concatString("列表循环 ","( ",mListSize+" )");
                break;
            case RANDOM_PLAY:
                text = StringConcatUtil.concatString("随机播放 ","( ",mListSize+" )");
                break;
            case ONE_CICAL:
                text = StringConcatUtil.concatString("单曲循环 ","( ",mListSize+" )");
                break;
        }
        mPupBn.setText(text);
    }

    private void initView() {
        mSongRv = mCovertView.findViewById(R.id.popup_rv);
        mPupBn = mCovertView.findViewById(R.id.popup_bn);
        mPupIbn = mCovertView.findViewById(R.id.popup_deleteall_ibn);
        mPupIbn.setOnClickListener(this);
        mPupBn.setOnClickListener(this);
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new PupListAdapter(mContext, mSongList, R.layout.item_popup);
        mAdapter.setmOnItemClickListener(this);
        mSongRv.setLayoutManager(linearLayoutManager);
        mSongRv.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.popup_deleteall_ibn:
                Song song = mSongList.get(mPosition);
                mSongList.clear();
                mSongList.add(song);
                mBottomPlayFragment.setmSongList(mSongList);
                mAdapter.setmDatas(mSongList);
                mListSize = mSongList.size();
                mPosition = 0;
                mBottomPlayFragment.setmPosition(mPosition);
                mPopHandler.sendEmptyMessage(0);
                break;
            case R.id.popup_bn:
                mPlayMode = (mPlayMode+1)%3;
                mBottomPlayFragment.setmPlayMode(mPlayMode);
                mPopHandler.sendEmptyMessage(0);
                break;
        }
    }

    @Override
    public void OnItemClick(int position) {
        if(position != mPosition){
            Song song = mSongList.get(position);
            mBottomPlayFragment.showNextSong(song);
            mBottomPlayFragment.setmPosition(position);
        }
    }

    @Override
    public void OnItemViewClick(int position) {
        if(position != mPosition){
            mSongList.remove(position);
            mBottomPlayFragment.setmSongList(mSongList);
            mAdapter.setmDatas(mSongList);
            mListSize--;
            if(position < mPosition){
                mPosition--;
                mBottomPlayFragment.setmPosition(mPosition);
            }
            mPopHandler.sendEmptyMessage(0);
        }
    }

    /**
     *
     */
    public static class PopHandler extends Handler{
        private WeakReference<SongPopupWindow> reference;

        PopHandler(SongPopupWindow window){
            this.reference = new WeakReference<>(window);
        }

        @Override
        public void handleMessage(Message msg) {
            reference.get().initPupBnView();
        }
    }
}
