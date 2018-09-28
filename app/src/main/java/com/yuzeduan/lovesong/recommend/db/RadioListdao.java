package com.yuzeduan.lovesong.recommend.db;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.RadioListDao;
import com.yuzeduan.lovesong.recommend.bean.RadioList;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * 对数据库电台的数据进行增删查改
 * author: Allen
 * date: On 2018/9/27
 */
public class RadioListdao {
    private DaoSession mDaoSession;
    private RadioListDao mRadioListDao;

    public RadioListdao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mRadioListDao = mDaoSession.getRadioListDao();
    }

    public void insetData(List<RadioList> list){
        for(RadioList item : list){
            if(!queryDataByTitle(item.getMTitle())){
                mRadioListDao.insert(item);
            }
        }
    }

    private boolean queryDataByTitle(String title){
        return mRadioListDao.queryBuilder().where(RadioListDao.Properties.MTitle.eq(title)).unique() != null;
    }

    public List<RadioList> queryData(){
        return mRadioListDao.queryBuilder().orderDesc(RadioListDao.Properties.Id).limit(6).list();
    }
}
