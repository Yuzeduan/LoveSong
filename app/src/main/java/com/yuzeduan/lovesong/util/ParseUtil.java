package com.yuzeduan.lovesong.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuzeduan.lovesong.recommend.bean.FocusPic;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class ParseUtil {
    private static Gson sGson = new Gson();

    public static List<FocusPic> parseFocusPic(String jsonData){
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            String data = jsonObject.getString("pic");
            List<FocusPic> list = sGson.fromJson(data, new TypeToken<List<FocusPic>>(){}.getType());
            return list;
        }   catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
