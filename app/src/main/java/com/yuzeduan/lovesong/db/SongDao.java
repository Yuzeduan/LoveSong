package com.yuzeduan.lovesong.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yuzeduan.lovesong.music.bean.Song;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SONG".
*/
public class SongDao extends AbstractDao<Song, Void> {

    public static final String TABLENAME = "SONG";

    /**
     * Properties of entity Song.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MSongId = new Property(0, String.class, "mSongId", false, "mSongId");
        public final static Property MSongAddress = new Property(1, String.class, "mSongAddress", false, "mSongAddress");
        public final static Property MSongName = new Property(2, String.class, "mSongName", false, "mSongName");
        public final static Property MArtist = new Property(3, String.class, "mArtist", false, "mArtist");
        public final static Property MHugePicPath = new Property(4, String.class, "mHugePicPath", false, "mHugePicPath");
        public final static Property MSmallPicPath = new Property(5, String.class, "mSmallPicPath", false, "mSmallPicPath");
        public final static Property MLrcLink = new Property(6, String.class, "mLrcLink", false, "mLrcLink");
        public final static Property IsLocal = new Property(7, boolean.class, "isLocal", false, "isLocal");
    }


    public SongDao(DaoConfig config) {
        super(config);
    }
    
    public SongDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SONG\" (" + //
                "\"mSongId\" TEXT," + // 0: mSongId
                "\"mSongAddress\" TEXT," + // 1: mSongAddress
                "\"mSongName\" TEXT," + // 2: mSongName
                "\"mArtist\" TEXT," + // 3: mArtist
                "\"mHugePicPath\" TEXT," + // 4: mHugePicPath
                "\"mSmallPicPath\" TEXT," + // 5: mSmallPicPath
                "\"mLrcLink\" TEXT," + // 6: mLrcLink
                "\"isLocal\" INTEGER NOT NULL );"); // 7: isLocal
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SONG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Song entity) {
        stmt.clearBindings();
 
        String mSongId = entity.getMSongId();
        if (mSongId != null) {
            stmt.bindString(1, mSongId);
        }
 
        String mSongAddress = entity.getMSongAddress();
        if (mSongAddress != null) {
            stmt.bindString(2, mSongAddress);
        }
 
        String mSongName = entity.getMSongName();
        if (mSongName != null) {
            stmt.bindString(3, mSongName);
        }
 
        String mArtist = entity.getMArtist();
        if (mArtist != null) {
            stmt.bindString(4, mArtist);
        }
 
        String mHugePicPath = entity.getMHugePicPath();
        if (mHugePicPath != null) {
            stmt.bindString(5, mHugePicPath);
        }
 
        String mSmallPicPath = entity.getMSmallPicPath();
        if (mSmallPicPath != null) {
            stmt.bindString(6, mSmallPicPath);
        }
 
        String mLrcLink = entity.getMLrcLink();
        if (mLrcLink != null) {
            stmt.bindString(7, mLrcLink);
        }
        stmt.bindLong(8, entity.getIsLocal() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Song entity) {
        stmt.clearBindings();
 
        String mSongId = entity.getMSongId();
        if (mSongId != null) {
            stmt.bindString(1, mSongId);
        }
 
        String mSongAddress = entity.getMSongAddress();
        if (mSongAddress != null) {
            stmt.bindString(2, mSongAddress);
        }
 
        String mSongName = entity.getMSongName();
        if (mSongName != null) {
            stmt.bindString(3, mSongName);
        }
 
        String mArtist = entity.getMArtist();
        if (mArtist != null) {
            stmt.bindString(4, mArtist);
        }
 
        String mHugePicPath = entity.getMHugePicPath();
        if (mHugePicPath != null) {
            stmt.bindString(5, mHugePicPath);
        }
 
        String mSmallPicPath = entity.getMSmallPicPath();
        if (mSmallPicPath != null) {
            stmt.bindString(6, mSmallPicPath);
        }
 
        String mLrcLink = entity.getMLrcLink();
        if (mLrcLink != null) {
            stmt.bindString(7, mLrcLink);
        }
        stmt.bindLong(8, entity.getIsLocal() ? 1L: 0L);
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public Song readEntity(Cursor cursor, int offset) {
        Song entity = new Song( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // mSongId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mSongAddress
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mSongName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // mArtist
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // mHugePicPath
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // mSmallPicPath
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // mLrcLink
            cursor.getShort(offset + 7) != 0 // isLocal
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Song entity, int offset) {
        entity.setMSongId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setMSongAddress(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMSongName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMArtist(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMHugePicPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMSmallPicPath(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMLrcLink(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIsLocal(cursor.getShort(offset + 7) != 0);
     }
    
    @Override
    protected final Void updateKeyAfterInsert(Song entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(Song entity) {
        return null;
    }

    @Override
    public boolean hasKey(Song entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}