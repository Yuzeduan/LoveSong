package com.yuzeduan.lovesong.main.db;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.playConditionDao;
import com.yuzeduan.lovesong.music.bean.playCondition;
import com.yuzeduan.lovesong.util.LoveSongApplication;

/**
 * 进行数据库中播放列表中播放状态的数据增删查改
 * author: Allen
 * date: On 2018/10/2
 */
public class PlayConditiondao {
    private DaoSession mDaoSession;
    private playConditionDao mPlayConditionDao;

    public PlayConditiondao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mPlayConditionDao = mDaoSession.getPlayConditionDao();
    }

    /**
     * 添加数据进数据库,数据库中只存储一条数据
     */
    public void insetData(playCondition condition){
        if(!(queryData() != null && condition.getMPlayMode() == queryData().getMPlayMode()
                && condition.getMPosition() == queryData().getMPosition())) {
            mPlayConditionDao.deleteAll();
            mPlayConditionDao.insert(condition);
        }
    }

    /**
     * 获取数据库中的数据
     * @return
     */
    public playCondition queryData(){
        return mPlayConditionDao.queryBuilder().unique();
    }
}
