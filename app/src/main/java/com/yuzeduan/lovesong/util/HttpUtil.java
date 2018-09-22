package com.yuzeduan.lovesong.util;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 进行网络请求的类
 */
public class HttpUtil {
    public static void sendHttpRequest(String address, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/60.0.3112.113 Safari/537.36")
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
