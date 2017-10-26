package com.keke.baselib.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;

import com.keke.baselib.BaseApplication;

/**
 * Created by Administrator on 2016/5/12.
 */
public class SystemUtils {
    
    public static String getDiskCacheDir() {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = BaseApplication.getInstance().getExternalCacheDir().getPath();
        } else {
            cachePath = BaseApplication.getInstance().getCacheDir().getPath();
        }
        return cachePath;
    }

    public static void hideSoftKeyBoard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
//        cookieManager.removeSessionCookie();//移除
        cookieManager.removeAllCookie();
        String cookie = BaseSPUtils.getPermanentCookie();
        if(!TextUtils.isEmpty(cookie)){
            cookieManager.setCookie(url, cookie);
        }

        String shortcookie = BaseSPUtils.getCookie();
        if(!TextUtils.isEmpty(shortcookie)){
            cookieManager.setCookie(url, shortcookie);//cookies是在HttpClient中获得的cookie
        }
        CookieSyncManager.getInstance().sync();
    }


}
