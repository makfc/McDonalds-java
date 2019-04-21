package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

/* renamed from: com.amap.api.services.core.ay */
class ANRLogUpDateProcessor extends LogUpDateProcessor {
    /* renamed from: a */
    private static boolean f3698a = true;

    protected ANRLogUpDateProcessor(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12041a() {
        return C1107be.f3727d;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo12043b() {
        return 2;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo12042a(Context context) {
        if (C1138y.m5109g(context) != 1 || !f3698a) {
            return false;
        }
        f3698a = false;
        synchronized (Looper.getMainLooper()) {
            C1088ap c1088ap = new C1088ap(context);
            C1089ar a = c1088ap.mo12023a();
            if (a == null) {
                return true;
            } else if (a.mo12032c()) {
                a.mo12031c(false);
                c1088ap.mo12024a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
