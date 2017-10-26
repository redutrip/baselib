package com.keke.baselib.utils.common;

import android.util.Log;

/**
 * @author 刘畅
 * @describe 系统日志输出工具类
 * @return
 * @date 2017/6/14 10:30
 */
public class AppCommonLogUtils {

    //是否输出
    private static boolean isDebug = true;

    /*
     * 设置debug模式(true:打印日志  false：不打印)
     */
    public static void isEnableDebug(boolean isDebug) {
        AppCommonLogUtils.isDebug = isDebug;
    }

    /**
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg != null ? msg : "");
        }
    }

    public static void i(Object object, String msg) {
        if (isDebug) {
            Log.i(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(" [INFO] --- ", msg != null ? msg : "");
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg != null ? msg : "");
        }
    }

    public static void d(Object object, String msg) {
        if (isDebug) {
            Log.d(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(" [DEBUG] --- ", msg != null ? msg : "");
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg != null ? msg : "");
        }
    }

    public static void w(Object object, String msg) {
        if (isDebug) {
            Log.w(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(" [WARN] --- ", msg != null ? msg : "");
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg != null ? msg : "");
        }
    }

    public static void e(Object object, String msg) {
        if (isDebug) {
            Log.e(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(" [ERROR] --- ", msg != null ? msg : "");
        }
    }

    /**
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg != null ? msg : "");
        }
    }

    public static void v(Object object, String msg) {
        if (isDebug) {
            Log.v(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(" [VERBOSE] --- ", msg != null ? msg : "");
        }
    }

}
