package com.admaster.square.api;

import android.util.Log;

/* renamed from: com.admaster.square.api.m */
public class Logger {
    /* renamed from: a */
    private static boolean f234a;

    /* renamed from: a */
    public static void m363a(boolean z) {
        f234a = z;
    }

    /* renamed from: a */
    public static void m362a(String str) {
        try {
            if (f234a) {
                Log.w("AdMasterConvMobi", str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public static void m364b(String str) {
        try {
            if (f234a) {
                Log.e("AdMasterConvMobi", str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
