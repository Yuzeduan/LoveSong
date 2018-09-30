package com.yuzeduan.lovesong.music.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuzeduan.lovesong.R;
import com.yuzeduan.lovesong.music.MusicManager;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;
import com.yuzeduan.lovesong.util.LocalMusicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/28
 */
public class BottomPlayFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private View mView;
    private RelativeLayout mFooterLayout;
    private ImageView mPicIv;
    private TextView mSongNameTv, mSongAuthorTv;
    private ImageButton mNextIbn, mListIbn;
    private CheckBox mCbPlay;
    private List<Song> mSongList;
    private int mPosition;
    private MusicManager mMusicManager;

    /**
     * 根据传进的音乐播放栏的状态构建出新的播放栏
     * @param condition
     * @return
     */
    public static BottomPlayFragment getInstance(MusicConditionEvent condition){
        BottomPlayFragment fragment = new BottomPlayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("condition", condition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.footer, container, false);
        initView();
        initEvent();
        initData();
        return mView;
    }

    private void initView() {
        mFooterLayout = mView.findViewById(R.id.footer_layout);
        mPicIv = mView.findViewById(R.id.song_picture_iv);
        mSongNameTv = mView.findViewById(R.id.song_name_tv);
        mSongAuthorTv = mView.findViewById(R.id.song_author_tv);
        mCbPlay = mView.findViewById(R.id.play_ibn);
        mNextIbn = mView.findViewById(R.id.next_ibn);
        mListIbn = mView.findViewById(R.id.list_ibn);
        mPosition = 0;
        mSongList = new ArrayList<>();
        mMusicManager = MusicManager.getInstance();
    }

    /**
     *通过活动间的事件传递设置状态栏的数据
     */
    private void initData(){
        Bundle bundle = getArguments();
        if(bundle != null){
            MusicConditionEvent conditionEvent =(MusicConditionEvent) bundle.getSerializable("condition");
            setConditionEvent(conditionEvent);
        }
    }

    /**
     * 设置状态栏的数据
     * @param conditionEvent
     */
    public void setConditionEvent(MusicConditionEvent conditionEvent){
        if(conditionEvent != null){
            List<Song> songList= conditionEvent.getmList();
            mPosition = conditionEvent.getmPosition();
            mSongList.clear();
            mSongList.addAll(songList);
            if(mSongList.size() != 0){
                Song song = mSongList.get(mPosition);
                showView(song);
            }
            mCbPlay.setChecked(conditionEvent.isPlay());
        }
    }

    /**
     * 当点击歌曲时候,添加歌曲到播放栏中并进行播放
     * @param song
     */
    public void insetSongToPlay(Song song){
        if(mSongList.size() == 0){
            mSongList.add(song);
            mPosition = 0;
        }else{
            mPosition++;
            mSongList.add(mPosition, song);
        }
        mMusicManager.setSong(song.getmSongAddress());
        showView(song);
    }

    /**
     * 添加歌曲到播放列表不进行播放
     */
    public void insetSong(Song song){
        if(mSongList != null){
            mSongList.add(song);
        }
    }

    /**
     * 当点击本地音乐的时候,刷新播放栏的歌曲,全部变为本地音乐,并从点开的歌曲进行播放
     */
    public void setSongList(List<Song> list, int position){
        Song song = list.get(position);
        //添加并播放歌曲
        mMusicManager.setSong(song.getmSongAddress());
        mSongList.clear();
        mSongList.addAll(list);
        mPosition = position;
        showView(song);
    }

    private void showView(Song song){
        mSongNameTv.setText(song.getmSongName());
        mSongAuthorTv.setText(song.getmArtist());
        if(mCbPlay.isChecked()){
            mMusicManager.onPlayMusic();
        }else{
            mCbPlay.setChecked(true);
        }
        if(song.isLocal()){
            Bitmap bitmap = LocalMusicUtil.getLocalityMusicBitmap(song.getmSongId(), song.getmSmallPicPath(), 150);
            mPicIv.setImageBitmap(bitmap);
        }else{
            Glide.with(getContext()).load(song.getmSmallPicPath()).into(mPicIv);
        }
    }

    /**
     * 返回当前底部播放栏的状态
     * @return 底部播放栏状态
     */
    public MusicConditionEvent getPlayerCondition(){
        List<Song> songList = new ArrayList<>();
        if(mSongList != null){
            songList.addAll(mSongList);
        }
        return new MusicConditionEvent(songList, mPosition, mCbPlay.isChecked());
    }

    private void initEvent(){
        mFooterLayout.setOnClickListener(this);
        mNextIbn.setOnClickListener(this);
        mListIbn.setOnClickListener(this);
        mCbPlay.setOnCheckedChangeListener(this);
    }

    /**
     * 点击下一首的时候,状态栏的变化
     * @param song
     */
    public void showNextSong(Song song){
        mMusicManager.setSong(song.getmSongAddress());
        showView(song);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.footer_layout:
                break;
            case R.id.next_ibn:
                if(mSongList.size() > 1){
                    mPosition++;
                    showNextSong(mSongList.get(mPosition));
                }
                break;
            case R.id.list_ibn:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            mMusicManager.onPlayMusic();
        }else {
            mMusicManager.onPauseMusic();
        }
    }
}
