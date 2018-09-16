package com.yuzeduan.lovesong.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 进行网络请求的类
 */
public class HttpUtil {
    public static void okHttpAsync(final  String address, final HttpCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(address)
                            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                                    "Chrome/60.0.3112.113 Safari/537.36")
                            .build();
                    Response response = client.newCall(request).execute();
                    if(callback != null ) {
                        callback.onFinish(response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface HttpCallback{
        void onFinish(String str);
    }
}
