package com.yuzeduan.lovesong.util;


public class StringConcatUtil {
    private static StringBuilder mBuilder = new StringBuilder();

    /**
     * 用于拼接字符串
     * @param strings
     * @return
     */
    public static String concatString(String... strings){
        mBuilder.delete(0, mBuilder.length());
        for(String string : strings){
            mBuilder.append(string);
        }
        return mBuilder.toString();
    }
}
