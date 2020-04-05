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

    public static void w(String tag, String format, Object... args) {
        if (DEBUG_MODEL) {
            Log.w(tag, String.format(format, args));
        }
    }

    public static void i(String tag, String format, Object... args) {
        if (DEBUG_MODEL) {
            Log.i(tag, String.format(format, args));
        }
    }
}
