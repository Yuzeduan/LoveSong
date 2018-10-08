package com.yuzeduan.lovesong.music.util;

import com.yuzeduan.lovesong.music.bean.LrcEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 解析Lrc歌词的工具类
 * author: Allen
 * date: On 2018/10/7
 */
public class ParseLrc{

    public static List<LrcEntity> parseLrc(String lrcText){
        if(lrcText.equals("")){
            return null;
        }
        List<LrcEntity> entityList = new ArrayList<>();
        // 将字符串以换行符切割
        String[] array = lrcText.split("\\n");

        for (String line : array) {
            // 循环遍历按行解析
            List<LrcEntity> list = parseLine(line);
            if (list != null && !list.isEmpty()) {
                entityList.addAll(list);
            }
        }
        // 使序列按大小升序排序（由小到大）
        Collections.sort(entityList);
        return entityList;
    }

    /**
     * 解析单行歌词
     * @param line
     * @return
     */
    private static List<LrcEntity> parseLine(String line){
        if(line.equals("")){
            return null;
        }
        List<LrcEntity> entryList = new ArrayList<>();
        int pos1 = line.indexOf("[");//0
        int pos2 = line.indexOf("]");//9  indexof如果找不到返回-1
        if (pos1 == 0 && pos2 != -1) {
            //long数组用于存放时间戳，判断含有多少个时间标签
            String[] times = new String[getCount(line)];
            String strTime = line.substring(pos1, pos2+1);//[02:45.69]
            // 时间标签数组
            times[0] = strTime;
            //判断是否还有下一个
            String text = line;
            int i = 1;
            while (pos1 == 0 && pos2 != -1) {
                text = text.substring(pos2 + 1);
                pos1 = text.indexOf("[");//0
                pos2 = text.indexOf("]");//9
                if (pos2 != -1) {
                    strTime = text.substring(pos1, pos2 + 1);
                    times[i] = strTime;//将第二个时间戳添加到数组中
                    if (times[i].equals("")) {
                        return entryList;
                    }
                }
                i++;
            }
            LrcEntity lrcEntity = new LrcEntity();
            for (String time : times ) {
                if (time != null && StrToLong(time) != -1) {
                    lrcEntity.setmText(text);
                    lrcEntity.setmTimeLong(StrToLong(time));
                    lrcEntity.setmTime(time);
                    entryList.add(lrcEntity);
                    lrcEntity = new LrcEntity();
                }
            }
        }
        return entryList;
    }


    //将字符串的时间格式转换为long类型
    private static long StrToLong(String strTime) {
        long showTime = -1;
        if(!strTime.contains(".")){
            return showTime;
        }
        try {
            strTime = strTime.substring(1,strTime.length()-1);
            String[] s1 = strTime.split(":");
            String[] s2 = s1[1].split("\\.");
            long min = Long.parseLong(s1[0]);
            long second = Long.parseLong(s2[0]);
            long mil = Long.parseLong(s2[1]);
            showTime = min * 60 * 1000 + second * 1000 + mil * 10;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return showTime;
    }

    /**
     * 判断当前行播放的次数
     * @param line
     * @return
     */
    private static int getCount(String line) {
        String[] split = line.split("\\]");
        return split.length;
    }
}
