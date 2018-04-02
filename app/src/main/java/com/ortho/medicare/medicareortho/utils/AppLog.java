package com.ortho.medicare.medicareortho.utils;

import android.util.Log;

/**
 * {@link AppLog}: this class is responsible to
 * handle log in application
 *
 * @author ashish mishra
 * @version : 0.0.1
 * @Date : 27/01/2017
 */

public class AppLog {

    private static boolean isProd = false;

    public static void LogD(String tag, String message) {
        if (!isProd) {
            Log.d(tag, message);
        }
    }

    public static void LogE(String tag, String message) {
        if (!isProd) {
            Log.e(tag, message);
        }
    }

    public static void LogW(String tag, String message) {
        if (!isProd) {
            Log.d(tag, message);
        }
    }

    public static void LogI(String tag, String message) {
        if (!isProd) {
            Log.i(tag, message);
        }
    }
}
