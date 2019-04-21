package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

/* renamed from: com.amap.api.services.core.bc */
class ExceptionLogUpDateProcessor extends LogUpDateProcessor {
    /* renamed from: a */
    private static boolean f3714a = true;

    protected ExceptionLogUpDateProcessor(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12041a() {
        return C1107be.f3725b;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo12043b() {
        return 1;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo12042a(Context context) {
        if (C1138y.m5109g(context) != 1 || !f3714a) {
            return false;
        }
        f3714a = false;
        synchronized (Looper.getMainLooper()) {
            C1088ap c1088ap = new C1088ap(context);
            C1089ar a = c1088ap.mo12023a();
            if (a == null) {
                return true;
            } else if (a.mo12030b()) {
                a.mo12029b(false);
                c1088ap.mo12024a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
