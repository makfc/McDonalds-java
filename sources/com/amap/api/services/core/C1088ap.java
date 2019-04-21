package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

/* compiled from: UpdateLogDBOperation */
/* renamed from: com.amap.api.services.core.ap */
public class C1088ap {
    /* renamed from: a */
    private C1084ah f3670a = m4768a(this.f3671b);
    /* renamed from: b */
    private Context f3671b;

    public C1088ap(Context context) {
        this.f3671b = context;
    }

    /* renamed from: a */
    private C1084ah m4768a(Context context) {
        try {
            return new C1084ah(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "UpdateLogDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public C1089ar mo12023a() {
        try {
            if (this.f3670a == null) {
                this.f3670a = m4768a(this.f3671b);
            }
            List c = this.f3670a.mo12005c("1=1", new UpdateLogEntity());
            if (c.size() > 0) {
                return (C1089ar) c.get(0);
            }
        } catch (Throwable th) {
            C1099ax.m4800a(th, "UpdateLogDB", "getUpdateLog");
            th.printStackTrace();
        }
        return null;
    }

    /* renamed from: a */
    public void mo12024a(C1089ar c1089ar) {
        if (c1089ar != null) {
            try {
                if (this.f3670a == null) {
                    this.f3670a = m4768a(this.f3671b);
                }
                UpdateLogEntity updateLogEntity = new UpdateLogEntity();
                updateLogEntity.mo11995a(c1089ar);
                String str = "1=1";
                List c = this.f3670a.mo12005c(str, new UpdateLogEntity());
                if (c == null || c.size() == 0) {
                    this.f3670a.mo12002a(updateLogEntity);
                } else {
                    this.f3670a.mo12004b(str, updateLogEntity);
                }
            } catch (Throwable th) {
                C1099ax.m4800a(th, "UpdateLogDB", "updateLog");
                th.printStackTrace();
            }
        }
    }
}
