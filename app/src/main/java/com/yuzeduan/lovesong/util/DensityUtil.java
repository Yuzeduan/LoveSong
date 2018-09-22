package com.yuzeduan.lovesong.util;

public class DensityUtil {
    /**
     * dp转换为px
     * @param dpValue
     * @return
     */
    public static int dpToPx(float dpValue){
        float scale = LoveSongApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
