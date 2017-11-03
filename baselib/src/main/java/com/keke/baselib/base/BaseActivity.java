package com.keke.baselib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.sdk.android.push.notification.BasicCustomPushNotification;
import com.alibaba.sdk.android.push.notification.CustomNotificationBuilder;
import com.keke.baselib.utils.ActivityUtils;
import com.keke.baselib.utils.BaseLog;
import com.keke.baselib.utils.StatusBarUtil;
import com.keke.baselib.utils.XPermissionUtils;
import com.keke.viewlib.progress.ProgressMaterial;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//         佛祖保佑       永无BUG     永不修改                    	  //
////////////////////////////////////////////////////////////////////
public abstract class BaseActivity extends RxAppCompatActivity implements BaseView {
    private final String TAG = "BaseActivity";
    // 临时用户的缓存

    protected String activityName;
    protected Subscription subscription;
    protected List<Subscription> subscriptionList = new ArrayList<>();
    protected ProgressMaterial progressMaterial;

    private static Activity activity;
    public static Activity getThis() {
        return activity;
    }

    private boolean needFinish = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        activityName = this.getClass().getSimpleName();
        BaseLog.i(TAG, activityName + " onCreate");
        progressMaterial = ProgressMaterial.create(this, true, null);

        BasicCustomPushNotification notification = new BasicCustomPushNotification();
        notification.setBuildWhenAppInForeground(false);
        boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(1, notification);//注册该通知,并设置ID为1

        ActivityUtils.addActivity(this);
        setImmersionStatus(); //顶部框的处理。

    }


    private void setImmersionStatus() {
        StatusBarUtil.setTranslucent(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
////            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //亮色状态栏模式
//            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getSimpleName());
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        MobclickAgent.onPause(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(needFinish)
            finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseLog.i(TAG, activityName + "onDestroy()");
        ActivityUtils.removeActivity(this);
        unsubscribe();
    }


    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        if (subscriptionList.size() != 0) {
            for (Subscription subscription : subscriptionList) {
                if (subscription != null && !subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                }
            }
        }
    }

    public ProgressMaterial getProgressMaterial() {
        if(progressMaterial == null)
            progressMaterial= ProgressMaterial.create(this, true, null);
        return progressMaterial;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        XPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showLoadingBar() {
        getProgressMaterial().show();
    }

    @Override
    public void hideLoadindBar() {
        getProgressMaterial().dismiss();

    }

    @Override
    public void finish() {
        super.finish();
    }

    public void showSuccessDialog(String msg) {
//		DialogUtils.showNormal(this,DialogUtils.TYPE_NORMAL_SUCCESS,msg);
    }

    public void showWaringDialog(String msg) {
//		DialogUtils.showNormal(this,DialogUtils.TYPE_NORMAL_WARNING,msg);
    }

    public void showErrorDialog(String msg) {
//		DialogUtils.showNormal(this,DialogUtils.TYPE_NORMAL_ERROR,msg);
    }

}
