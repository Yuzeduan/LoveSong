package com.yuzeduan.lovesong.util;

import com.yuzeduan.lovesong.music.bean.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ParseJsonUtil {
    /**
     * 通过格式对json数据进行截取
     * @param json 需要截取的原始json数据
     * @param route 截取的格式（a.b.c.d字母是要截取的标签）
     * @return  截取后的json数据
     */
    public static String obtainDesignationJson(String json, String route) {
        // 获取到每一级json字符串的key
        String[] keys = route.split("\\.");
        String newJson = json;
        for (int i = 0; i < keys.length; i++) {
            newJson = splitJsonByKey(newJson, keys[i]);
            if (i == keys.length - 1) {
                return newJson;
            }
        }
        return null;
    }

    /**
     * 通过一个特定标签对json数据进行截取
     * @param json  需要截取的json数据
     * @param key   要截取的标签
     * @return  截取后的json数据
     */
    private static String splitJsonByKey(String json,String key) {
        try {
            JSONObject obj = new JSONObject(json);
            for (Iterator<String> it = obj.keys(); it.hasNext(); ) {
                String s = it.next();
                if (s == null) {
                    return null;
                }
                if (s.equals(key)) {
                    return obj.getString(key);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析歌曲JSON数据
     * @param jsonData
     * @return
     */
    public static Song parseSongUtil(String jsonData){
        try {
            Song song = new Song();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject songUrl = jsonObject.getJSONObject("songurl");
            JSONArray url = songUrl.getJSONArray("url");
            JSONObject obj = url.getJSONObject(0);
            song.setmSongAddress(obj.getString("show_link"));
            JSONObject songInfo = jsonObject.getJSONObject("songinfo");
            song.setmSongName(songInfo.getString("title"));
            song.setmArtist(songInfo.getString("artist"));
            song.setmHugePicPath(songInfo.getString("pic_huge"));
            song.setmSmallPicPath(songInfo.getString("pic_radio"));
            song.setmLrcLink(songInfo.getString("lrclink"));
            song.setLocal(false);
            song.setmSongId(songInfo.getString("song_id"));
            return song;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
