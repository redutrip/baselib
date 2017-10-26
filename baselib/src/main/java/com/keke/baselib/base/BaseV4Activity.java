package com.keke.baselib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

// 支持v4包的activity
public abstract class BaseV4Activity extends RxAppCompatActivity implements BaseView{
	private  final String TAG = "BaseActivity";
	// 临时用户的缓存

	protected String activityName ;
	protected Subscription subscription;
	protected List<Subscription> subscriptionList = new ArrayList<>();
	protected ProgressMaterial progressMaterial;




	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityName = this.getClass().getSimpleName();
		BaseLog.i(TAG, activityName + " onCreate");

		progressMaterial  = ProgressMaterial.create(this, true, null);

		BasicCustomPushNotification notification = new BasicCustomPushNotification();
		notification.setBuildWhenAppInForeground(false);
		boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(1, notification);//注册该通知,并设置ID为1

		ActivityUtils.addActivity(this);
		setImmersionStatus(); //顶部框的处理。
    }



	private void setImmersionStatus() {
		StatusBarUtil.setTranslucent(this);
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			// 透明状态栏
//			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 透明导航栏
////			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//		}

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
		if(subscriptionList.size()!=0){
			for (Subscription subscription : subscriptionList){
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



}
