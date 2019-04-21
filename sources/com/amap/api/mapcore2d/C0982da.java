package com.amap.api.mapcore2d;

import android.content.Context;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: BasicLogHandler */
/* renamed from: com.amap.api.mapcore2d.da */
public class C0982da {
    /* renamed from: a */
    protected static C0982da f2799a;
    /* renamed from: b */
    protected UncaughtExceptionHandler f2800b;
    /* renamed from: c */
    protected boolean f2801c = true;

    /* renamed from: a */
    public static void m4076a(Throwable th, String str, String str2) {
        th.printStackTrace();
        if (f2799a != null) {
            f2799a.mo10182a(th, 1, str, str2);
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10182a(Throwable th, int i, String str, String str2) {
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10181a(Context context, C0977cv c0977cv, boolean z) {
    }
}
