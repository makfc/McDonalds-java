package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.amap.api.mapcore.util.eb */
public class BasicLogHandler {
    /* renamed from: a */
    protected static BasicLogHandler f1844a;
    /* renamed from: b */
    protected UncaughtExceptionHandler f1845b;
    /* renamed from: c */
    protected boolean f1846c = true;

    /* renamed from: a */
    public static void m2542a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (f1844a != null) {
            f1844a.mo9303a(th, 1, str, str2);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9303a(Throwable th, int i, String str, String str2) {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo9302a(Context context, SDKInfo sDKInfo, boolean z) {
    }
}
