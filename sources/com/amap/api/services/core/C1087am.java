package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation */
/* renamed from: com.amap.api.services.core.am */
public class C1087am {
    /* renamed from: a */
    private C1084ah f3661a = m4755a(this.f3662b);
    /* renamed from: b */
    private Context f3662b;

    public C1087am(Context context) {
        this.f3662b = context;
    }

    /* renamed from: a */
    private C1084ah m4755a(Context context) {
        try {
            return new C1084ah(context);
        } catch (Throwable th) {
            C1099ax.m4800a(th, "SDKDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void mo12020a(C1081ac c1081ac) {
        if (c1081ac != null) {
            try {
                if (this.f3661a == null) {
                    this.f3661a = m4755a(this.f3662b);
                }
                SDKEntity sDKEntity = new SDKEntity();
                sDKEntity.mo11995a(c1081ac);
                String a = SDKEntity.m4758a(c1081ac.mo11988a());
                List c = this.f3661a.mo12005c(a, new SDKEntity());
                if (c == null || c.size() == 0) {
                    this.f3661a.mo12002a(sDKEntity);
                } else {
                    this.f3661a.mo12004b(a, sDKEntity);
                }
            } catch (Throwable th) {
                C1099ax.m4800a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public List<C1081ac> mo12019a() {
        List<C1081ac> list = null;
        try {
            SDKEntity sDKEntity = new SDKEntity();
            return this.f3661a.mo12005c(SDKEntity.m4761c(), sDKEntity);
        } catch (Throwable th) {
            th.printStackTrace();
            return list;
        }
    }
}
