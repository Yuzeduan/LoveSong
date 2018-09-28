package com.yuzeduan.lovesong.songlist.db;

import com.yuzeduan.lovesong.db.AlbumInfoDao;
import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.songlist.bean.AlbumInfo;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * 对数据库专辑列表的信息进行增删查改
 * author: Allen
 * date: On 2018/9/27
 */
public class AlbumInfodao {
    private DaoSession mDaoSession;
    private AlbumInfoDao mAlbumInfoDao;

    public AlbumInfodao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mAlbumInfoDao = mDaoSession.getAlbumInfoDao();
    }

    public void insetData(List<AlbumInfo> list){
        for(AlbumInfo info : list){
            if(!queryDataBySongId(info.getMSongId())){
                mAlbumInfoDao.insert(info);
            }
        }
    }

    private boolean queryDataBySongId(String songId){
        return mAlbumInfoDao.queryBuilder().where(AlbumInfoDao.Properties.MSongId.eq(songId)).unique() != null;
    }

    public List<AlbumInfo> queryDataByAlbumId(String albumId){
        return mAlbumInfoDao.queryBuilder().where(AlbumInfoDao.Properties.MAlbumId.eq(albumId)).list();
    }
}
