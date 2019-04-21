package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* compiled from: CrashLogProcessor */
/* renamed from: com.amap.api.mapcore2d.df */
public class C0994df extends C0991dh {
    /* renamed from: a */
    private static boolean f2833a = true;

    protected C0994df(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo10197a(Context context) {
        if (!f2833a) {
            return false;
        }
        f2833a = false;
        synchronized (Looper.getMainLooper()) {
            C1009du c1009du = new C1009du(context);
            C1010dv a = c1009du.mo10234a();
            if (a == null) {
                return true;
            } else if (a.mo10237a()) {
                a.mo10236a(false);
                c1009du.mo10235a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo10191a(String str) {
        return C0970cs.m3997c(str + C0978cw.m4041a(new Date().getTime()));
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public String mo10193a(List<C0977cv> list) {
        return null;
    }
}
