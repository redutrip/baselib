package com.keke.viewlib.iosaleart;

import android.view.Gravity;

import com.keke.baselib.R;

/**
 * Created by Sai on 15/8/9.
 */
public class AlertAnimateUtil {
    private static final int INVALID = -1;
    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.iosalert_slide_in_bottom : R.anim.iosalert_slide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.iosalert_fade_in_center : R.anim.iosalert_fade_out_center;
        }
        return INVALID;
    }
}
