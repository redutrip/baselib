package com.keke.baselib.utils.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.keke.baselib.common.Constants;

/**
 * @author 刘畅
 * @describe App网络管理工具类
 * @return
 * @date 2017/6/14 10:27
 */
public class AppCommonNetUtils {

    //未找到合适匹配网络类型
    public static final int TYPE_NO = 0;

    //中国移动CMNET网络(中国移动GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)
    public static final int TYPE_MOBILE_CMNET = 1;

    //中国移动CMWAP网络(中国移动GPRS接入方式之一,主要为手机WAP上网而设立)
    public static final int TYPE_MOBILE_CMWAP = 2;

    //中国联通UNIWAP网络(中国联通划分GPRS接入方式之一, 主要为手机WAP上网而设立)
    public static final int TYPE_MOBILE_UNIWAP = 3;

    //中国联通3GWAP网络
    public static final int TYPE_MOBILE_3GWAP = 4;

    //中国联通3HNET网络
    public static final int TYPE_MOBLIE_3GNET = 5;

    //中国联通UNINET网络(中国联通划分GPRS接入方式之一, 主要为PC、笔记本电脑、PDA设立)
    public static final int TYPE_MOBILE_UNINET = 6;

    //中国电信CTWAP网络
    public static final int TYPE_MOBILE_CTWAP = 7;

    //中国电信CTNET网络
    public static final int TYPE_MOBILE_CTNET = 8;

    //WIFI网络
    public static final int TYPE_WIFI = 10;

    /**
     * 获取当前手机连接的网络类型
     *
     * @param context 上下文
     * @return int 网络类型
     */
    public static int getNetworkState(Context context) {
        //获取ConnectivityManager对象
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获得当前网络信息
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            //获取网络类型
            int currentNetWork = networkInfo.getType();
            //手机网络类型
            if (currentNetWork == ConnectivityManager.TYPE_MOBILE) {
                if (networkInfo.getExtraInfo() != null) {
                    if (networkInfo.getExtraInfo().equals("cmnet")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国移动CMNET网络");
                        return TYPE_MOBILE_CMNET;
                    }
                    if (networkInfo.getExtraInfo().equals("cmwap")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国移动CMWAP网络");
                        return TYPE_MOBILE_CMWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("uniwap")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国联通UNIWAP网络");
                        return TYPE_MOBILE_UNIWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("3gwap")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国联通3GWAP网络");
                        return TYPE_MOBILE_3GWAP;
                    }
                    if (networkInfo.getExtraInfo().equals("3gnet")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国联通3GNET网络");
                        return TYPE_MOBLIE_3GNET;
                    }
                    if (networkInfo.getExtraInfo().equals("uninet")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国联通UNINET网络");
                        return TYPE_MOBILE_UNINET;
                    }
                    if (networkInfo.getExtraInfo().equals("ctwap")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国电信CTWAP网络");
                        return TYPE_MOBILE_UNINET;
                    }
                    if (networkInfo.getExtraInfo().equals("ctnet")) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为中国电信CTNET网络");
                        return TYPE_MOBILE_UNINET;
                    }
                }
                //WIFI网络类型
            } else if (currentNetWork == ConnectivityManager.TYPE_WIFI) {
                AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为WIFI网络");
                return TYPE_WIFI;
            }
        }
        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "当前网络为不是我们考虑的网络");
        return TYPE_NO;
    }

    /**
     * 判断网络是否连接
     *
     * @param context 上下文
     * @return boolean 网络连接状态
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            //获取连接对象
            if (mNetworkInfo != null) {
                //判断是TYPE_MOBILE网络
                if (ConnectivityManager.TYPE_MOBILE == mNetworkInfo.getType()) {
                    AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "网络连接类型为：TYPE_MOBILE");
                    //判断移动网络连接状态
                    NetworkInfo.State STATE_MOBILE = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                    if (STATE_MOBILE == NetworkInfo.State.CONNECTED) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "网络连接类型为：TYPE_MOBILE, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
                //判断是TYPE_WIFI网络
                if (ConnectivityManager.TYPE_WIFI == mNetworkInfo.getType()) {
                    AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "网络连接类型为：TYPE_WIFI");
                    //判断WIFI网络状态
                    NetworkInfo.State STATE_WIFI = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
                    if (STATE_WIFI == NetworkInfo.State.CONNECTED) {
                        AppCommonLogUtils.i(Constants.APP_COMMON_NET_UTILS_FLAG, "网络连接类型为：TYPE_WIFI, 网络连接状态CONNECTED成功！");
                        return mNetworkInfo.isAvailable();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断网络是否连接
     *
     * @param activity Activity
     * @return boolean 网络连接状态
     */
    public static boolean isNetworkConnected(Activity activity) {
        boolean falg = false;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager == null) {
            return falg;
        }
        NetworkInfo[] arrayOfNetworkInfo = mConnectivityManager.getAllNetworkInfo();
        if (arrayOfNetworkInfo != null) {
            for (int j = 0; j < arrayOfNetworkInfo.length; j++) {
                if (arrayOfNetworkInfo[j].getState() == NetworkInfo.State.CONNECTED) {
                    falg = true;
                    break;
                }
            }
        }
        return falg;
    }

    /**
     * 打开网络设置界面
     *
     * @param activity Activity
     */
    public static void openNetSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

    /**
     * 检测3G是否连接
     *
     * @param context 上下文
     * @return 结果
     */
    public static boolean is3gConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null
                    && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }


}
