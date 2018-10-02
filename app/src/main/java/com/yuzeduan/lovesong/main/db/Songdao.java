package com.yuzeduan.lovesong.main.db;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.SongDao;
import com.yuzeduan.lovesong.music.bean.Song;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * 进行数据库中播放列表中音乐数据的增删查改
 * author: Allen
 * date: On 2018/10/2
 */
public class Songdao {
    private DaoSession mDaoSession;
    private SongDao mSongDao;

    public Songdao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mSongDao = mDaoSession.getSongDao();
    }

    /**
     * 添加播放列表的歌曲进数据库,并且将之前的数据清空
     * @param list
     */
    public void insetSong(List<Song> list){
        mSongDao.deleteAll();
        for(Song item : list){
            mSongDao.insert(item);
        }
    }

    /**
     * 获得数据库中存储的歌曲播放列表中的所有歌曲信息
     * @return
     */
    public List<Song> queryData(){
        return mSongDao.queryBuilder().list();
    }
}
