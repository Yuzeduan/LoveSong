package com.yuzeduan.lovesong.songlist.db;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.SongInfoJsonBeanDao;
import com.yuzeduan.lovesong.songlist.bean.SongInfoJsonBean;
import com.yuzeduan.lovesong.util.LoveSongApplication;

/**
 * 对数据库中的歌曲列表的json数据进行增删查改
 * author: Allen
 * date: On 2018/9/28
 */
public class SongInfoJsonBeandao {
    private DaoSession mDaoSession;
    private SongInfoJsonBeanDao mSongInfoJsonBeanDao;

    public SongInfoJsonBeandao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mSongInfoJsonBeanDao = mDaoSession.getSongInfoJsonBeanDao();
    }

    public void insetData(SongInfoJsonBean bean){
        if(queryDataBySongId(bean.getMSongId()) == null){
            mSongInfoJsonBeanDao.insert(bean);
        }
    }

    public SongInfoJsonBean queryDataBySongId(String songId){
        return mSongInfoJsonBeanDao.queryBuilder().where(SongInfoJsonBeanDao.Properties.MSongId.eq(songId)).unique();
    }
}
