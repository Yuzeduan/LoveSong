package com.yuzeduan.lovesong.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yuzeduan.lovesong.search.bean.HotWord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HOT_WORD".
*/
public class HotWordDao extends AbstractDao<HotWord, Long> {

    public static final String TABLENAME = "HOT_WORD";

    /**
     * Properties of entity HotWord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MWord = new Property(1, String.class, "mWord", false, "mWord");
        public final static Property MLinkUrl = new Property(2, String.class, "mLinkUrl", false, "mLinkUri");
    }


    public HotWordDao(DaoConfig config) {
        super(config);
    }
    
    public HotWordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HOT_WORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"mWord\" TEXT," + // 1: mWord
                "\"mLinkUri\" TEXT);"); // 2: mLinkUrl
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HOT_WORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HotWord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mWord = entity.getMWord();
        if (mWord != null) {
            stmt.bindString(2, mWord);
        }
 
        String mLinkUrl = entity.getMLinkUrl();
        if (mLinkUrl != null) {
            stmt.bindString(3, mLinkUrl);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HotWord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mWord = entity.getMWord();
        if (mWord != null) {
            stmt.bindString(2, mWord);
        }
 
        String mLinkUrl = entity.getMLinkUrl();
        if (mLinkUrl != null) {
            stmt.bindString(3, mLinkUrl);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HotWord readEntity(Cursor cursor, int offset) {
        HotWord entity = new HotWord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mWord
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // mLinkUrl
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HotWord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMWord(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMLinkUrl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HotWord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HotWord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HotWord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}