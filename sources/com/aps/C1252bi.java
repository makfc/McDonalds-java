package com.aps;

import android.content.Context;
import android.util.Log;

/* renamed from: com.aps.bi */
public final class C1252bi {
    /* renamed from: a */
    private static String f4377a = "";

    /* renamed from: a */
    protected static void m5553a(String str) {
        if (str.equals("GPS_SATELLITE")) {
        }
    }

    /* renamed from: a */
    protected static boolean m5554a(Context context) {
        if (context != null) {
            f4377a = context.getPackageName();
            return true;
        }
        Log.d(f4377a, "Error: No SD Card!");
        return false;
    }
}
