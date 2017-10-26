package com.keke.baselib.utils;

import android.util.Log;

/**
 * 打印Log日志
 */

public class BaseLog {

    public static boolean isDebug = true;

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (isDebug)
            Log.i(" ",msg);
    }

    public static void d(String msg)
    {
        if (isDebug)
            Log.d(" ",msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(" " ,msg);
    }

    public static void v(String msg)
    {
        if (isDebug)
            Log.e(" " , msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.v(tag, msg);
    }
}
