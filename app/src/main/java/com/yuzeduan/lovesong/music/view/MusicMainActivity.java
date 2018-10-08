package com.yuzeduan.lovesong.music.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.base.MVPActivity;
import com.yuzeduan.lovesong.music.MVPContract;
import com.yuzeduan.lovesong.music.MusicManager;
import com.yuzeduan.lovesong.music.bean.LrcEntity;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;
import com.yuzeduan.lovesong.music.presenter.LrcPresenter;
import com.yuzeduan.lovesong.util.LocalMusicUtil;
import com.yuzeduan.lovesong.util.WindowUtil;
import com.yuzeduan.lovesong.widget.LrcView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

import static com.yuzeduan.lovesong.music.view.BottomPlayFragment.LIST_PLAY;
import static com.yuzeduan.lovesong.music.view.BottomPlayFragment.ONE_CICAL;
import static com.yuzeduan.lovesong.music.view.BottomPlayFragment.RANDOM_PLAY;

public class MusicMainActivity extends MVPActivity<MVPContract.IView, LrcPresenter> implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, MVPContract.IView{
    private SeekBar mSeekBar;
    private ImageButton mNextIbn, mListIbn, mPreviousIbn, mBackIbn;
    private ImageView mPlayModeIv;
    private TextView mNameTv, mArtistTv, mCurrentTimeTv, mAllTimeTv;
    private CheckBox mCbPlay, mFragmentCbPlay;
    private BottomPlayFragment mBottomPlayFragment;
    private Song mSong;
    private int mPlayMode;
    private SongCompleteReceiver mSongCompleteReceiver;
    private RelativeLayout mFooterLayout;
    private MusicManager mMusicManager;
    private Timer mTimer;
    private boolean mControlled;  //记录是由该活动点击切换上下首歌
    private LrcView mLrcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_main);
        EventBus.getDefault().register(this);
        init();
        initEvent();
        initBroadcastReceiver();
        initTimer();
        initSeekBar();
        initTitle();
        initPlayModeView(mPlayMode);
    }

    private void init() {
        mSeekBar = findViewById(R.id.song_seekbar);
        mNextIbn = findViewById(R.id.next_ibn);
        mListIbn = findViewById(R.id.list_ibn);
        mPreviousIbn = findViewById(R.id.previous_ibn);
        mBackIbn = findViewById(R.id.back_ibn);
        mNameTv = findViewById(R.id.name_tv);
        mArtistTv = findViewById(R.id.artist_tv);
        mCurrentTimeTv = findViewById(R.id.time_tv);
        mAllTimeTv = findViewById(R.id.all_time_tv);
        mPlayModeIv = findViewById(R.id.playmode_iv);
        mCbPlay = findViewById(R.id.play_ibn);
        mFooterLayout = findViewById(R.id.song_bottom_layout);
        mLrcView = findViewById(R.id.lrcview);
        mLrcView.setmListener(new LrcView.LrcViewSeekListener() {
            @Override
            public void onLrcSeek(int position, LrcEntity entity) {
                if(mMusicManager.isPlaying()){
                    long time = entity.getmTimeLong();
                    mSeekBar.setProgress((int)time);
                    mMusicManager.setCurrDuration((int)time);
                }
            }
        });
        mSong = mBottomPlayFragment.getmSong();
        mPlayMode = mBottomPlayFragment.getmPlayMode();
        mFragmentCbPlay = mBottomPlayFragment.getmCbPlay();
        mCbPlay.setChecked(mFragmentCbPlay.isChecked());
        mMusicManager = mBottomPlayFragment.getmMusicManager();
    }


    @Override
    protected MusicConditionEvent getMusicCondition() {
        return null;
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    public void showLrc(List<LrcEntity> list) {
        mLrcView.setmLrcList(list);
    }

    public void getData(Song song){
        String mLrcLinkPath;
        if(song != null && (mLrcLinkPath = song.getmLrcLink()) != null){
            mPresenter.getData(mLrcLinkPath);
        }else{
            mLrcView.setmLrcList(null);
        }
    }

    @Override
    protected LrcPresenter createPresenter() {
        return new LrcPresenter();
    }

    private void initEvent() {
        mNextIbn.setOnClickListener(this);
        mPreviousIbn.setOnClickListener(this);
        mListIbn.setOnClickListener(this);
        mBackIbn.setOnClickListener(this);
        mPlayModeIv.setOnClickListener(this);
        mCbPlay.setOnCheckedChangeListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    private void initPlayModeView(int i) {
        switch (i){
            case LIST_PLAY:
                mPlayModeIv.setImageResource(R.drawable.ic_list_play);
                break;
            case RANDOM_PLAY:
                mPlayModeIv.setImageResource(R.drawable.ic_random);
                break;
            case ONE_CICAL:
                mPlayModeIv.setImageResource(R.drawable.ic_cicle);
                break;
        }
    }

    private void initSeekBar() {
        if(!mCbPlay.isChecked()){
            mSeekBar.setEnabled(false);
        }
        mSeekBar.getThumb().setColorFilter(getResources().getColor(R.color.title_bar), PorterDuff.Mode.SRC_ATOP);
        mSeekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.title_bar), PorterDuff.Mode.SRC_ATOP);
    }

    private void initTitle() {
        mNameTv.setText(mSong.getmSongName());
        mArtistTv.setText(mSong.getmArtist());
    }

    private void initBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.yuzeduan.lovesong.nextmusic");
        mSongCompleteReceiver = new SongCompleteReceiver();
        registerReceiver(mSongCompleteReceiver, intentFilter);
    }

    private void initTimer() {
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(mMusicManager.isPlaying()){
                    final int progress = mMusicManager.getCurrentTime();
                    final int total = mMusicManager.getDuration();
                    mSeekBar.setMax(total);
                    mSeekBar.setProgress(progress);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mCurrentTimeTv.setText(LocalMusicUtil.formatTime(progress));
                            mAllTimeTv.setText(LocalMusicUtil.formatTime(total));
                            mLrcView.seekLrcToTime(progress); //滚动歌词
                        }
                    });
                }
            }
        };
        mTimer.schedule(task, 100,1000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next_ibn:
                mBottomPlayFragment.setFirst(false);
                mSong = mBottomPlayFragment.checkNextSong();
                mControlled = true;
                mCbPlay.setChecked(true);
                initTitle();
                getData(mSong);
                break;
            case R.id.previous_ibn:
                mBottomPlayFragment.setFirst(false);
                mSong = mBottomPlayFragment.checkPreviousSong();
                mControlled = true;
                mCbPlay.setChecked(true);
                initTitle();
                getData(mSong);
                break;
            case R.id.list_ibn:
                initPopupWindow();
                break;
            case R.id.playmode_iv:
                mPlayMode = (mPlayMode+1)%3;
                initPlayModeView(mPlayMode);
                mBottomPlayFragment.setmPlayMode(mPlayMode);
                break;
            case R.id.back_ibn:
                finish();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int position = mSeekBar.getProgress();
        if(mCbPlay.isChecked()) {
            mMusicManager.setCurrDuration(position);
        }
    }

    private void initPopupWindow() {
        SongPopupWindow mSongPopupWindow = new SongPopupWindow(this, mBottomPlayFragment, mBottomPlayFragment.getmSongList());
        mSongPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mSongPopupWindow.showAtLocation(mFooterLayout, Gravity.BOTTOM,0,0 );
        WindowUtil.lightOffWindow(this);
        mSongPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowUtil.lightOnWindow(MusicMainActivity.this);
            }
        });
        mSongPopupWindow.setmListener(new OnClickWindowItemListener() {
            @Override
            public void onClick(Song song) {
                mSong = song;
                initTitle();
                mControlled = true;
                mCbPlay.setChecked(true);
                getData(mSong);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            if(!mControlled){
                mFragmentCbPlay.setChecked(true);
            }
            mSeekBar.setEnabled(true);
        }else{
            mFragmentCbPlay.setChecked(false);
            mControlled = false;
            mSeekBar.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mSongCompleteReceiver);
    }

    @Subscribe(sticky = true)
    public void getBottomEvent(BottomPlayFragment event){
        mBottomPlayFragment = event;
        getData(event.getmSong());
    }

    class SongCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mSong = mBottomPlayFragment.checkNextSong();
            if(mSong != null){
                initTitle();
                getData(mSong);
            }
        }
    }

    /**
     * 点击了popupWindow的子项时候,更新Activity的回调接口
     */
    public interface OnClickWindowItemListener{
        void onClick(Song song);
    }
}
