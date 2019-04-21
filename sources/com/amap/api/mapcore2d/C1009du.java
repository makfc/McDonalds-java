package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.List;

/* compiled from: UpdateLogDBOperation */
/* renamed from: com.amap.api.mapcore2d.du */
public class C1009du {
    /* renamed from: a */
    private C0998dj f2847a = m4195a(this.f2848b);
    /* renamed from: b */
    private Context f2848b;

    public C1009du(Context context) {
        this.f2848b = context;
    }

    /* renamed from: a */
    private C0998dj m4195a(Context context) {
        try {
            return new C0998dj(context, C0998dj.m4152a(C1006dq.class));
        } catch (Throwable th) {
            C0982da.m4076a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    /* renamed from: a */
    public C1010dv mo10234a() {
        try {
            if (this.f2847a == null) {
                this.f2847a = m4195a(this.f2848b);
            }
            List b = this.f2847a.mo10213b("1=1", C1010dv.class);
            if (b.size() > 0) {
                return (C1010dv) b.get(0);
            }
        } catch (Throwable th) {
            C0982da.m4076a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    /* renamed from: a */
    public void mo10235a(C1010dv c1010dv) {
        if (c1010dv != null) {
            try {
                if (this.f2847a == null) {
                    this.f2847a = m4195a(this.f2848b);
                }
                String str = "1=1";
                List b = this.f2847a.mo10213b(str, C1010dv.class);
                if (b == null || b.size() == 0) {
                    this.f2847a.mo10208a((Object) c1010dv);
                } else {
                    this.f2847a.mo10211a(str, (Object) c1010dv);
                }
            } catch (Throwable th) {
                C0982da.m4076a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}
