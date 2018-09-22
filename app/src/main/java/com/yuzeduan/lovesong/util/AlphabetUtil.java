package com.yuzeduan.lovesong.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 获取首汉字的首拼音字母,或者英文的首字母
 */
public class AlphabetUtil {

    public static String getFirstAlphabet(String str){
        if(str != null){
            char[] strChar = str.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
            //设置输出大写
            outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            //设置输出不带音标
            outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            StringBuilder pyStringBuilder = new StringBuilder();
            //通过正则表达式判断是否是中文,否则拿英文的首字母
            if(String.valueOf(strChar[0]).matches("[\\u4E00-\\u9FA5]+")){
                try {
                    String[] pyStringArray = PinyinHelper.toHanyuPinyinStringArray(strChar[0], outputFormat);
                    if (null != pyStringArray && pyStringArray[0] != null) {
                        pyStringBuilder.append(pyStringArray[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else{
                pyStringBuilder.append(str.toUpperCase().charAt(0));
            }
            return pyStringBuilder.toString();
        }
        return null;
    }
}
