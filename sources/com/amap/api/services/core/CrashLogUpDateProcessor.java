package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

/* renamed from: com.amap.api.services.core.ba */
class CrashLogUpDateProcessor extends LogUpDateProcessor {
    /* renamed from: a */
    private static boolean f3710a = true;

    protected CrashLogUpDateProcessor(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo12041a() {
        return C1107be.f3726c;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo12043b() {
        return 0;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo12042a(Context context) {
        if (!f3710a) {
            return false;
        }
        f3710a = false;
        synchronized (Looper.getMainLooper()) {
            C1088ap c1088ap = new C1088ap(context);
            C1089ar a = c1088ap.mo12023a();
            if (a == null) {
                return true;
            } else if (a.mo12028a()) {
                a.mo12027a(false);
                c1088ap.mo12024a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
