package com.yuzeduan.lovesong.search.db;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.HotWordDao;
import com.yuzeduan.lovesong.search.bean.HotWord;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * author: Allen
 * date: On 2018/9/28
 */
@Deprecated
public class HotWorddao {
    private DaoSession mDaoSession;
    private HotWordDao mHotWordDao;

    public HotWorddao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mHotWordDao = mDaoSession.getHotWordDao();
    }

    public void insetData(List<HotWord> list){
        for(HotWord word : list){
            if(!queryDataByWord(word.getMWord())){
                mHotWordDao.insert(word);
            }
        }
    }

    private boolean queryDataByWord(String word){
        return mHotWordDao.queryBuilder().where(HotWordDao.Properties.MWord.eq((word))).unique() != null;
    }

    public List<HotWord> queryData(){
        return mHotWordDao.queryBuilder().orderDesc(HotWordDao.Properties.Id).limit(8).list();
    }
}
