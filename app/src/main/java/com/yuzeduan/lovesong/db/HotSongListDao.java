package com.yuzeduan.lovesong.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yuzeduan.lovesong.recommend.bean.HotSongList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOT_SONG_LIST".
*/
public class HotSongListDao extends AbstractDao<HotSongList, Long> {

    public static final String TABLENAME = "HOT_SONG_LIST";

    /**
     * Properties of entity HotSongList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MListId = new Property(1, String.class, "mListId", false, "mListId");
        public final static Property MPicPath = new Property(2, String.class, "mPicPath", false, "mPicPath");
        public final static Property MTitle = new Property(3, String.class, "mTitle", false, "mTitle");
        public final static Property MTag = new Property(4, String.class, "mTag", false, "mTag");
    }


    public HotSongListDao(DaoConfig config) {
        super(config);
    }
    
    public HotSongListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOT_SONG_LIST\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"mListId\" TEXT," + // 1: mListId
                "\"mPicPath\" TEXT," + // 2: mPicPath
                "\"mTitle\" TEXT," + // 3: mTitle
                "\"mTag\" TEXT);"); // 4: mTag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOT_SONG_LIST\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HotSongList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mListId = entity.getMListId();
        if (mListId != null) {
            stmt.bindString(2, mListId);
        }
 
        String mPicPath = entity.getMPicPath();
        if (mPicPath != null) {
            stmt.bindString(3, mPicPath);
        }
 
        String mTitle = entity.getMTitle();
        if (mTitle != null) {
            stmt.bindString(4, mTitle);
        }
 
        String mTag = entity.getMTag();
        if (mTag != null) {
            stmt.bindString(5, mTag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HotSongList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mListId = entity.getMListId();
        if (mListId != null) {
            stmt.bindString(2, mListId);
        }
 
        String mPicPath = entity.getMPicPath();
        if (mPicPath != null) {
            stmt.bindString(3, mPicPath);
        }
 
        String mTitle = entity.getMTitle();
        if (mTitle != null) {
            stmt.bindString(4, mTitle);
        }
 
        String mTag = entity.getMTag();
        if (mTag != null) {
            stmt.bindString(5, mTag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HotSongList readEntity(Cursor cursor, int offset) {
        HotSongList entity = new HotSongList( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mListId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mPicPath
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mTitle
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // mTag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HotSongList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMListId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMPicPath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMTag(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HotSongList entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HotSongList entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HotSongList entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
