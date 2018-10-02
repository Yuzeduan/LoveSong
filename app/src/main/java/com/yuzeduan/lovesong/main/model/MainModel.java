package com.yuzeduan.lovesong.main.model;

import com.yuzeduan.lovesong.main.db.PlayConditiondao;
import com.yuzeduan.lovesong.main.db.Songdao;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.music.bean.playCondition;
import com.yuzeduan.lovesong.music.event.MusicConditionEvent;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/10/2
 */
public class MainModel {
    private Songdao mSongDao;
    private PlayConditiondao mPlayConditionDao;

    public MainModel() {
        mSongDao = new Songdao();
        mPlayConditionDao = new PlayConditiondao();
    }

    /**
     * 活动销毁的时候,保存播放栏的数据进数据库
     * @param event
     */
    public void saveData(MusicConditionEvent event){
        List<Song> list = event.getmList();
        int playMode = event.getmPlayMode();
        int position = event.getmPosition();
        playCondition condition = new playCondition(position, playMode);
        mSongDao.insetSong(list);
        mPlayConditionDao.insetData(condition);
    }

    /**
     * 获取数据库中存取的播放栏数据
     */
    public MusicConditionEvent getSavedEvent(){
        playCondition condition;
        List<Song> list;
        MusicConditionEvent event = null;
        if((condition = mPlayConditionDao.queryData())!= null && !(list = mSongDao.queryData()).isEmpty()){
            int playMode = condition.getMPlayMode();
            int position = condition.getMPosition();
            event = new MusicConditionEvent(list, position, playMode, false);
        }
        return event;
    }
}
