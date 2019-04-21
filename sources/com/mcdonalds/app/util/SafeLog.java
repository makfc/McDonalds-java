package com.mcdonalds.app.util;

import android.util.Log;
import com.ensighten.Ensighten;

public class SafeLog {
    private SafeLog() {
    }

    /* renamed from: d */
    public static void m7737d(String tag, String msg) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.SafeLog", "d", new Object[]{tag, msg});
        if (isLoggable(msg)) {
            Log.d(tag, msg);
        }
    }

    /* renamed from: i */
    public static void m7739i(String tag, String msg) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.SafeLog", "i", new Object[]{tag, msg});
        if (isLoggable(msg)) {
            Log.i(tag, msg);
        }
    }

    /* renamed from: e */
    public static void m7738e(String tag, String msg, Throwable tr) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.SafeLog", "e", new Object[]{tag, msg, tr});
        if (isLoggable(msg)) {
            Log.e(tag, msg, tr);
        }
    }

    private static boolean isLoggable(String msg) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.util.SafeLog", "isLoggable", new Object[]{msg});
        return false;
    }
}
