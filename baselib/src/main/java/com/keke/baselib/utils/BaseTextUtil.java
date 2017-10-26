package com.keke.baselib.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class BaseTextUtil {

    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    public static String showPrice(String price) {
        if (null == price)
            return "0";
        if (price.equals(""))
            return "0";
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(price);
    }

    public static String showPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(price);
    }

    public static String showScore(double score) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(score);
    }

    public static String ListToSpecialString(List<String> stringList) {
        String result = "";
        for (int i = 0; i < stringList.size(); i++) {
            if (i == stringList.size() - 1) {
                result += stringList.get(i);
            } else {
                result += stringList.get(i) + "|";
            }
        }
        return result;
    }

    /**
     * 验证手机格式
     * @param mobiles
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPhoneNum(String mobiles) {
    /*
        移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
        联通：130、131、132、152、155、156、185、186
        电信：133、153、180、189、（1349卫通）
        总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
        13(老)号段：130、131、132、133、134、135、136、137、138、139
        14(新)号段：145、147
        15(新)号段：150、151、152、153、154、155、156、157、158、159
        17(新)号段：170、171、173、175、176、177、178
        18(3G)号段：180、181、182、183、184、185、186、187、188、189
        //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
    */
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


    /**
     * 校验邮箱
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        String REGEX_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return email.matches(REGEX_EMAIL);
    }
}
