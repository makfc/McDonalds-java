package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.List;

/* compiled from: LogDBOperation */
/* renamed from: com.amap.api.mapcore2d.dr */
public class C1007dr {
    /* renamed from: a */
    private C0998dj f2844a;

    public C1007dr(Context context) {
        try {
            this.f2844a = new C0998dj(context, C0998dj.m4152a(C1006dq.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo10229a(String str, Class<? extends C1001ds> cls) {
        try {
            m4186c(str, cls);
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogDB", "delLog");
        }
    }

    /* renamed from: b */
    public void mo10231b(String str, Class<? extends C1001ds> cls) {
        try {
            m4186c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m4186c(String str, Class<? extends C1001ds> cls) {
        this.f2844a.mo10210a(C1001ds.m4174c(str), (Class) cls);
    }

    /* renamed from: a */
    public List<? extends C1001ds> mo10227a(int i, Class<? extends C1001ds> cls) {
        List<? extends C1001ds> list = null;
        try {
            return this.f2844a.mo10213b(C1001ds.m4173c(i), cls);
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogDB", "ByState");
            return list;
        }
    }

    /* renamed from: a */
    public void mo10228a(C1001ds c1001ds) {
        if (c1001ds != null) {
            String c = C1001ds.m4174c(c1001ds.mo10221b());
            List a = this.f2844a.mo10207a(c, c1001ds.getClass(), true);
            if (a == null || a.size() == 0) {
                this.f2844a.mo10209a((Object) c1001ds, true);
                return;
            }
            Object obj = (C1001ds) a.get(0);
            if (c1001ds.mo10218a() == 0) {
                obj.mo10222b(obj.mo10224c() + 1);
            } else {
                obj.mo10222b(0);
            }
            this.f2844a.mo10212a(c, obj, true);
        }
    }

    /* renamed from: b */
    public void mo10230b(C1001ds c1001ds) {
        try {
            this.f2844a.mo10211a(C1001ds.m4174c(c1001ds.mo10221b()), (Object) c1001ds);
        } catch (Throwable th) {
            C0982da.m4076a(th, "LogDB", "updateLogInfo");
        }
    }
}
