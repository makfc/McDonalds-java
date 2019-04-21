package com.amap.api.services.core;

import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

/* compiled from: Request */
/* renamed from: com.amap.api.services.core.bs */
public abstract class C1075bs {
    /* renamed from: e */
    int f3609e = 20000;
    /* renamed from: f */
    int f3610f = 20000;
    /* renamed from: g */
    HttpHost f3611g = null;

    /* renamed from: b */
    public abstract String mo11971b();

    /* renamed from: c_ */
    public abstract Map<String, String> mo11972c_();

    /* renamed from: d_ */
    public abstract Map<String, String> mo11973d_();

    /* renamed from: e */
    public abstract HttpEntity mo11974e();

    /* renamed from: e_ */
    public byte[] mo11975e_() {
        return null;
    }

    /* renamed from: a */
    public final void mo11970a(HttpHost httpHost) {
        this.f3611g = httpHost;
    }
}
