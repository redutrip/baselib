package com.keke.baselib.utils;

/**
 * @author 刘畅
 * @describe 点击事件工具类
 * @return 无返回值
 * @date 2017/6/7 9:39
 */
public class ClickUtils {

    private static long lastClickTime;
    private final static int SPACE_TIME = 1000;
    private final static int SPACE_TIME_SHORT = 500;
//
//    public static void initLastClickTime() {
//        lastClickTime = 0;
//    }

    public synchronized static boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isDouble;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isDouble = false;
        } else {
            isDouble = true;
        }
        lastClickTime = currentTime;
        return isDouble;
    }

}
