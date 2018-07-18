package com.rod.log;

import android.util.Log;

/**
 * @author Rod
 * @date 2018/7/18
 */
public final class PL {

    private static final boolean DEBUG_MODEL = true;

    public static void d(String tag, String format, Object... args) {
        if (DEBUG_MODEL) {
            Log.d(tag, String.format(format, args));
        }
    }
}
