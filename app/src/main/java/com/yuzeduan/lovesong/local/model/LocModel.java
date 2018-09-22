package com.yuzeduan.lovesong.local.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import com.yuzeduan.lovesong.local.bean.MusicInfo;
import com.yuzeduan.lovesong.util.AlphabetUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocModel {
    public List<MusicInfo> getMusicList(ContentResolver contentResolver){
        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<MusicInfo> list = new ArrayList<>();

        if(cursor != null){
            cursor.moveToFirst();
            do {
                //音乐id
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                //歌名
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                //唱片
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                //唱片图片id
                long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                //歌手
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                //歌曲文件的路径
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                //判断是否为音乐
                int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
                //音乐播放长度
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                //音乐文件大小
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                //对音乐进行筛选（是音乐，播放长度大于一分钟，文件大小大于800k）
                if (isMusic != 0 && duration/(1000 * 60) >= 1 && size >1024*800){
                    MusicInfo musicInfo = new MusicInfo();
                    String firstAlphabet = AlphabetUtil.getFirstAlphabet(title);
                    musicInfo.setmId(id);
                    musicInfo.setmTitle(title);
                    musicInfo.setmArtist(artist);
                    musicInfo.setmAlbum(album);
                    musicInfo.setmAlbumId(albumId);
                    musicInfo.setmDuration(duration);
                    musicInfo.setmUrl(url);
                    musicInfo.setmSize(size);
                    musicInfo.setmFirstAlphabet(firstAlphabet);
                    list.add(musicInfo);
                }
            }while (cursor.moveToNext());
            cursor.close();
        }
        //将容器中的对象按照拼音进行排序
        Collections.sort(list, new Comparator<MusicInfo>() {
            @Override
            public int compare(MusicInfo lhs, MusicInfo rhs) {
                return lhs.getmFirstAlphabet().compareTo(rhs.getmFirstAlphabet());
            }
        });
        return list;
    }
}
