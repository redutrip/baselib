package com.keke.baselib;

import android.app.Service;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.baidu.mapapi.SDKInitializer;
import com.keke.baselib.utils.ActivityUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Wpz on 2016/11/23.
 */
public class BaseApplication extends MultiDexApplication {
    public LocationService locationService;
    public Vibrator mVibrator;
    /**
     * 当前应用的实例
     */
    private static BaseApplication mInstance;

    public static CloudPushService cloudPushService;

    /** 友盟-第三方登录 */
    private UMShareAPI mShareAPI;

    private ApplicationInfo appInfo;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        MultiDex.install(this);
        //UM分享
        initUM();
        //ali 推送
        initCloudChannel(this);
        //xUtils
        x.Ext.init(this);
        //baidu
        initBaiDu();
//        //即时通讯
//        initYWIM();
        initImageLoader();
        // 初始化Bugly
        initBugly();


    }

    /**
     * 获取当前应用程序的实例
     *
     * @return 应用程序的实例
     */
    public static BaseApplication getInstance() {
        return mInstance;
    }


    private void initImageLoader() {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
    }



    private void initBaiDu() {
        locationService = new LocationService(this);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }


    private void initUM() {
        if(appInfo == null ){
            try {
                appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        Config.DEBUG = false;
        // 各平台的 ID
        String weixinId = appInfo.metaData.getString("WEIXIN_ID");
        String weixinSecret = appInfo.metaData.getString("WEIXIN_SECRET");
        String qqId = appInfo.metaData.getString("QQ_ID");
        String qqKey = appInfo.metaData.getString("QQ_KEY");
        String sinaKey = appInfo.metaData.getString("SINA_KEY");
        String sinaSecret = appInfo.metaData.getString("SINA_SECRET");
        PlatformConfig.setWeixin(weixinId, weixinSecret);
        PlatformConfig.setQQZone(qqId, qqKey);
        PlatformConfig.setSinaWeibo(sinaKey, sinaSecret,"http://sns.whalecloud.com/sina2/callback");
        mShareAPI = UMShareAPI.get(this);

    }

    private void initBugly(){
        if(appInfo == null ){
            try {
            appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        // 设置是否为上报进程
        // 获取当前包名
        String packageName = mInstance.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mInstance);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        String buglyAppid = appInfo.metaData.getString("BUGLY_APPID");
        Bugly.init(mInstance, buglyAppid, false, strategy);
    }


    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        cloudPushService = PushServiceFactory.getCloudPushService();
        cloudPushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e("TAG", "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e("TAG", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    public UMShareAPI getUMShareAPI() {
        return mShareAPI;
    }
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    //关闭每一个list内的activity
    public void exit() {
        try {
            ActivityUtils.finishAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
