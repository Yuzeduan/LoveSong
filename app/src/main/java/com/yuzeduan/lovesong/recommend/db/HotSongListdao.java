package com.yuzeduan.lovesong.recommend.db;

import android.util.Log;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.HotSongListDao;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * 对数据库中的热门歌曲数据进行增删查改
 * author: Allen
 * date: On 2018/9/27
 */
public class HotSongListdao {
    private DaoSession mDaoSession;
    private HotSongListDao mHotSongListDao;

    public HotSongListdao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mHotSongListDao = mDaoSession.getHotSongListDao();
    }

    public void insetData(List<HotSongList> list){
        for(HotSongList item : list){
            if(!queryDataByListId(item.getMListId())){
                mHotSongListDao.insert(item);
                Log.d("HotSongListDao", "insetData: "+item.getMListId());
            }
        }
    }

    private boolean queryDataByListId(String listId){
        return mHotSongListDao.queryBuilder().where(HotSongListDao.Properties.MListId.eq(listId)).unique() != null;
    }

    public List<HotSongList> queryData(){
        return mHotSongListDao.queryBuilder().orderDesc(HotSongListDao.Properties.Id).limit(6).list();
    }
}
