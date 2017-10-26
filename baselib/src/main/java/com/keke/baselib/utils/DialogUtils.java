package com.keke.baselib.utils;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.View;

import com.keke.baselib.view.NormalDialogFragment;


public class DialogUtils {
    public static final int TYPE_NORMAL_SUCCESS = 0;
    public static final int TYPE_NORMAL_ERROR = 1;
    public static final int TYPE_NORMAL_WARNING = 2;

    public static void showNormal(Activity activity,int type,String contentStr) {
        final NormalDialogFragment normalFrgDialog = new NormalDialogFragment();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        normalFrgDialog.setContentStr(contentStr);
        normalFrgDialog.setContentType(type);
        normalFrgDialog.setFinalBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalFrgDialog.dismiss();
            }
        });
        normalFrgDialog.show(ft, "normalFrgDialog");
    }

    public static void showNormal(Activity activity,String contentStr) {
        showNormal(activity,DialogUtils.TYPE_NORMAL_SUCCESS,contentStr);
    }


}
