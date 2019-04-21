package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation */
/* renamed from: com.amap.api.mapcore2d.dt */
public class C1008dt {
    /* renamed from: a */
    private C0998dj f2845a;
    /* renamed from: b */
    private Context f2846b;

    public C1008dt(Context context, boolean z) {
        this.f2846b = context;
        this.f2845a = m4192a(this.f2846b, z);
    }

    /* renamed from: a */
    private C0998dj m4192a(Context context, boolean z) {
        try {
            return new C0998dj(context, C0998dj.m4152a(C1006dq.class));
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
                return null;
            }
            C0982da.m4076a(th, "SDKDB", "getDB");
            return null;
        }
    }

    /* renamed from: a */
    public void mo10233a(C0977cv c0977cv) {
        if (c0977cv != null) {
            try {
                if (this.f2845a == null) {
                    this.f2845a = m4192a(this.f2846b, false);
                }
                String a = C0977cv.m4031a(c0977cv.mo10172a());
                List b = this.f2845a.mo10213b(a, C0977cv.class);
                if (b == null || b.size() == 0) {
                    this.f2845a.mo10208a((Object) c0977cv);
                } else {
                    this.f2845a.mo10211a(a, (Object) c0977cv);
                }
            } catch (Throwable th) {
                C0982da.m4076a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public List<C0977cv> mo10232a() {
        List<C0977cv> list = null;
        try {
            return this.f2845a.mo10207a(C0977cv.m4034f(), C0977cv.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return list;
        }
    }
}
