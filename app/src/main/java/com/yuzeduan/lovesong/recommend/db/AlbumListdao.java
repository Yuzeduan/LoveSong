package com.yuzeduan.lovesong.recommend.db;

import com.yuzeduan.lovesong.db.AlbumListDao;
import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.recommend.bean.AlbumList;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * 对数据库中的专辑数据进行增删查改
 * author: Allen
 * date: On 2018/9/27
 */
public class AlbumListdao {
    private DaoSession mDaoSession;
    private AlbumListDao mAlbumDao;

    public AlbumListdao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mAlbumDao = mDaoSession.getAlbumListDao();
    }

    /**
     * 判断数据库是否存在该数据,若无,则进行添加数据
     * @param list
     */
    public void insetData(List<AlbumList> list){
        for(AlbumList item : list){
            if(!queryDataByAlbumId(item.getMAlbumId())){
                mAlbumDao.insert(item);
            }
        }
    }

    /**
     * 通过专辑id查询数据库是否存在该数据
     * @param AlbumId
     * @return 若存在返回true,不存在返回false
     */
    private boolean queryDataByAlbumId(String AlbumId){
        return mAlbumDao.queryBuilder().where(AlbumListDao.Properties.MAlbumId.eq(AlbumId)).unique() != null;
    }

    /**
     * 查询获得最近添加进数据库的前六条数据
     * @return
     */
    public List<AlbumList> queryData(){
        return mAlbumDao.queryBuilder().orderDesc(AlbumListDao.Properties.Id).limit(6).list();
    }
}
