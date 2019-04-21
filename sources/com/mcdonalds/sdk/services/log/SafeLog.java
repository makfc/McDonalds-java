package com.mcdonalds.sdk.services.log;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint({"LogTagMismatch"})
public class SafeLog {
    private SafeLog() {
    }

    /* renamed from: d */
    public static void m7744d(String tag, String msg) {
        if (isLoggable(msg)) {
            Log.d(tag, msg);
        }
    }

    /* renamed from: i */
    public static void m7747i(String tag, String msg) {
        if (isLoggable(msg)) {
            Log.i(tag, msg);
        }
    }

    /* renamed from: e */
    public static void m7745e(String tag, String msg) {
        if (isLoggable(msg)) {
            Log.e(tag, msg);
        }
    }

    /* renamed from: e */
    public static void m7746e(String tag, String msg, Throwable tr) {
        if (isLoggable(msg)) {
            Log.e(tag, msg, tr);
        }
    }

    private static boolean isLoggable(String msg) {
        return false;
    }

    public static void println(int priority, String tag, String msg) {
        if (isLoggable(msg)) {
            Log.println(priority, tag, msg);
        }
    }
}
