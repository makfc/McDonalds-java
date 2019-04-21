package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* compiled from: ExceptionLogProcessor */
/* renamed from: com.amap.api.mapcore2d.dg */
public class C0995dg extends C0991dh {
    /* renamed from: a */
    private static boolean f2834a = true;

    protected C0995dg(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo10197a(Context context) {
        if (C0968cq.m3970m(context) != 1 || !f2834a) {
            return false;
        }
        f2834a = false;
        synchronized (Looper.getMainLooper()) {
            C1009du c1009du = new C1009du(context);
            C1010dv a = c1009du.mo10234a();
            if (a == null) {
                return true;
            } else if (a.mo10239b()) {
                a.mo10238b(false);
                c1009du.mo10235a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo10193a(List<C0977cv> list) {
        return null;
    }
}
