package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.eu */
public class SDKDBOperation {
    /* renamed from: a */
    private DBOperation f1890a;
    /* renamed from: b */
    private Context f1891b;

    public SDKDBOperation(Context context, boolean z) {
        this.f1891b = context;
        this.f1890a = m2656a(this.f1891b, z);
    }

    /* renamed from: a */
    private DBOperation m2656a(Context context, boolean z) {
        try {
            return new DBOperation(context, DBOperation.m2614a(LogDBCreator.class));
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
                return null;
            }
            BasicLogHandler.m2542a(th, "SDKDB", "getDB");
            return null;
        }
    }

    /* renamed from: a */
    public void mo9352a(SDKInfo sDKInfo) {
        if (sDKInfo != null) {
            try {
                if (this.f1890a == null) {
                    this.f1890a = m2656a(this.f1891b, false);
                }
                String a = SDKInfo.m2496a(sDKInfo.mo9292a());
                List b = this.f1890a.mo9332b(a, SDKInfo.class);
                if (b == null || b.size() == 0) {
                    this.f1890a.mo9325a((Object) sDKInfo);
                } else {
                    this.f1890a.mo9329a(a, (Object) sDKInfo);
                }
            } catch (Throwable th) {
                BasicLogHandler.m2542a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public List<SDKInfo> mo9351a() {
        List<SDKInfo> list = null;
        try {
            return this.f1890a.mo9324a(SDKInfo.m2499f(), SDKInfo.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return list;
        }
    }
}
