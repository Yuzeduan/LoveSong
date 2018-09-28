package com.yuzeduan.lovesong.recommend.db;

import com.yuzeduan.lovesong.db.DaoSession;
import com.yuzeduan.lovesong.db.FocusPicDao;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.util.LoveSongApplication;

import java.util.List;

/**
 * 对数据库轮播图的数据进行增删查改
 * author: Allen
 * date: On 2018/9/27
 */
public class FocusPicdao {
    private DaoSession mDaoSession;
    private FocusPicDao mFocusPicDao;

    public FocusPicdao() {
        mDaoSession = ((LoveSongApplication) LoveSongApplication.getContext()).getmDaoSession();
        mFocusPicDao = mDaoSession.getFocusPicDao();
    }

    public void insetData(List<FocusPic> list){
        for(FocusPic item : list ){
            if(!queryDataByCode(item.getMCode())){
                mFocusPicDao.insert(item);
            }
        }
    }

    /**
     * 根据数据中的code进行判断是否存在数据库中
     * @param code
     * @return 若存在,则返回true,否则返回false
     */
    private boolean queryDataByCode(String code){
        return mFocusPicDao.queryBuilder().where(FocusPicDao.Properties.MCode.eq(code)).unique() != null;
    }

    /**
     * 获得最近添加进数据库的五条数据
     * @return
     */
    public List<FocusPic> queryData(){
        return mFocusPicDao.queryBuilder().orderDesc(FocusPicDao.Properties.Id).limit(5).list();
    }
}
