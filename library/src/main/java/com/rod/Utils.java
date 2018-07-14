package com.rod;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author Rod
 * @date 2018/7/15
 */
public final class Utils {

    public static void removeFromParent(View view) {
        if (view == null || !(view.getParent() instanceof ViewGroup)) {
            return;
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);
    }
}
