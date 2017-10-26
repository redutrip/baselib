package com.keke.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.keke.baselib.BaseApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;


public class BaseSPUtils {
    /**
     * 保存在手机里面的文件名
     */
//    protected static final String FILE_NAME = "keke_data";
    protected static final String FILE_NAME = "userInfo_guide"; //老版本中用的存储名
    protected static final String FILE_CHAT = "Info_chat"; //用于聊天的存储
    protected static final String FILE_GUIDEPAGE = "guidepage"; //用于聊天引导页


    public static boolean getIsLogin() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin", (Boolean) false) && getCookie().length()>0;
    }

    
    public static void  setLogin(boolean islogin) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", islogin);
        SharedPreferencesCompat.apply(editor);
    }

    public static String getToken() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString("token", "");
    }

    public static void  setToken(String token) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", token);
        SharedPreferencesCompat.apply(editor);
    }

    public static String getCookie() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString("loginCookie", "");
    }

    public static void  setCookie(String cookie) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginCookie", cookie);
        SharedPreferencesCompat.apply(editor);
    }

    public static void  clearCookie() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginCookie", "");
        editor.putString("permanentCookie", "");
        editor.commit();
    }

    public static String getPermanentCookie() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString("permanentCookie", "");
    }

    public static void  setPermanentCookie(String cookie) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("permanentCookie", cookie);
        SharedPreferencesCompat.apply(editor);
    }

    public static Double getLatitude() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return Double.parseDouble(sp.getString("latitude", "0"));
    }

    public static void  setLatitude(String latitude) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("latitude", latitude);
        SharedPreferencesCompat.apply(editor);
    }

    public static Double getLongitude() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return  Double.parseDouble(sp.getString("longitude", "0"));
    }

    public static void  setLongitude(String token) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("longitude", token);
        SharedPreferencesCompat.apply(editor);
    }

    public static String getUsername() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getString("username","");
    }

    public static void  setUsername(String username) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 存储LIST string
     * @return
     */
    public static void saveStringList(String name,ArrayList<String> list)  {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        // 还原数据
        int size = sp.getInt(name + "_size", 0);
        editor.putInt(name + "_size",0);
        for(int i=0;i< size;i++) {
            editor.remove(name + "_" + i);
        }
        editor.commit();
        // 添加数据
        editor.putInt(name + "_size",list.size());
        for(int i=0;i<list.size();i++) {
            editor.remove(name + "_" + i);
            editor.putString(name + "_" + i, list.get(i));
        }
        editor.commit();
    }
    /**
     * 取LIST string
     * @return
     */
    public static ArrayList<String>  getStringList(String name) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        int size = sp.getInt(name + "_size", 0);
        ArrayList<String> templist = new ArrayList<>();
        for(int i=0;i< size;i++) {
            templist.add(sp.getString(name + "_" + i, ""));
        }
        return templist;
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     * @author zhy
     *
     */
    protected static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();
        /**
         * 反射查找apply的方法
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {

            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            }
            editor.commit();
        }
    }


}
