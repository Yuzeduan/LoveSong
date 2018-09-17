package com.yuzeduan.lovesong.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;
import com.yuzeduan.lovesong.recommend.bean.HotSongList;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class ParseUtil {
    private static Gson sGson = new Gson();

    /**
     * 用于解析banner轮播图中所需的数据
     * @param jsonData
     * @return
     */
    public static List<FocusPic> parseFocusPic(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String data = jsonObject.getString("pic");
            return sGson.fromJson(data, new TypeToken<List<FocusPic>>(){}.getType());
        }   catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用于解析热门音乐榜单的数据
     * @param jsonData
     * @return
     */
    public static List<HotSongList> parseHotSongList(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject content = jsonObject.getJSONObject("content");
            String data = content.getString("list");
            return sGson.fromJson(data, new TypeToken<List<HotSongList>>(){}.getType());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
